package com.example.demo.src.store.model;

import java.sql.Array;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class GetStoreHomeRes {
    private int id;
    private String shopName;
    private String itemName;
    private int price;
    private int discountRate;
    private int reviewCnt;
    private float starAvg;
    private int deliveryFee;
    private int specialPrice;
    private int mediumId;
}
