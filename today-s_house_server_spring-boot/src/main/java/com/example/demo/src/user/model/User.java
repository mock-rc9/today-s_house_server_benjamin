package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private int USER_IDX;
    private String EMAIL;
    private String PASSWORD;
    private String NICKNAME;
    private String MEMBERSHIP_LEVEL;
    private int POINTS;
    //private String PHONE_NUMBER;
    //private String ADDRESS;
    //private String PROFILE_URL;
    
}
