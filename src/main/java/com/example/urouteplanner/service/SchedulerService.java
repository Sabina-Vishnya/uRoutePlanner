package com.example.urouteplanner.service;


import com.example.urouteplanner.persistence.entity.Location;
import com.example.urouteplanner.persistence.entity.Route;
import com.example.urouteplanner.persistence.entity.Setting;
import com.example.urouteplanner.persistence.entity.User;
import com.example.urouteplanner.persistence.entity.UserSetting;
import com.example.urouteplanner.persistence.integration.twogis.Point;
import com.example.urouteplanner.persistence.integration.twogis.TwoGisService;
import com.example.urouteplanner.persistence.repository.UserSettingRepository;
import com.example.urouteplanner.utility.TimeCalculation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class SchedulerService {
    private final NotificationService notificationService;
    private final TwoGisService twoGisService;

    private final UserSettingRepository userSettingRepository;

    //TODO 2ой шшедулер на КЭШ?


    @Scheduled(cron = "0 * * * * MON-FRI") // каждую минуту пн-пт
    public void sendNotifications() {
        userSettingRepository.findAll().forEach(userSetting -> {
            var user = userSetting.getUser();
            try {
                processUserSetting(userSetting);
            } catch (Exception e) {
                log.error("Ошибка отправки уведомления для пользователя: {} в чат: {}: {}",
                    user.getUsername(), user.getChatId(), e.getMessage());
            }
        });
    }

    private void processUserSetting(UserSetting userSetting) {
        ZonedDateTime systemNow = ZonedDateTime.now(ZoneOffset.UTC);

        Setting setting = userSetting.getSetting();
        Route route = setting.getRoute();
        Location fromLocation = route.getFromLocation();
        Location toLocation = route.getToLocation();
        log.debug("Проверка уведомлений (UTC): {}, пользователь с тайм-зоной {}", systemNow, fromLocation);

        ZonedDateTime currentUserTimeZone = systemNow.withZoneSameInstant(ZoneId.of(fromLocation.getTimezone()));

        if (isWeekdayForUserNow(currentUserTimeZone)) {
            int routeDurationInSeconds = getRouteDurationInSeconds(fromLocation, toLocation);

            LocalTime userArrivalTime = setting.getArrivalTime();
            int userBufferTimeInSeconds = setting.getBufferTime();

            LocalTime alertTime = TimeCalculation.calculateAlertTime(
                userArrivalTime,
                routeDurationInSeconds,
                userBufferTimeInSeconds
            );

            if (isAlertTime(currentUserTimeZone, alertTime)) {
                LocalTime departureTime = userArrivalTime.minusMinutes(routeDurationInSeconds);
                User user = userSetting.getUser();
                notificationService.sendNotification(
                    user.getUsername(),
                    user.getChatId(),
                    userArrivalTime,
                    departureTime
                );
            }
        }
    }

    private boolean isWeekdayForUserNow(ZonedDateTime currentUserTimeZone) {
        DayOfWeek dayOfWeek = currentUserTimeZone.getDayOfWeek();
        return dayOfWeek.getValue() >= DayOfWeek.MONDAY.getValue()
               && dayOfWeek.getValue() <= DayOfWeek.FRIDAY.getValue();
    }

    private boolean isAlertTime(ZonedDateTime currentUserTimeZone, LocalTime alertTime) {
        return currentUserTimeZone.toLocalTime().getHour() == alertTime.getHour() &&
               currentUserTimeZone.toLocalTime().getMinute() == alertTime.getMinute();
    }

    private int getRouteDurationInSeconds(Location fromLocation, Location toLocation) {
        Point startPoint = getPoint(fromLocation);
        startPoint.setStart(true);
        Point endPoint = getPoint(toLocation);
        return twoGisService.getRouteDurationInSeconds(startPoint, endPoint);
    }

    private Point getPoint(Location toLocation) {
        Point endPoint = new Point();
        endPoint.setLat(toLocation.getLatitude());
        endPoint.setLon(toLocation.getLongitude());
        endPoint.setType("stop");
        return endPoint;
    }
}
