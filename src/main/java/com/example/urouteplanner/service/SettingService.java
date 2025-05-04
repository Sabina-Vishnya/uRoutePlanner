package com.example.urouteplanner.service;

import com.example.urouteplanner.enums.ScheduleType;
import com.example.urouteplanner.persistence.entity.Route;
import com.example.urouteplanner.persistence.entity.Setting;
import com.example.urouteplanner.persistence.repository.SettingRepository;
import com.example.urouteplanner.service.dto.UserSettingDto;
import com.example.urouteplanner.utility.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SettingService {

    private final SettingRepository settingRepository;

    Setting getOrSaveSetting(UserSettingDto command, Route route) {
        return settingRepository.findByRouteAndArrivalTimeAndBufferTimeAndScheduleType(
            route,
            command.getArrivalTime(),
            Constant.BUFFER_TIME_IN_SECONDS,
            ScheduleType.WEEKDAYS
        ).orElseGet(() -> {
            Setting newSetting = new Setting();
            newSetting.setRoute(route);
            newSetting.setArrivalTime(command.getArrivalTime());
            newSetting.setBufferTime(Constant.BUFFER_TIME_IN_SECONDS);
            newSetting.setScheduleType(ScheduleType.WEEKDAYS);  // todo пока всегда по будням?
            settingRepository.save(newSetting);
            return newSetting;
        });
    }
}
