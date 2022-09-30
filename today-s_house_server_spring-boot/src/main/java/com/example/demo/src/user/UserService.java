package com.example.demo.src.user;



import com.example.demo.config.BaseException;
import com.example.demo.src.user.model.*;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

// Service Create, Update, Delete 의 로직 처리
@Service
public class UserService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserDao userDao;
    private final UserProvider userProvider;
    private final JwtService jwtService;


    @Autowired
    public UserService(UserDao userDao, UserProvider userProvider, JwtService jwtService) {
        this.userDao = userDao;
        this.userProvider = userProvider;
        this.jwtService = jwtService;

    }

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

    public void addFollow(PostFollowReq postFollowReq) throws BaseException {
        try{
            int result = userDao.addFollow(postFollowReq);
            if(result == 0){
                throw new BaseException(ADD_FOLLOW_FAIL);
            }
        } catch(Exception exception){
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void deleteFollow(DeleteFollowReq deleteFollowReq) throws BaseException {
        try{
            int result = userDao.deleteFollow(deleteFollowReq);
            if(result == 0){
                throw new BaseException(DELETE_FOLLOW_FAIL);
            }
        } catch(Exception exception){
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void modifyProfile(PatchProfileReq patchProfileReq) throws BaseException {
        try{
            int result = userDao.modifyProfile(patchProfileReq);
            if(result == 0){
                throw new BaseException(MODIFY_FAIL_PROFILE);
            }
        } catch(Exception exception){
            //exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void levelUp(PatchLevelReq patchLevelReq) throws BaseException {
        try{
            int result = userDao.levelUp(patchLevelReq);
            if(result == 0){
                throw new BaseException(MODIFY_FAIL_LEVEL);
            }
        } catch(Exception exception){
            //exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void levelDown(PatchLevelReq patchLevelReq) throws BaseException {
        try{
            int result = userDao.levelDown(patchLevelReq);
            if(result == 0){
                throw new BaseException(MODIFY_FAIL_LEVEL);
            }
        } catch(Exception exception){
            //exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }
    public void patchBackImage(PatchBackReq patchBackReq) throws BaseException {
        try{
            int result = userDao.patchBackImage(patchBackReq);
            if(result == 0){
                throw new BaseException(MODIFY_FAIL_BACKGROUNDIMAGE);
            }
        } catch(Exception exception){
            //exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void patchUrl(PatchMyUrlReq patchMyUrlReq) throws BaseException {
        try{
            int result = userDao.patchUrl(patchMyUrlReq);
            if(result == 0){
                throw new BaseException(MODIFY_FAIL_MYURL);
            }
        } catch(Exception exception){
            //exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void patchIntroduction(PatchIntroductionReq patchIntroductionReq ) throws BaseException {
        try{
            int result = userDao.patchIntroduction(patchIntroductionReq);
            if(result == 0){
                throw new BaseException(MODIFY_FAIL_INTRODUCTION);
            }
        } catch(Exception exception){
            //exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void patchPwd(PatchPwdReq patchPwdReq) throws BaseException {
        String encPwd;
        try{
            //암호화
            encPwd = new SHA256().encrypt(patchPwdReq.getPwd());
            patchPwdReq.setPwd(encPwd);

        } catch (Exception ignored) {
            throw new BaseException(PASSWORD_ENCRYPTION_ERROR);
        }
        try{
            int result = userDao.patchPwd(patchPwdReq);
            if(result == 0){
                throw new BaseException(MODIFY_FAIL_PASSWORD);
            }
        } catch(Exception exception){
            //exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void addShippingList(PostShipAddressReq postShipAddressReq) throws BaseException {
        try{
            int result = userDao.addShippingList(postShipAddressReq);
            if(result == 0){
                throw new BaseException(ADD_SHIPPING_LIST_FAIL);
            }
        } catch(Exception exception){
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void deleteShippingList(DeleteShipAddressReq deleteShipAddressReq ) throws BaseException {
        try{
            int result = userDao.deleteShippingList(deleteShipAddressReq );
            if(result == 0){
                throw new BaseException(DELETE_SHIPPING_LIST_FAIL);
            }
        } catch(Exception exception){
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void addPoints(PostAddPointsReq postAddPointsReq) throws BaseException {
        try{
            int result = userDao.addPoints(postAddPointsReq);
            if(result == 0){
                throw new BaseException(ADD_POINTS_FAIL);
            }
        } catch(Exception exception){
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void minusPoints(PostMinusPointsReq postMinusPointsReq) throws BaseException {
        try{
            int result = userDao.minusPoints(postMinusPointsReq);
            if(result == 0){
                throw new BaseException(MINUS_POINTS_FAIL);
            }
        } catch(Exception exception){
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void postAlarm(PostAlarmReq postAlarmReq) throws BaseException {
        try{
            int result = userDao.postAlarm(postAlarmReq);
            if(result == 0){
                throw new BaseException(ALARM_SETTING_FAIL);
            }
        } catch(Exception exception){
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void deleteAlarm(PostAlarmReq postAlarmReq) throws BaseException {
        try{
            int result = userDao.deleteAlarm(postAlarmReq);
            if(result == 0){
                throw new BaseException(ALARM_SETTING_FAIL);
            }
        } catch(Exception exception){
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void patchAutoPlay(PatchPalySetReq  patchPalySetReq) throws BaseException {
        try{
            int result = userDao.patchAutoPlay(patchPalySetReq);
            if(result == 0){
                throw new BaseException(AUTO_PLAY_SETTING_FAIL);
            }
        } catch(Exception exception){
            exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }


/* 
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
