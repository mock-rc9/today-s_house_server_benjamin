package com.example.demo.src.store.model;

import java.security.Timestamp;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;

@ToString
@Getter
@Setter
@AllArgsConstructor


public class GetReviewRes {
    private String nickname;
    private int starRating;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;

    private String optional;
    private String writing;
}
