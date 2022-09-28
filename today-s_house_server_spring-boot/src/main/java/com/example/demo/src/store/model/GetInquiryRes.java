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

public class GetInquiryRes {
    private String nickname;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;
    private String question;
    private String answer;
    private int progress;
    private int sercret;
    private String type;
    
}
