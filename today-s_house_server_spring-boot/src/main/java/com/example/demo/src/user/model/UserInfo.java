package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserInfo {

    private String profile;
    private String myUrl;
    private String introduction;
    private String backgroundImage;
    private String pwd;
    private String shippingName;
    private String recipient;
    private String phonenumber;
    private String address;
    private int shipAddId;

}