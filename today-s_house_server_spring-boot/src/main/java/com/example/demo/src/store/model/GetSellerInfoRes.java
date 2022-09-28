package com.example.demo.src.store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor


public class GetSellerInfoRes {
    private String name;
    private String header;
    private String address;
    private String customerServiceNumber;
    private String email;
    private String companyRegistrationNumber;
}
