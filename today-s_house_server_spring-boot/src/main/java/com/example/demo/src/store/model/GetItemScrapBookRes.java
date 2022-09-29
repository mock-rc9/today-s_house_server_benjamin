package com.example.demo.src.store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class GetItemScrapBookRes {
    private int id;
    private String shopName;
    private String itemName;
    private int price;
    private int discountRate;
    private int reviewCnt;
    private float starAvg;
    private int deliveryFee;
    private int specialPrice;
    
}
