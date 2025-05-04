package com.example.urouteplanner.persistence.integration.telegram;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "telegramBotClient", url = "https://api.telegram.org")
public interface TelegramBotClient {

    @PostMapping("/bot{token}/sendMessage")
    void sendMessage(
        @PathVariable("token") String token,
        @RequestParam("chat_id") Long chatId,
        @RequestParam("text") String text
    );
}
