package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class GetShippingListRes {
    private int shipListId;
    private String shipListName;
    private String address;
    private String recipient;
    private String phonenumber;
    private int basic;
    
}
