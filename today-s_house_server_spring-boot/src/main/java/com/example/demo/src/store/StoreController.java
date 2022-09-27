package com.example.demo.src.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.store.model.*;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;


import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

@RestController
@RequestMapping("/app/stores")
public class StoreController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final StoreProvider storeProvider;
    @Autowired
    private final StoreService storeService;
    @Autowired
    private final JwtService jwtService;




    public StoreController(StoreProvider storeProvider, StoreService storeService, JwtService jwtService){
        this.storeProvider = storeProvider;
        this.storeService = storeService;
        this.jwtService = jwtService;
    }

    /**
     * 회원조회 API
     * [GET] /app/stores/home
     * 회원 번호 및 이메일 검색 조회 API
     * [GET] /users? Email=
     * @return BaseResponse<List<GetUserRes>>
     */
    /* 
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
    */

    /**
     * 스토어 홈 API
     * [GET] /app/stores/home
     * @return BaseResponse<GetStoreHomeRes>
     */
    // Path-variable
    @ResponseBody
    @GetMapping("/home") 
    public BaseResponse<List<GetStoreHomeRes>> getStoreHome() {
        // Get Users
        try{
            List<GetStoreHomeRes> getStoreHomeRes = storeProvider.getStoreHome();
            return new BaseResponse<>(SUCCESS, getStoreHomeRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()), null);
        }

    }

    /**
     * 상품 댓글 불러오기 API
     * [GET] /app/stores/review/:item-id
     * @return BaseResponse<GetReviewRes>
     */
    // Path-variable
    @ResponseBody
    @GetMapping("/review/{item-id}") 
    public BaseResponse<List<GetReviewRes>> getReview(@PathVariable("item-id") int itemId) {
        // Get Review
        try{
            List<GetReviewRes> GetReviewRes = storeProvider.getReview(itemId);
            return new BaseResponse<>(SUCCESS, GetReviewRes);
        } catch(BaseException exception){
            //exception.printStackTrace();
            return new BaseResponse<>((exception.getStatus()), null);
            //new GetReviewRes("",0, Date(0000-00-00) ,"","")
        }

    }

    

    /**
     * 회원가입 API
     * [POST] /users
     * @return BaseResponse<PostUserRes>
     */
    // Body
    /* 
    @ResponseBody
    @PostMapping("/sign-up")
    public BaseResponse<PostUserRes> createUser(@RequestBody PostUserReq postUserReq) {
        // TODO: email 관련한 짧은 validation 예시입니다. 그 외 더 부가적으로 추가해주세요!
        if(postUserReq.getEMAIL() == null || postUserReq.getEMAIL() == ""){
            return new BaseResponse<>(POST_USERS_EMPTY_EMAIL, null);
        }
        if(postUserReq.getPASSWORD() == null || postUserReq.getPASSWORD() == ""){
            return new BaseResponse<>(POST_USERS_EMPTY_PASSWORD,null);
        }
        if(postUserReq.getNICKNAME() == null || postUserReq.getNICKNAME() == ""){
            return new BaseResponse<>(POST_USERS_EMPTY_NICKNAME,null);
        }
        
        try{
            PostUserRes postUserRes = userService.createUser(postUserReq);
            return new BaseResponse<>(SUCCESS, postUserRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()), null);
        }
    }
    */


    /**
     * 로그인 API
     * [POST] /users/login
     * @return BaseResponse<PostLoginRes>
     */
    /* 
    @ResponseBody
    @PostMapping("/login")
    public BaseResponse<PostLoginRes> login(@RequestBody PostLoginReq postLoginReq){
        if(postLoginReq.getEMAIL() == null || postLoginReq.getEMAIL() == ""){
            return new BaseResponse<>(POST_USERS_EMPTY_EMAIL,new PostLoginRes(0,""));
        }
        if(postLoginReq.getPASSWORD() == null || postLoginReq.getPASSWORD() == ""){
            return new BaseResponse<>(POST_USERS_EMPTY_PASSWORD,new PostLoginRes(0,""));
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
    */

    /**
     * 유저정보변경 API
     * [PATCH] /users/:userIdx
     * @return BaseResponse<String>
     */
    /* 
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
    */
    

}
