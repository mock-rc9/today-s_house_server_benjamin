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
        // Get Store Home
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
            exception.printStackTrace();
            return new BaseResponse<>((exception.getStatus()), null);
        }

    }

    /**
     * 상품 판매자정보 API
     * [GET] /app/stores/seller-info/:item-id
     * @return BaseResponse<GetSellerInfoRes>
     */
    // Path-variable
    @ResponseBody
    @GetMapping("/seller-info/{item-id}") 
    public BaseResponse<GetSellerInfoRes> getSellerInfo(@PathVariable("item-id") int itemId) {
        // Get Seller Information
        try{
            GetSellerInfoRes getSellerInfoRes = storeProvider.getSellerInfo(itemId);
            return new BaseResponse<>(SUCCESS, getSellerInfoRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()), null);
        }

    }

    /**
     * 상품 문의 불러오기 API
     * [GET] /app/stores/inquiry/:item-id
     * @return BaseResponse<GetInquiryRes>
     */
    // Path-variable
    @ResponseBody
    @GetMapping("/inquiry/{item-id}") 
    public BaseResponse<List<GetInquiryRes>> getInquiry (@PathVariable("item-id") int itemId) {
        // Get inquiry 
        try{
            List<GetInquiryRes> getInquiryRes = storeProvider.getInquiry(itemId);
            return new BaseResponse<>(SUCCESS, getInquiryRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()), null);
        }

    }

    /**
     * 상품 구매하기 API
     * [GET] /app/stores/item/purchase/:item-id
     * @return BaseResponse<GetInquiryRes>
     */
    // Path-variable
    @ResponseBody
    @GetMapping("/item/purchase/{item-id}") 
    public BaseResponse<List<GetPurchaseRes>> getPurchse(@PathVariable("item-id") int itemId) {
        // Get Purchase
        try{
            List<GetPurchaseRes> getPurchaseRes= storeProvider.getPurchase(itemId);
            return new BaseResponse<>(SUCCESS, getPurchaseRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()), null);
        }

    }

    /**
     * 사용자관련 주문/결제 API
     * [GET] /app/stores/payment/user/:user-idx
     * @return BaseResponse<GetUserPaymentRes>
     */
    // Path-variable
    @ResponseBody
    @GetMapping("/payment/user/{user-idx}") 
    public BaseResponse<GetUserPaymentRes> getUserPayment(@PathVariable("user-idx") int userIdx) {
        // Get User Payment
        try{
            GetUserPaymentRes getUserPaymentRes= storeProvider.getUserPayment(userIdx);
            return new BaseResponse<>(SUCCESS, getUserPaymentRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()), null);
        }

    }

    /**
     * 개별상품관련 주문/결제 API
     * [GET] /app/stores/payment/item/:item-id
     * @return BaseResponse<GetItemPaymentRes>
     */
    // Path-variable
    @ResponseBody
    @GetMapping("/payment/item/{item-id}") 
    public BaseResponse<GetItemPaymentRes> getItemPayment(@PathVariable("item-id") int itemId) {
        // Get Item Payment
        try{
            GetItemPaymentRes getItemPaymentRes= storeProvider.getItemPayment(itemId);
            return new BaseResponse<>(SUCCESS, getItemPaymentRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()), null);
        }

    }

    /**
     * 상품 상세정보(배송 카테고리, 스크랩 수, 쿠폰관련)  API
     * [GET] /app/stores/item/:item-id
     * @return BaseResponse<GetItemDetailRes>
     */
    // Path-variable
    @ResponseBody
    @GetMapping("/item/{item-id}") 
    public BaseResponse<GetItemDetailRes> getItemDetail(@PathVariable("item-id") int itemId) {
        // Get Item Detail
        try{
            GetItemDetailRes getItemDetailRes = storeProvider.getItemDetail(itemId);
            return new BaseResponse<>(SUCCESS, getItemDetailRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()), null);
        }

    }
    
    /** 
    * 주문/결제의 주문자 닉네임수정 API
    * [PATCH] /app/stores/payment/modify-nick/:user-idx
    * @return BaseResponse<String>
    */
    // Path-variable
   @ResponseBody
   @PatchMapping("/payment/modify-nick/{user-idx}")
   public BaseResponse<String> modifyUserNick(@PathVariable("user-idx") int userIdx, @RequestBody Store store){
       try {
           //jwt에서 idx 추출.
           int userIdxByJwt = jwtService.getUserIdx();
           //userIdx와 접근한 유저가 같은지 확인
           if(userIdx != userIdxByJwt){
               return new BaseResponse<>(INVALID_USER_JWT,null);
           }
           //같다면 주문자 닉네임 수정
           PatchUserNicknameReq patchUserNicknameReq  = new PatchUserNicknameReq(userIdx, store.getNickname());
           storeService.modifyUserNick(patchUserNicknameReq);

           String result = "*닉네임을 변경하였습니다*";
           return new BaseResponse<>(SUCCESS ,result);
       } catch (BaseException exception) {
           return new BaseResponse<>((exception.getStatus()), null);
       }
   }

   /** 
    * 주문/결제의 주문자 이메일수정 API
    * [PATCH] /app/stores/payment/modify-email/:user-idx
    * @return BaseResponse<String>
    */
    // Path-variable
    @ResponseBody
    @PatchMapping("/payment/modify-email/{user-idx}")
    public BaseResponse<String> modifyUserEmail(@PathVariable("user-idx") int userIdx, @RequestBody Store store){
        try {
            //jwt에서 idx 추출.
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            if(userIdx != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT,null);
            }
            //같다면 주문자 이메일 수정
            PatchUserEmailReq patchUserEmailReq   = new PatchUserEmailReq (userIdx, store.getEmail());
            storeService.modifyUserEmail(patchUserEmailReq );
 
            String result = "*이메일을 변경하였습니다*";
            return new BaseResponse<>(SUCCESS ,result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()), null);
        }
    }

    /** 
    * 주문/결제의 주문자 전화번호수정 API
    * [PATCH] /app/stores/payment/modify-phone/:user-idx
    * @return BaseResponse<String>
    */
    // Path-variable
    @ResponseBody
    @PatchMapping("/payment/modify-phone/{user-idx}")
    public BaseResponse<String> modifyUserPhone(@PathVariable("user-idx") int userIdx, @RequestBody Store store){
        try {
            //jwt에서 idx 추출.
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            if(userIdx != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT,null);
            }
            //같다면 주문자 전화번호 수정
            PatchUserPhonenumberReq patchUserPhonenumberReq = new PatchUserPhonenumberReq(userIdx, store.getPhoneNumber());
            storeService.modifyUserPhone(patchUserPhonenumberReq);
 
            String result = "*전화번호를 변경하였습니다*";
            return new BaseResponse<>(SUCCESS ,result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()), null);
        }
    }

    /**
     * 상품정보 탭 (이미지)API
     * [GET] /app/stores/item/info-tap/:item-id
     * @return BaseResponse<GetItemInfoTapRes>
     */
    // Path-variable
    @ResponseBody
    @GetMapping("/item/info-tap/{item-id}") 
    public BaseResponse<List<GetItemInfoTapRes>> getItemInfoTap(@PathVariable("item-id") int itemId) {
        // Get Item Info Tap
        try{
            List<GetItemInfoTapRes> getItemInfoTapRes = storeProvider.getItemInfoTap(itemId);
            return new BaseResponse<>(SUCCESS, getItemInfoTapRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()), null);
        }

    }

    /**
     * 장바구니 담기 API
     * [POST] /app/stores/item/add/basket
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PostMapping("/item/add/basket")
    public BaseResponse<String> addBasket(@RequestBody Store store){
        if(store.getItemId() == 0){
            return new BaseResponse<>(POST_ADD_BASKET_EMPTY_ITEM, null);
        }
        try{
            //jwt에서 idx 추출.
            int userIdxByJwt = jwtService.getUserIdx();

            PostAddBasketReq postAddBasketReq = new PostAddBasketReq(userIdxByJwt, store.getItemId());

            storeService.addBasket(postAddBasketReq);
 
            String result = "*해당 상품이 장바구니에 담겼습니다*";
            return new BaseResponse<>(SUCCESS ,result);
        } catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()), null);
        }
    }

    /**
     * 장바구니 삭제 API
     * [DELETE] /app/stores/item/delete/basket
     * @return BaseResponse<String>
     */
    @ResponseBody
    @DeleteMapping("/item/delete/basket")
    public BaseResponse<String> deleteBasket(@RequestBody Store store) {
        try{
            //jwt에서 idx 추출.
            int userIdxByJwt = jwtService.getUserIdx();

            DeleteItemBasketReq deleteItemBasketReq = new DeleteItemBasketReq(userIdxByJwt, store.getItemId());

            storeService.deleteBasket(deleteItemBasketReq);
 
            String result = "*해당 상품이 장바구니에서 제거되었습니다*";
            return new BaseResponse<>(SUCCESS ,result);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()), null);
        }
    }

    /**
     * 리뷰쓰기 API
     * [POST] /app/stores/item/review
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PostMapping("/item/review")
    public BaseResponse<String> addReview(@RequestBody Review review){
        if(review.getContents() == ""){
            return new BaseResponse<>(POST_REVIEW_EMPTY, null);
        }
        try{
            //jwt에서 idx 추출.
            int userIdxByJwt = jwtService.getUserIdx();

            PostReviewReq postReviewReq = new PostReviewReq(review.getItemId(), userIdxByJwt, review.getColorId(), review.getStarRating(), review.getContents(), review.getImage());

            storeService.addReview(postReviewReq);
 
            String result = "*리뷰가 성공적으로 작성되었습니다*";
            return new BaseResponse<>(SUCCESS ,result);
        } catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()), null);
        }
    }

    /**
     * 리뷰 삭제 API
     * [DELETE] /app/stores/item/delete/review/:user-idx
     * @return BaseResponse<String>
     */
    @ResponseBody
    @DeleteMapping("item/delete/review/{user-idx}")
    public BaseResponse<String> deleteReview(@PathVariable("user-idx") int userIdx, @RequestBody Review review) {
        try{
            //jwt에서 idx 추출.
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            if(userIdx != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT,null);
            }

            //같으면 삭제
            storeService.deleteReview(review.getReviewId());
 
            String result = "*리뷰가 삭제되었습니다*";
            return new BaseResponse<>(SUCCESS ,result);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()), null);
        }
    }

    /**
     * 상품 스크랩 API
     * [POST] /app/stores/item/scrap
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PostMapping("/item/scrap")
    public BaseResponse<String> addScrap(@RequestBody Store store){
        try{
            //jwt에서 idx 추출.
            int userIdxByJwt = jwtService.getUserIdx();

            ScrapItemReq postAddScrapItemReq = new ScrapItemReq(userIdxByJwt, store.getItemId());

            storeService.addScrap(postAddScrapItemReq);
 
            String result = "*해당 상품을 스크랩했습니다*";
            return new BaseResponse<>(SUCCESS ,result);
        } catch (BaseException exception){
            return new BaseResponse<>((exception.getStatus()), null);
        }
    }

    /**
     * 스크랩 삭제 API
     * [DELETE] /app/stores/item/delete/scrap
     * @return BaseResponse<String>
     */
    @ResponseBody
    @DeleteMapping("/item/delete/scrap")
    public BaseResponse<String> deleteScrap(@RequestBody Store store) {
        try{
            //jwt에서 idx 추출.
            int userIdxByJwt = jwtService.getUserIdx();

            ScrapItemReq deleteScrapItemReq = new ScrapItemReq(userIdxByJwt, store.getItemId());

            storeService.deleteScrap(deleteScrapItemReq);
 
            String result = "*해당상품을 스크랩 목록에서 삭제하였습니다*";
            return new BaseResponse<>(SUCCESS ,result);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()), null);
        }
    }

    /** 
    * 리뷰글 수정 API
    * [PATCH] /app/stores/item/modify-review/:user-idx
    * @return BaseResponse<String>
    */
    // Path-variable
    @ResponseBody
    @PatchMapping("/item/modify-review/{user-idx}")
    public BaseResponse<String> modifyReview(@PathVariable("user-idx") int userIdx, @RequestBody Review review){
        try {
            //jwt에서 idx 추출.
            int userIdxByJwt = jwtService.getUserIdx();
            //userIdx와 접근한 유저가 같은지 확인
            if(userIdx != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT,null);
            }
            //같다면 주문자 리뷰 수정
            PatchReviewReq patchReviewReq = new PatchReviewReq(userIdx, review.getReviewId(), review.getContents());
            storeService.modifyReview(patchReviewReq);
 
            String result = "*리뷰를 수정하였습니다*";
            return new BaseResponse<>(SUCCESS ,result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()), null);
        }
    }

    /**
     * 스토어 홈 광고 불러오기 API
     * [GET] /app/stores/home/ad
     * @return BaseResponse<GetStoreHomeRes>
     */
    // Path-variable
    @ResponseBody
    @GetMapping("/home/ad") 
    public BaseResponse<List<GetStoreHomeAdRes>> getStoreHomeAd() {
        // Get Store Home Advertisement
        try{
            List<GetStoreHomeAdRes> getStoreHomeAdRes = storeProvider.getStoreHomeAd();
            return new BaseResponse<>(SUCCESS, getStoreHomeAdRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()), null);
        }

    }

    /**
     * 상품 스크랩북 API
     * [GET] /app/stores/scrap-book
     * @return BaseResponse<GetStoreHomeRes>
     */
    // Path-variable
    @ResponseBody
    @GetMapping("/scrap-book") 
    public BaseResponse<List<GetItemScrapBookRes>> getItemScrapBook() {
        // Get Item Scrap Book
        try{
            int userIdxByJwt = jwtService.getUserIdx();

            List<GetItemScrapBookRes> getItemScrapBookRes = storeProvider.getItemScrapBook(userIdxByJwt);
            return new BaseResponse<>(SUCCESS, getItemScrapBookRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()), null);
        }

    }

    

    
    /**
     * 담겨있는 장바구니 API
     * [GET] /app/stores/item/basket/:user-idx
     * @return BaseResponse<GetUserRes>
     */
    // Path-variable
    /* 
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
    */

    

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
