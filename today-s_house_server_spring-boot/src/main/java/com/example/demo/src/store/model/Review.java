package com.example.demo.src.store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class Review {
    private int reviewId;
    private int itemId;
    private int colorId;
    private int starRating;
    private String contents;
    private String image; 
    
}
