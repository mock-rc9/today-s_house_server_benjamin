package com.example.demo.src.s3file;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

//이하 user거 가져옴
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.s3file.model.*;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.amazonaws.services.s3.AmazonS3Client;

import java.io.IOException;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

@RestController
@RequestMapping("/s3")
@RequiredArgsConstructor //Lombok라이브러리로, final 필드에 대한 생성자를 생성해준다.

public class FileUploadController {

    @Autowired
    private final FileUploadService fileUploadService;    

    @PostMapping("/ad/upload")
    public String upload(@RequestParam("image")MultipartFile multipartFile) throws IOException {
        fileUploadService.upload(multipartFile, "static");
        return "SUCCESS";
    }
    
}


