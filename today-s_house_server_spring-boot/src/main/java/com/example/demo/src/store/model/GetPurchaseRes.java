package com.example.demo.src.store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class GetPurchaseRes {
    private String optional;
    private String additional;
    private int addPrice;
    
}
