package com.example.urouteplanner.service;

import com.example.urouteplanner.controller.UserSettingRequest;
import com.example.urouteplanner.mapper.UserSettingMapper;
import com.example.urouteplanner.persistence.entity.Location;
import com.example.urouteplanner.persistence.entity.Route;
import com.example.urouteplanner.persistence.entity.Setting;
import com.example.urouteplanner.persistence.entity.User;
import com.example.urouteplanner.persistence.entity.UserSetting;
import com.example.urouteplanner.persistence.repository.UserRepository;
import com.example.urouteplanner.persistence.repository.UserSettingRepository;
import com.example.urouteplanner.service.dto.UserSettingDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserSettingsService {
    private final UserSettingMapper userSettingMapper;
    private final RouteService routeService;
    private final SettingService settingService;
    private final LocationService locationService;
    private final UserRepository userRepository;
    private final UserSettingRepository userSettingRepository;

    @Transactional
    public void createUserSettings(UserSettingRequest request) {
        User user = userRepository.findByChatId(request.getChatId())
            .orElseGet(() -> {
                User newUser = new User();
                newUser.setChatId(request.getChatId());
                newUser.setUsername(request.getUsername());
                return userRepository.save(newUser);
            });


        if (userSettingRepository.existsByUser_Id(user.getId())) {
            throw new IllegalStateException(MessageFormat.format(
                "Настройки для пользователя {} уже существуют", user.getId()
            ));
        }

        UserSettingDto userSettingDto = userSettingMapper.toMap(request);

        Location startLocation = locationService.getOrSaveLocation(userSettingDto.getStartPoint());
        Location endLocation = locationService.getOrSaveLocation(userSettingDto.getEndPoint());
        Route route = routeService.getOrSaveRoute(startLocation, endLocation);

        Setting setting = settingService.getOrSaveSetting(userSettingDto, route);

        UserSetting userSetting = new UserSetting();
        userSetting.setUser(user);
        userSetting.setSetting(setting);
        userSettingRepository.save(userSetting);

        log.debug("""
                Добавлена новая настройка!
                Пользователь: {}
                Часовой пояс по стартовой локации пользователя: {}
                Необходимое время прибытия: {}
                """,
            userSetting.getUser().getChatId(),
            userSetting.getSetting().getRoute().getFromLocation().getTimezone(),
            userSetting.getSetting().getArrivalTime()
        );
    }
}