package com.example.urouteplanner.persistence.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserSettingId implements Serializable {
    private long user;
    private int setting;
}