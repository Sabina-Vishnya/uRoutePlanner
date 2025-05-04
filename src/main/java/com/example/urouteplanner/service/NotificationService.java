package com.example.urouteplanner.service;

import com.example.urouteplanner.persistence.integration.telegram.TelegramBotClient;
import com.example.urouteplanner.utility.Constant;
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

    public void sendNotification(String userName, Long chatId, LocalTime userArrivalTime, LocalTime departureTime) {
        try {
            String message = String.format("Напоминаю о выезде! Чтобы прибыть к %s, нужно выехать в %s",
                userArrivalTime.format(Constant.TIME_FORMATTER),
                departureTime.format(Constant.TIME_FORMATTER)
            );
            telegramBotClient.sendMessage(botToken, chatId, message);
            log.info("Уведомление отправлено для пользователя: {} в чат: {}", userName, chatId);
        } catch (Exception e) {
            log.error("Ошибка при отправке уведомления для пользователя: {} в чат: {}, {}", userName, chatId, e.getMessage());
        }
    }
}
