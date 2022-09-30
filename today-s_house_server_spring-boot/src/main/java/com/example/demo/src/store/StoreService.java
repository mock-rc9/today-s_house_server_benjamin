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

import static com.example.demo.config.BaseResponseStatus.*;

// Service Create, Update, Delete 의 로직 처리
@Service
public class StoreService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final StoreDao storeDao;
    private final StoreProvider storeProvider;
    private final JwtService jwtService;


    @Autowired
    public StoreService(StoreDao storeDao, StoreProvider storeProvider, JwtService jwtService) {
        this.storeDao = storeDao;
        this.storeProvider = storeProvider;
        this.jwtService = jwtService;
    }


    public void modifyUserNick(PatchUserNicknameReq patchUserNicknameReq) throws BaseException {
        try{
            int result = storeDao.modifyUserNick(patchUserNicknameReq);
            if(result == 0){
                throw new BaseException(MODIFY_FAIL_USER_NICKNAME);
            }
        } catch(Exception exception){
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void modifyUserEmail(PatchUserEmailReq patchUserEmailReq) throws BaseException {
        try{
            int result = storeDao.modifyUserEmail(patchUserEmailReq);
            if(result == 0){
                throw new BaseException(MODIFY_FAIL_USER_EMAIL);
            }
        } catch(Exception exception){
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void modifyUserPhone(PatchUserPhonenumberReq patchUserPhonenumberReq) throws BaseException {
        try{
            int result = storeDao.modifyUserPhone(patchUserPhonenumberReq);
            if(result == 0){
                throw new BaseException(MODIFY_FAIL_USER_PHONENUMBER);
            }
        } catch(Exception exception){
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void addBasket(PostAddBasketReq postAddBasketReq) throws BaseException {
        try{
            int result = storeDao.addBasket(postAddBasketReq);
            if(result == 0){
                throw new BaseException(ADD_BASKET_FAIL);
            }
        } catch(Exception exception){
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void deleteBasket(DeleteItemBasketReq deleteItemBasketReq) throws BaseException {
        try{
            int result = storeDao.deleteBasket(deleteItemBasketReq);
            if(result == 0){
                throw new BaseException(DELETE_BASKET_FAIL);
            }
        } catch(Exception exception){
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void addReview(PostReviewReq postReviewReq) throws BaseException {
        try{
            int result = storeDao.addReview(postReviewReq);
            if(result == 0){
                throw new BaseException(ADD_REVIEW_FAIL);
            }
        } catch(Exception exception){
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void deleteReview(int reviewId) throws BaseException {
        try{
            int result = storeDao.deleteReview(reviewId);
            if(result == 0){
                throw new BaseException(DELETE_REVIEW_FAIL);
            }
        } catch(Exception exception){
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void addScrap(ScrapItemReq postAddScrapItemReq) throws BaseException {
        try{
            int result = storeDao.addScrap(postAddScrapItemReq);
            if(result == 0){
                throw new BaseException(ADD_SCRAP_FAIL);
            }
        } catch(Exception exception){
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void deleteScrap(ScrapItemReq deleteScrapItemReq) throws BaseException {
        try{
            int result = storeDao.deleteScrap(deleteScrapItemReq);
            if(result == 0){
                throw new BaseException(DELETE_SCRAP_FAIL);
            }
        } catch(Exception exception){
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void modifyReview(PatchReviewReq patchReviewReq) throws BaseException {
        try{
            int result = storeDao.modifyReview(patchReviewReq);
            if(result == 0){
                throw new BaseException(MODIFY_FAIL_REVIEW);
            }
        } catch(Exception exception){
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    
/* 
    //POST
    public PostUserRes createUser(PostUserReq postUserReq) throws BaseException {
        //중복
        if(userProvider.checkEmail(postUserReq.getEMAIL()) ==1){
            throw new BaseException(POST_USERS_EXISTS_EMAIL);
        }
        if(userProvider.checkNickname(postUserReq.getNICKNAME()) ==1){
            throw new BaseException(POST_USERS_EXISTS_NICKNAME);
        }

        String pwd;
        try{
            //암호화
            pwd = new SHA256().encrypt(postUserReq.getPASSWORD());
            postUserReq.setPASSWORD(pwd);

        } catch (Exception ignored) {
            throw new BaseException(PASSWORD_ENCRYPTION_ERROR);
        }
        
        try{
            int userIdx = userDao.createUser(postUserReq);
            //jwt 발급.
            String jwt = jwtService.createJwt(userIdx);
            return new PostUserRes(jwt,userIdx);
        } catch (Exception exception) {
            throw new BaseException(CREATE_USER_ERROR);
        }
    }

    public void modifyUserName(PatchUserReq patchUserReq) throws BaseException {
        try{
            int result = userDao.modifyUserName(patchUserReq);
            if(result == 0){
                throw new BaseException(MODIFY_FAIL_USERNAME);
            }
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    */
}
