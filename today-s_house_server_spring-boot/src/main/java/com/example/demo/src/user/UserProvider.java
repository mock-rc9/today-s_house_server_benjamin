package com.example.demo.src.user;


import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.user.model.*;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

//Provider : Read의 비즈니스 로직 처리
@Service
public class UserProvider {

    private final UserDao userDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UserProvider(UserDao userDao, JwtService jwtService) {
        this.userDao = userDao;
        this.jwtService = jwtService;
    }

    public List<GetUserRes> getUsers() throws BaseException{
        try{
            List<GetUserRes> getUserRes = userDao.getUsers();
            return getUserRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetUserRes> getUsersByEmail(String email) throws BaseException{
        try{
            List<GetUserRes> getUsersRes = userDao.getUsersByEmail(email);
            return getUsersRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
                    }


    public GetUserRes getUser(int userIdx) throws BaseException {
        try {
            GetUserRes getUserRes = userDao.getUser(userIdx);
            return getUserRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public int checkEmail(String email) throws BaseException{
        try{
            return userDao.checkEmail(email);
        } catch (Exception exception){
            throw new BaseException(CHECK_EXISTS_EMAIL);
        }
    }

    public int checkNickname(String nickname) throws BaseException{
        try{
            return userDao.checkNickname(nickname);
        } catch (Exception exception){
            throw new BaseException(CHECK_EXISTS_NICKNAME);
        }
    }



    public PostLoginRes login(PostLoginReq postLoginReq) throws BaseException{
        //가입하지 않은 회원
        /* 
        if(userDao.checkEmail(postLoginReq.getEMAIL()) == 0){
            throw new BaseException(FAILED_TO_LOGIN);
        }
        */
        int valid = userDao.checkEmail(postLoginReq.getEMAIL());
        if (valid == 1) {

            User user = userDao.getUserInfo(postLoginReq);
            String encryptPwd;
            try {
                encryptPwd=new SHA256().encrypt(postLoginReq.getPASSWORD());
            } catch (Exception ignored) {
                throw new BaseException(PASSWORD_ENCRYPTION_ERROR); 
            }

            if(user.getPASSWORD().equals(encryptPwd)){
                int userIdx = user.getUSER_IDX();
                String jwt = jwtService.createJwt(userIdx);
                return new PostLoginRes(userIdx,jwt);
            }
            else{
                throw new BaseException(FAILED_TO_LOGIN);
            }
        }
        else {throw new BaseException(FAILED_TO_LOGIN);  }        

    }

    public List<GetShippingListRes> getShippingList(int userIdx) throws BaseException {
        try {
            List<GetShippingListRes> getShippingListRes = userDao.getShippingList(userIdx);
            return getShippingListRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
