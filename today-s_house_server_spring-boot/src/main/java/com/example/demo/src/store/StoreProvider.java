package com.example.demo.src.store;


import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.store.model.*;
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
public class StoreProvider {

    private final StoreDao storeDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public StoreProvider(StoreDao storeDao, JwtService jwtService) {
        this.storeDao = storeDao;
        this.jwtService = jwtService;
    }



    /* 
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
    */


    public List<GetStoreHomeRes> getStoreHome() throws BaseException {
        try {
            List<GetStoreHomeRes> getStoreHomeRes = storeDao.getStoreHome();
            return getStoreHomeRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetReviewRes> getReview(int itemId) throws BaseException {
        try {
            List<GetReviewRes> getReviewRes = storeDao.getReview(itemId);
            return getReviewRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public GetSellerInfoRes getSellerInfo(int itemId) throws BaseException {
        try {
            GetSellerInfoRes getSellerInfoRes = storeDao.getSellerInfo(itemId);
            return getSellerInfoRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetInquiryRes> getInquiry(int itemId) throws BaseException {
        try {
            List<GetInquiryRes> getInquiryRes = storeDao.getInquiry(itemId);
            return getInquiryRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetPurchaseRes> getPurchase(int itemId) throws BaseException {
        try {
            List<GetPurchaseRes> getPurchaseRes = storeDao.getPurchase(itemId);
            return getPurchaseRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public GetUserPaymentRes getUserPayment(int userIdx) throws BaseException {
        try {
            GetUserPaymentRes getUserPaymentRes = storeDao.getUserPayment(userIdx);
            return getUserPaymentRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public GetItemPaymentRes getItemPayment(int itemId) throws BaseException {
        try {
            GetItemPaymentRes getItemPaymentRes = storeDao.getItemPayment(itemId);
            return getItemPaymentRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public GetItemDetailRes getItemDetail(int itemId) throws BaseException {
        try {
            GetItemDetailRes getItemDetailRes = storeDao.getItemDetail(itemId);
            return getItemDetailRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetItemInfoTapRes> getItemInfoTap(int itemId) throws BaseException {
        try {
            List<GetItemInfoTapRes> getItemInfoTapRes = storeDao.getItemInfoTap(itemId);
            return getItemInfoTapRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetStoreHomeAdRes> getStoreHomeAd() throws BaseException {
        try {
            List<GetStoreHomeAdRes> getStoreHomeAdRes = storeDao.getStoreHomeAd();
            return getStoreHomeAdRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetItemScrapBookRes> getItemScrapBook(int userIdx) throws BaseException {
        try {
            List<GetItemScrapBookRes> getItemScrapBookRes = storeDao.getItemScrapBook(userIdx);
            return getItemScrapBookRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
    

    /* 
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

    */



    //public PostLoginRes login(PostLoginReq postLoginReq) throws BaseException{
        //가입하지 않은 회원
        /* 
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
    */

}
