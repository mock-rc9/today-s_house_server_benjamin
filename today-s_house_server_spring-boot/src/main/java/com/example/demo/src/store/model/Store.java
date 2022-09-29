package com.example.demo.src.store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Store {
    private int itemId;
    private String nickname;
    private String email;
    private String phoneNumber;
    private String review;
}
