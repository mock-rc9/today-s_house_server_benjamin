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


}
