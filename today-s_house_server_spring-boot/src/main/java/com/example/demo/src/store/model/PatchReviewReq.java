package com.example.demo.src.store.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class PatchReviewReq {
    private int userIdx;
    private int reviewId;
    private String contents;
    
}
