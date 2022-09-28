package com.example.demo.src.store.model;

import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;

@ToString
@Getter
@Setter
@AllArgsConstructor

public class GetItemPaymentRes {
    private String companyName;
    private int deliveryFee;
    private String itemName;
    private String couponName;
    private int basePriceCondition;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expDate;

    
}
