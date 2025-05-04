package com.example.urouteplanner.service;

import com.example.urouteplanner.persistence.integration.telegram.TelegramBotClient;
import com.example.urouteplanner.utility.Constant;
import com.example.urouteplanner.utility.Formatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {
    private final TelegramBotClient telegramBotClient;

    @Value("${bot.token}")
    private String botToken;

    public void sendNotification(String userName,
                                 Long chatId,
                                 LocalTime userArrivalTime,
                                 LocalTime departureTime,
                                 int routeDurationInSeconds) {
        try {
            String message = String.format("Напоминаю о выезде! Чтобы прибыть к %s, нужно выехать в %s" +
                                           "\nВремя в пути составит %s",
                userArrivalTime.format(Constant.TIME_FORMATTER),
                departureTime.format(Constant.TIME_FORMATTER),
                Formatter.formatRouteDuration(routeDurationInSeconds)
            );
            telegramBotClient.sendMessage(botToken, chatId, message);
            log.info("Уведомление отправлено для пользователя: {} в чат: {}", userName, chatId);
        } catch (Exception e) {
            log.error("Ошибка при отправке уведомления для пользователя: {} в чат: {}, {}", userName, chatId, e.getMessage());
        }
    }
}
