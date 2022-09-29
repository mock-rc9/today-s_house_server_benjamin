package com.example.demo.src.user.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class PostShipAddressReq {
    private int userIdx;
    private String shippingName;
    private String recipient;
    private String phonenumber;
    private String address;
    
}
