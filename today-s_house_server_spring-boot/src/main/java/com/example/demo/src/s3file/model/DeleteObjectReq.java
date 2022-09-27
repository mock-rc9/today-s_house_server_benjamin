package com.example.demo.src.s3file.model;

import java.io.InputStream;
import com.amazonaws.services.s3.model.ObjectMetadata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class DeleteObjectReq {
    private String bucket;
    private String fileName; 
    
}
