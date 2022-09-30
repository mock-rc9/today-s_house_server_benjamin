package com.example.demo.config;

import lombok.Getter;

/**
 * 에러 코드 관리
 */
@Getter
public enum BaseResponseStatus {
    /**
     * 1000 : 요청 성공
     */
    SUCCESS(true, 1000, "요청에 성공하였습니다."),


    /**
     * 2000 : Request 오류
     */
    // Common
    REQUEST_ERROR(false, 2000, "입력값을 확인해주세요."),
    EMPTY_JWT(false, 2001, "JWT를 입력해주세요."),
    INVALID_JWT(false, 2002, "유효하지 않은 JWT입니다."),
    INVALID_USER_JWT(false,2003,"권한이 없는 유저의 접근입니다." ),

    // users
    USERS_EMPTY_USER_ID(false, 2010, "유저 아이디 값을 확인해주세요."),

    // [POST] /users/sign-up && [POST] /users/login
    POST_USERS_EMPTY_EMAIL(false, 2015, "이메일을 입력해주세요."),
    POST_USERS_EMPTY_PASSWORD(false, 2016, "비밀번호를 입력해주세요."),
    POST_USERS_EMPTY_NICKNAME(false, 2017, "닉네임을 입력해주세요."),

    POST_ADD_BASKET_EMPTY_ITEM(false, 2018, "아이템이 제대로 선택되지 않았습니다."),

    POST_REVIEW_EMPTY(false,2019,"리뷰작성란이 비어있습니다."),

    POST_SHIPPING_NAME_EMPTY(false,2020,"배송지명을 입력해주세요."),
    POST_RECIPIENT_EMPTY(false,2021,"받는사람을 입력해주세요."),
    POST_PHONENUMBER_EMPTY(false,2022,"연락처를 입력해주세요."),
    POST_ADDRESS_EMPTY(false,2023,"주소를 입력해주세요."),

    



    
    /**
     * 3000 : Response 오류
     */
    // Common
    RESPONSE_ERROR(false, 3000, "값을 불러오는데 실패하였습니다."),

    // [POST] /users/sign-up
    POST_USERS_EXISTS_EMAIL(false,3001,"중복된 이메일입니다." ),
    POST_USERS_EXISTS_NICKNAME(false,3002,"중복된 닉네임입니다."),
    CREATE_USER_ERROR(false, 3003, "유저를 생성하는데 실패하였습니다."),

    // [POST] /users/login
    FAILED_TO_LOGIN(false,3004,"아이디 또는 비밀번호가 잘못되었습니다."),



    /**
     * 4000 : Database, Server 오류
     */
    DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, 4001, "서버와의 연결에 실패하였습니다."),

    // [POST] /users/sign-up
    CHECK_EXISTS_EMAIL(false, 4002, "중복된 이메일인지 체크하는데 실패하였습니다."),
    CHECK_EXISTS_NICKNAME(false, 4003, "중복된 닉네임인지 체크하는데 실패하였습니다."),


    PASSWORD_ENCRYPTION_ERROR(false, 4011, "비밀번호 암호화에 실패하였습니다."),
    PASSWORD_DECRYPTION_ERROR(false, 4012, "비밀번호 복호화에 실패하였습니다."),

    FILE_UPLOAD_FAIL(false,4013,"파일 업로드에 실패하였습니다."),
    FILE_DELETE_FAIL(false,4014,"파일 삭제에 실패하였습니다."),
    WRONG_FORMAT_ERROR(false,4015,"잘못된 형식의 파일입니다."),

    //[PATCH] /app/stores/payment/correction/:user-idx
    MODIFY_FAIL_USER_NICKNAME(false,4016,"유저 닉네임 수정에 실패하였습니다."),
    MODIFY_FAIL_USER_EMAIL(false,4017,"유저 이메일 수정에 실패하였습니다."),
    MODIFY_FAIL_USER_PHONENUMBER(false,4018,"유저 전화번호 수정에 실패하였습니다."),

    ADD_BASKET_FAIL(false, 4019, "장바구니에 상품을 추가하지 못하였습니다."),
    DELETE_BASKET_FAIL(false, 4020, "장바구니에서 상품을 삭제하지 못하였습니다."),

    ADD_FOLLOW_FAIL(false,4021,"follow에 실패하였습니다."),
    DELETE_FOLLOW_FAIL(false,4022,"unfollow에 실패하였습니다."),

    ADD_REVIEW_FAIL(false,4023,"리뷰업로드에 실패하였습니다."),
    DELETE_REVIEW_FAIL(false,4024,"리뷰삭제에 실패하였습니다."),
    ADD_SCRAP_FAIL(false,4025,"상품을 스크랩하는데에 실패하였습니다."),
    DELETE_SCRAP_FAIL(false,4026,"상품을 스크랩 목록에서 삭제하는데에 실패하였습니다."),

    MODIFY_FAIL_REVIEW(false,4027,"리뷰를 수정하는데 실패하였습니다."),
    MODIFY_FAIL_LEVEL(false,4028,"회원등급을 수정하는데 실패하였습니다."),
    MODIFY_FAIL_PROFILE(false,4029,"프로필 사진을 수정하는데 실패하였습니다."),
    MODIFY_FAIL_BACKGROUNDIMAGE(false,4030,"배경사진을 수정하는데 실패하였습니다."),
    MODIFY_FAIL_MYURL(false,4031,"my url을 수정하는데 실패하였습니다."),
    MODIFY_FAIL_INTRODUCTION(false,4032,"한 줄 소개를 수정하는데 실패하였습니다."),
    MODIFY_FAIL_PASSWORD(false,4033,"비밀번호 변경에 실패하였습니다."),

    ADD_SHIPPING_LIST_FAIL(false,4034,"배송지 추가에 실패하였습니다."),
    DELETE_SHIPPING_LIST_FAIL(false,4035,"배송지 삭제에 실패하였습니다."),
    GET_SHIPPING_LIST_FAIL(false,4036,"배송지 조회에 실패하였습니다."),

    ADD_POINTS_FAIL(false,4037,"포인트를 획득하는데 실패하였습니다."),
    MINUS_POINTS_FAIL(false,4038,"포인트를 차감하는데 실패하였습니다."),

    ALARM_SETTING_FAIL(false,4039,"알람설정에 실패하였습니다."),

    AUTO_PLAY_SETTING_FAIL(false,4040,"자동 재생 설정에 실패하였습니다.");

    // 5000 : 필요시 만들어서 쓰세요
    // 6000 : 필요시 만들어서 쓰세요


    private final boolean isSuccess;
    private final int code;
    private final String message; 

    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;

    }
}
