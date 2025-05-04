package com.example.urouteplanner.persistence.repository;

import com.example.urouteplanner.persistence.entity.UserSetting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSettingRepository extends JpaRepository<UserSetting, Integer> {

    boolean existsByUser_Id(Long userId);
}