package com.example.demo.src.store.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostReviewReq {
    private int itemId;
    private int userIdxByJwt;
    private int colorId;
    private int starRating;
    private String contents;
    private String image; 
}
