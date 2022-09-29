package com.example.demo.src.store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class PatchUserPhonenumberReq {
    private int userIdx;
    private String phonenumber;
    
}
