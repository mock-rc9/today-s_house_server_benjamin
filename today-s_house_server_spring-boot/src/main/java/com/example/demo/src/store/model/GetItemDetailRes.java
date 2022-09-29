package com.example.demo.src.store.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor

public class GetItemDetailRes {
    private String shippingCategory;
    private int scrapCnt;
    private int discountRate;
    private String couponName;
    private int basePriceCondition;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expDate;
    
}
