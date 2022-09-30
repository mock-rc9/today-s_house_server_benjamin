package com.example.demo.src.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.user.model.*;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

@RestController
@RequestMapping("/app/users")
public class UserController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final UserProvider userProvider;
    @Autowired
    private final UserService userService;
    @Autowired
    private final JwtService jwtService;




    public UserController(UserProvider userProvider, UserService userService, JwtService jwtService){
        this.userProvider = userProvider;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    /**
     * 회원 조회 API
     * [GET] /users
     * 회원 번호 및 이메일 검색 조회 API
     * [GET] /users? Email=
     * @return BaseResponse<List<GetUserRes>>
     */
    //Query String
    @ResponseBody
    @GetMapping("") // (GET) 127.0.0.1:9000/app/users
    public BaseResponse<List<GetUserRes>> getUsers(@RequestParam(required = false) String Email) {
        try{
            if(Email == null){
                List<GetUserRes> getUsersRes = userProvider.getUsers();
                return new BaseResponse<>(SUCCESS ,getUsersRes);
            }
            // Get Users
            List<GetUserRes> getUsersRes = userProvider.getUsersByEmail(Email);
            return new BaseResponse<>( SUCCESS,getUsersRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()), null);
        }
    }

    /**
     * 회원 1명 조회 API
     * [GET] /users/:userIdx
     * @return BaseResponse<GetUserRes>
     */
    // Path-variable
    @ResponseBody
    @GetMapping("/{userIdx}") // (GET) 127.0.0.1:9000/app/users/:userIdx
    public BaseResponse<GetUserRes> getUser(@PathVariable("userIdx") int userIdx) {
        // Get Users
        try{
            GetUserRes getUserRes = userProvider.getUser(userIdx);
            return new BaseResponse<>(SUCCESS, getUserRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()), null);
        }

    }

    /**
     * 회원가입 API
     * [POST] /users
     * @return BaseResponse<PostUserRes>
     */
    // Body
    @ResponseBody
    @PostMapping("/sign-up")
    public BaseResponse<PostUserRes> createUser(@RequestBody PostUserReq postUserReq) {
        // TODO: email 관련한 짧은 validation 예시입니다. 그 외 더 부가적으로 추가해주세요!
        if(postUserReq.getEMAIL() == null || postUserReq.getEMAIL() == ""){
            return new BaseResponse<>(POST_USERS_EMPTY_EMAIL, new PostUserRes("",0));
        }
        if(postUserReq.getPASSWORD() == null || postUserReq.getPASSWORD() == ""){
            return new BaseResponse<>(POST_USERS_EMPTY_PASSWORD,new PostUserRes("",0));
        }
        if(postUserReq.getNICKNAME() == null || postUserReq.getNICKNAME() == ""){
            return new BaseResponse<>(POST_USERS_EMPTY_NICKNAME,new PostUserRes("",0));
        }
        
        try{
            PostUserRes postUserRes = userService.createUser(postUserReq);
            return new BaseResponse<>(SUCCESS, postUserRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()), new PostUserRes("",0));
        }
    }
    /**
     * 로그인 API
     * [POST] /users/login
     * @return BaseResponse<PostLoginRes>
     */
    @ResponseBody
    @PostMapping("/login")
    public BaseResponse<PostLoginRes> login(@RequestBody PostLoginReq postLoginReq){
        if(postLoginReq.getEMAIL() == null || postLoginReq.getEMAIL() == ""){
            return new BaseResponse<>(POST_USERS_EMPTY_EMAIL, new PostLoginRes(0,""));
        }
        if(postLoginReq.getPASSWORD() == null || postLoginReq.getPASSWORD() == ""){
            return new BaseResponse<>(POST_USERS_EMPTY_PASSWORD, new PostLoginRes(0,""));
        }
        try{

            // TODO: 로그인 값들에 대한 형식적인 validatin 처리해주셔야합니다!
            // TODO: 유저의 status ex) 비활성화된 유저, 탈퇴한 유저 등을 관리해주고 있다면 해당 부분에 대한 validation 처리도 해주셔야합니다.
            PostLoginRes postLoginRes = userProvider.login(postLoginReq);
            return new BaseResponse<>(SUCCESS, postLoginRes);
        } catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()), new PostLoginRes(0,""));
        }
    }

    /**
     * 유저정보변경 API
     * [PATCH] /users/:userIdx
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PatchMapping("/{userIdx}")
    public BaseResponse<String> modifyUserName(@PathVariable("userIdx") int userIdx, @RequestBody User user){
        try {
            //jwt에서 idx 추출.
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            if(userIdx != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT,null);
            }
            //같다면 유저네임 변경
            //PatchUserReq patchUserReq = new PatchUserReq(userIdx,user.getUserName());
            //userService.modifyUserName(patchUserReq);

            String result = "";
            return new BaseResponse<>(SUCCESS ,result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()), null);
        }
    }

    /**
     * 팔로우 API
     * [POST] /app/users/follow
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PostMapping("/follow")
    public BaseResponse<String> addFollow(@RequestBody Follow follow){
        try{
            //jwt에서 idx 추출.
            int userIdx = jwtService.getUserIdx();

            PostFollowReq postFollowReq = new PostFollowReq(userIdx, follow.getFollowedId());

            userService.addFollow(postFollowReq);
 
            String result = "*FOLLOW*";
            return new BaseResponse<>(SUCCESS ,result);
        } catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()), null);
        }
    }

    /**
     * 팔로우 취소 API
     * [DELETE] /app/users/cancel/follow
     * @return BaseResponse<String>
     */
    @ResponseBody
    @DeleteMapping("/cancel/follow")
    public BaseResponse<String> deleteFollow(@RequestBody Follow follow){
        try{
            //jwt에서 idx 추출.
            int userIdxByJwt = jwtService.getUserIdx();

            DeleteFollowReq deleteFollowReq = new DeleteFollowReq(userIdxByJwt, follow.getUnFollowedId());

            userService.deleteFollow(deleteFollowReq);
 
            String result = "*UNFOLLOW*";
            return new BaseResponse<>(SUCCESS ,result);
        } catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()), null);
        }
    }

    /** 
    * 프로필사진 수정 API
    * [PATCH] /app/users/modify-profile/:user-idx
    * @return BaseResponse<String>
    */
    // Path-variable
    @ResponseBody
    @PatchMapping("/modify-profile/{user-idx}")
    public BaseResponse<String> modifyProfile(@PathVariable("user-idx") int userIdx, @RequestBody UserInfo userMyPage){
        try {
            //jwt에서 idx 추출.
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            if(userIdx != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT,null);
            }
            //같다면 프로필사진 수정
            PatchProfileReq patchProfileReq = new PatchProfileReq(userIdx, userMyPage.getProfile());
            userService.modifyProfile(patchProfileReq);
 
            String result = "*프로필 사진을 수정하였습니다*";
            return new BaseResponse<>(SUCCESS ,result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()), null);
        }
    }

    /** 
    * 회원등급 업 API
    * [PATCH] /app/users/levelUp
    * @return BaseResponse<String>
    */
    // Path-variable
    @ResponseBody
    @PatchMapping("/levelUp")
    public BaseResponse<String> levelUp(){
        try {

            //jwt에서 idx 추출.
            int userIdxByJwt = jwtService.getUserIdx();
            userService.levelUp(userIdxByJwt);
 
            String result = "*회원등급을 VIP로 업그레이드하였습니다*";
            return new BaseResponse<>(SUCCESS ,result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()), null);
        }
    }

    /** 
    * 회원등급 다운 API
    * [PATCH] /app/users/levelDown
    * @return BaseResponse<String>
    */
    // Path-variable
    @ResponseBody
    @PatchMapping("/levelDown")
    public BaseResponse<String> levelDown(){
        try {
            //jwt에서 idx 추출.
            int userIdx = jwtService.getUserIdx();
            userService.levelDown(userIdx);
 
            String result = "*회원등급을 WELCOME으로 다운그레이드하였습니다*";
            return new BaseResponse<>(SUCCESS ,result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()), null);
        }
    }

    /** 
    * 배경사진 수정 API
    * [PATCH] /app/users/modify-back/:user-idx
    * @return BaseResponse<String>
    */
    // Path-variable
    @ResponseBody
    @PatchMapping("/modify-back/{user-idx}")
    public BaseResponse<String> patchBackImage(@PathVariable("user-idx") int userIdx, @RequestBody UserInfo userInfo){
        try {

            //jwt에서 idx 추출.
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            if(userIdx != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT,null);
            }
        //같다면 배경사진 수정
            PatchBackReq patchBackReq = new PatchBackReq(userIdx, userInfo.getBackgroundImage());
            
            userService.patchBackImage(patchBackReq);
 
            String result = "*배경사진을 변경하였습니다*";
            return new BaseResponse<>(SUCCESS ,result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()), null);
        }
    }

    /** 
    * MY URL 수정 API
    * [PATCH] /app/users/modify-url/:user-idx
    * @return BaseResponse<String>
    */
    // Path-variable
    @ResponseBody
    @PatchMapping("/modify-url/{user-idx}")
    public BaseResponse<String> patchUrl(@PathVariable("user-idx") int userIdx, @RequestBody UserInfo userInfo){
        try {
            //jwt에서 idx 추출.
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            if(userIdx != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT,null);
            }
            //같다면 MY URL 수정
            PatchMyUrlReq patchMyUrlReq = new PatchMyUrlReq(userIdx, userInfo.getMyUrl());
            userService.patchUrl(patchMyUrlReq);
 
            String result = "*MY URL을 수정하였습니다*";
            return new BaseResponse<>(SUCCESS ,result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()), null);
        }
    }

    /** 
    * 한 줄 소개 수정 API
    * [PATCH] /app/users/modify-introduction/:user-idx
    * @return BaseResponse<String>
    */
    // Path-variable
    @ResponseBody
    @PatchMapping("/modify-introduction/{user-idx}")
    public BaseResponse<String> patchIntroduction(@PathVariable("user-idx") int userIdx, @RequestBody UserInfo userInfo ){
        try {
            //jwt에서 idx 추출.
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            if(userIdx != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT,null);
            }
            //같다면 프로필사진 수정
            PatchIntroductionReq patchIntroductionReq = new PatchIntroductionReq(userIdx, userInfo.getIntroduction());
            userService.patchIntroduction(patchIntroductionReq);
 
            String result = "*한 줄 소개를 수정하였습니다*";
            return new BaseResponse<>(SUCCESS ,result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()), null);
        }
    }

    /** 
    * 비밀번호 변경 API
    * [PATCH] /app/users/modify-pwd/:user-idx
    * @return BaseResponse<String>
    */
    // Path-variable
    @ResponseBody
    @PatchMapping("/modify-pwd/{user-idx}")
    public BaseResponse<String> patchPwd(@PathVariable("user-idx") int userIdx, @RequestBody UserInfo userInfo ){
        try {
            //jwt에서 idx 추출.
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            if(userIdx != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT,null);
            }
            //같다면 비밀번호 수정
            PatchPwdReq patchPwdReq = new PatchPwdReq(userIdx, userInfo.getPwd());
            userService.patchPwd(patchPwdReq);
 
            String result = "*비밀번호가 수정되었습니다*";
            return new BaseResponse<>(SUCCESS ,result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()), null);
        }
    }

    /**
     * 배송지 추가 API
     * [POST] /app/users/add/shipping-list
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PostMapping("/add/shipping-list")
    public BaseResponse<String> addShippingList(@RequestBody UserInfo userInfo){
        if(userInfo.getShippingName() == ""){
            return new BaseResponse<>(POST_SHIPPING_NAME_EMPTY, null);
        }
        if(userInfo.getRecipient() == ""){
            return new BaseResponse<>(POST_RECIPIENT_EMPTY, null);
        }
        if(userInfo.getPhonenumber() == ""){
            return new BaseResponse<>(POST_PHONENUMBER_EMPTY, null);
        }
        if(userInfo.getAddress() == ""){
            return new BaseResponse<>(POST_ADDRESS_EMPTY, null);
        }
        
        try{
            //jwt에서 idx 추출.
            int userIdxByJwt = jwtService.getUserIdx();

            PostShipAddressReq postShipAddressReq = new PostShipAddressReq(userIdxByJwt,userInfo.getShippingName(),userInfo.getRecipient(),userInfo.getPhonenumber(),userInfo.getAddress());

            userService.addShippingList(postShipAddressReq);
 
            String result = "*배송지가 추가되었습니다*";
            return new BaseResponse<>(SUCCESS ,result);
        } catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()), null);
        }
    }

    /**
     * 배송지 삭제 API
     * [DELETE] /app/users/delete/shipping-list
     * @return BaseResponse<String>
     */
    @ResponseBody
    @DeleteMapping("/delete/shipping-list")
    public BaseResponse<String> deleteShippingList(@RequestBody UserInfo userInfo){
        try{
            //jwt에서 idx 추출.
            int userIdxByJwt = jwtService.getUserIdx();

            DeleteShipAddressReq deleteShipAddressReq = new  DeleteShipAddressReq (userIdxByJwt, userInfo.getShipAddId());

            userService.deleteShippingList(deleteShipAddressReq);
 
            String result = "*배송지가 삭제되었습니다*";
            return new BaseResponse<>(SUCCESS ,result);
        } catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()), null);
        }
    }

    /**
     * 배송지 조회 API
     * [GET] /app/users/shipping-list
     * @return BaseResponse<List<GetShippingListRes>>
     */
    // Path-variable
    @ResponseBody
    @GetMapping("/shipping-list") 
    public BaseResponse<List<GetShippingListRes>> getShippingList() {
        // Get Shipping List
        try{
             //jwt에서 idx 추출.
             int userIdxByJwt = jwtService.getUserIdx();
            List<GetShippingListRes> getShippingListRes = userProvider.getShippingList(userIdxByJwt);
            return new BaseResponse<>(SUCCESS, getShippingListRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()), null);
        }

    }

    /**
     * 포인트 획득 API
     * [PATCH] /app/users/plus-point
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PatchMapping("/plus-point")
    public BaseResponse<String> addPoints(@RequestBody UserInfo userInfo ){
        try{
            //jwt에서 idx 추출.
            int userIdx = jwtService.getUserIdx();

            PostAddPointsReq postAddPointsReq= new PostAddPointsReq(userIdx, userInfo.getPoints());

            userService.addPoints(postAddPointsReq);
 
            String result = "*포인트를 획득하였습니다*";
            return new BaseResponse<>(SUCCESS ,result);
        } catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()), null);
        }
    }

    /**
     * 포인트 차감 API
     * [PATCH] /app/users/minus-point
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PatchMapping("/minus-point")
    public BaseResponse<String> minusPoints(@RequestBody UserInfo userInfo ){
        try{
            //jwt에서 idx 추출.
            int userIdx = jwtService.getUserIdx();

            PostMinusPointsReq postMinusPointsReq = new PostMinusPointsReq(userIdx, userInfo.getPoints());

            userService.minusPoints(postMinusPointsReq);
 
            String result = "*포인트를 차감하였습니다*";
            return new BaseResponse<>(SUCCESS ,result);
        } catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()), null);
        }
    }

    /**
     * 알림 ON API
     * [POST] /app/users/alarm/on
     * @return BaseResponse<String>
     */
    
    @ResponseBody
    @PostMapping("/alarm/on")
    public BaseResponse<String> postAlarm(@RequestBody UserInfo userInfo){
        try {
            //jwt에서 idx 추출.
            int userIdxByJwt = jwtService.getUserIdx();
        
            PostAlarmReq postAlarmReq = new PostAlarmReq(userIdxByJwt, userInfo.getAlarmId());
            userService.postAlarm(postAlarmReq);

            String result = "*알람 ON*";
            return new BaseResponse<>(SUCCESS ,result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()), null);
        }
    }

    /**
     * 알림 OFF API
     * [DELETE] /app/users/alarm/off
     * @return BaseResponse<String>
     */
    
    @ResponseBody
    @DeleteMapping("/alarm/off")
    public BaseResponse<String> deleteAlarm(@RequestBody UserInfo userInfo){
        try {
            //jwt에서 idx 추출.
            int userIdxByJwt = jwtService.getUserIdx();
        
            PostAlarmReq postAlarmReq = new PostAlarmReq(userIdxByJwt, userInfo.getAlarmId());
            userService.deleteAlarm(postAlarmReq);

            String result = "*알람 OFF*";
            return new BaseResponse<>(SUCCESS ,result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()), null);
        }
    }

     /**
     * 자동재생 설정 변경 API
     * [PATCH] /app/users/auto-play
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PatchMapping("/auto-play")
    public BaseResponse<String> patchAutoPlay(@RequestBody UserInfo userInfo){
        try{
            //jwt에서 idx 추출.
            int userIdx = jwtService.getUserIdx();

            PatchPalySetReq  patchPalySetReq  = new PatchPalySetReq (userIdx, userInfo.getAutoPlayId());

            userService.patchAutoPlay(patchPalySetReq );
 
            String result = "*자동 재생 설정을 변경하였습니다*";
            return new BaseResponse<>(SUCCESS ,result);
        } catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()), null);
        }
    }
    



}
