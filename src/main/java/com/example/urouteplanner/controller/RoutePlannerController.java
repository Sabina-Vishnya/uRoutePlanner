package com.example.urouteplanner.controller;

import com.example.urouteplanner.service.UserSettingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RoutePlannerController {

    private final UserSettingsService userSettingsService;

    @PostMapping("/setting")
    public ResponseEntity<Void> processUserSetting(@RequestBody UserSettingRequest request) {
        userSettingsService.createUserSettings(request);
        return ResponseEntity.ok().build();
    }
}
