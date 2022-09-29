package com.example.demo.src.store.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class ScrapItemReq {
    private int userIdxByJwt;
    private int itemId;
    
}
