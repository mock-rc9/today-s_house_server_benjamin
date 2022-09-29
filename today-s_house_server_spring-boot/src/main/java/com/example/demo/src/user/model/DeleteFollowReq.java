package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class DeleteFollowReq {
    private int userIdxByJwt;
    private int unfollowedId;
    
}
