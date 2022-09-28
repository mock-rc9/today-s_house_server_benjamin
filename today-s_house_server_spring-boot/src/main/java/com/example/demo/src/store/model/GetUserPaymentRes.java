package com.example.demo.src.store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor



public class GetUserPaymentRes {

    private String title;
    private String address;
    private String recipient;
    private String phoneNumber;
    private String ordererNickname;
    private String ordererEmail;
    private String ordererPhone;
    private int points;
}
