package com.example.demo.src.store;
import java.lang.*;

import com.example.demo.config.BaseResponse;
import com.example.demo.src.store.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import static com.example.demo.config.BaseResponseStatus.*;
import com.example.demo.config.BaseException;



@Repository
public class StoreDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /* 
    public List<GetUserRes> getUsers(){
        String getUsersQuery = "select * from UserInfo";
        return this.jdbcTemplate.query(getUsersQuery,
                (rs,rowNum) -> new GetUserRes(
                        rs.getInt("userIdx"),
                        rs.getString("userName"),
                        rs.getString("ID"),
                        rs.getString("Email"),
                        rs.getString("password"))
                );
    }

    public List<GetUserRes> getUsersByEmail(String email){
        String getUsersByEmailQuery = "select * from UserInfo where email =?";
        String getUsersByEmailParams = email;
        return this.jdbcTemplate.query(getUsersByEmailQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("userIdx"),
                        rs.getString("userName"),
                        rs.getString("ID"),
                        rs.getString("Email"),
                        rs.getString("password")),
                getUsersByEmailParams);
    }
    */

    public List<GetStoreHomeRes> getStoreHome(){
        String getStoreHomeQuery = "select ID as id,(select NAME as shopName from SHOP where ID = i.STORE_ID) as shopName, NAME as itemName, PRICE as price, DISCOUNT_RATE as discountRate, (select count(*) from REVIEW as r where ITEM_ID = i.ID group by ITEM_ID) as reviewCnt,(select round(sum(STAR_RATING)/count(*),1) from REVIEW as r where ITEM_ID = i.ID group by ITEM_ID) as starAvg, DELIVERY_FEE as deliveryFee, SPECIAL_PRICE as specialPrice, MEDIUM_ID as mediumId from ITEM as i";
        return this.jdbcTemplate.query(getStoreHomeQuery,
                (rs, rowNum) -> new GetStoreHomeRes(
                        rs.getInt("id"),
                        rs.getString("shopName"),
                        rs.getString("itemName"),
                        rs.getInt("price"),
                        rs.getInt("discountRate"),
                        rs.getInt("reviewCnt"),
                        rs.getFloat("starAvg"),
                        rs.getInt("deliveryFee"),
                        rs.getInt("specialPrice"),
                        rs.getInt("mediumId"))
                );
    }

    public List<GetReviewRes> getReview(int itemId) {
        String getReviewQuery = "select (select NICKNAME from USER as u where u.USER_IDX = r.USER_IDX) as nickname , STAR_RATING as starRating, CREATED_AT as createdDate, (select NAME from ITEM_COLOR as c where c.ID = r.COLOR_ID) as optional, CONTENTS as writing from REVIEW as r where ITEM_ID = ?";
        return this.jdbcTemplate.query(getReviewQuery,
                (rs, rowNum) -> new GetReviewRes(
                    rs.getString("nickname"),
                    rs.getInt("starRating"),
                    rs.getTimestamp("createdDate"),
                    rs.getString("optional"), //DB -color
                    rs.getString("writing")),
                itemId);
                
    }

    public GetSellerInfoRes getSellerInfo(int itemId) {
        String getSellerInfoQuery = "select NAME as name, HEADER as header, ADDRESS as address, CUSTOMER_SERVICE_NUMBER as customerServiceNumber , EMAIL as email, COMPANY_REGISTRATION_NUMBER as companyRegistrationNumber from SELLER_INFORMATION where ID = ?";
        return this.jdbcTemplate.queryForObject(getSellerInfoQuery,
                (rs, rowNum) -> new GetSellerInfoRes(
                    rs.getString("name"),
                    rs.getString("header"),
                    rs.getString("address"),
                    rs.getString("customerServiceNumber"), 
                    rs.getString("email"),
                    rs.getString("companyRegistrationNumber")),
                itemId);
                
    }

    public List<GetInquiryRes> getInquiry(int itemId) {
        String getInquiryQuery = "SELECT (select NICKNAME from USER as u where u.USER_IDX = q.USER_IDX) as nickname, CREATED_AT as createdDate, QUESTION as question, ANSWER as answer, PROGRESS, SECRET, (select NAME from QUESTION_TYPE as qt where qt.ID = q.TYPE_ID ) as type from QUESTION as q where ITEM_ID = ?";
        return this.jdbcTemplate.query(getInquiryQuery,
                (rs, rowNum) -> new GetInquiryRes(
                    rs.getString("nickname"),
                    rs.getTimestamp("createdDate"),
                    rs.getString("question"), 
                    rs.getString("answer"),
                    rs.getInt("progress"),
                    rs.getInt("secret"),
                    rs.getString("type")),
                itemId);
                
    }

    public List<GetPurchaseRes> getPurchase(int itemId) {
        String getPruchaseQuery = "select NAME as optional, null additional, null addPrice from ITEM_COLOR where ITEM_ID= ? union all select null optional, NAME as addtional,PRICE as addPrice from ITEM_ADDITIONAL where ITEM_ID = ?";
        return this.jdbcTemplate.query(getPruchaseQuery,
                (rs, rowNum) -> new GetPurchaseRes(
                    rs.getString("optional"),
                    rs.getString("additional"),
                    rs.getInt("addPrice")),
                itemId,itemId);
                
    }

    public GetUserPaymentRes getUserPayment(int userIdx) {
        String getUserPaymentQuery = "select s.TITLE as title, s.ADDRESS as address, s.RECIPIENT as recipient , s.PHONE_NUMBER as phoneNumber, u.NICKNAME as ordererNickname, u.EMAIL as ordererEmail, u.PHONE_NUMBER as ordererPhone, u.POINTS as points FROM SHIPPING_ADDRESS_LIST as s JOIN USER as u ON s.USER_IDX=u.USER_IDX where u.USER_IDX=? and s.BASIC=?";
        return this.jdbcTemplate.queryForObject(getUserPaymentQuery,
                (rs, rowNum) -> new GetUserPaymentRes(
                    rs.getString("title"),
                    rs.getString("address"),
                    rs.getString("recipient"),
                    rs.getString("phoneNumber"), 
                    rs.getString("ordererNickname"),
                    rs.getString("ordererEmail"),
                    rs.getString("ordererPhone"),
                    rs.getInt("points")),
                userIdx,1);
    }

    public GetItemPaymentRes getItemPayment(int itemId) {
        String getItemPaymentQuery = "select (select s.NAME from SELLER_INFORMATION as s where i.SELLER_INFO_ID = s.ID) as companyName, i.DELIVERY_FEE as deliveryFee, i.NAME as itemName, d.NAME as couponName, d.BASE_PRICE as basePriceCondition, d.EXPIRATION_PERIOD as expDate from ITEM as i, DISCOUNT_COUPON as d where i.ID = ? and d.ID = (select COUPON_ID from AVAILABLE_COUPON where ITEM_ID =?)";
        return this.jdbcTemplate.queryForObject(getItemPaymentQuery,
                (rs, rowNum) -> new GetItemPaymentRes(
                    rs.getString("companyName"),
                    rs.getInt("deliveryFee"),
                    rs.getString("itemName"),
                    rs.getString("couponName"), 
                    rs.getInt("basePriceCondition"),
                    rs.getTimestamp("expDate")),
                itemId, itemId);
    }

    public GetItemDetailRes getItemDetail(int itemId) {
        String getItemDetailQuery = "select (select NAME from SHIPPING_CATEGORY as s where s.ID = i.SHIPPING_CATEGORY_ID) as shippingCategory, (select count(*) as scrapCnt from SCRAP_ITEM where ITEM_ID = ?) as scrapCnt, (select DISCOUNT_RATE from DISCOUNT_COUPON as d where d.ID = ?) as discountRate, (select NAME from DISCOUNT_COUPON as d where d.ID = ?) as couponName, (select BASE_PRICE from DISCOUNT_COUPON as d where d.ID = ?) as basePriceCondition, (select EXPIRATION_PERIOD from DISCOUNT_COUPON as d where d.ID = ?) as expDate from ITEM as i LEFT JOIN AVAILABLE_COUPON as a on i.ID = a.ITEM_ID where i.ID = ?";
        return this.jdbcTemplate.queryForObject(getItemDetailQuery,
                (rs, rowNum) -> new GetItemDetailRes(
                    rs.getString("shippingCategory"),
                    rs.getInt("scrapCnt"),
                    rs.getInt("discountRate"),
                    rs.getString("couponName"), 
                    rs.getInt("basePriceCondition"),
                    rs.getTimestamp("expDate")),
                itemId, itemId, itemId, itemId, itemId, itemId);
    }

    public int modifyUserNick(PatchUserNicknameReq patchUserNicknameReq){

        String modifyUserNicknameQuery = "update USER set NICKNAME = ? where USER_IDX = ?";
        Object[] modifyUserNicknameParams = new Object[]{patchUserNicknameReq.getNickname(), patchUserNicknameReq.getUserIdx()};

        return this.jdbcTemplate.update(modifyUserNicknameQuery,modifyUserNicknameParams);
    }

    public int modifyUserEmail(PatchUserEmailReq patchUserEmailReq){

        String modifyUserEmailQuery = "update USER set EMAIL = ? where USER_IDX = ?";
        Object[] modifyUserEmailParams = new Object[]{patchUserEmailReq.getEmail(), patchUserEmailReq.getUserIdx()};

        return this.jdbcTemplate.update(modifyUserEmailQuery, modifyUserEmailParams);
    }

    public int modifyUserPhone(PatchUserPhonenumberReq patchUserPhonenumberReq){

        String modifyUserPhonenumberQuery = "update USER set PHONE_NUMBER = ? where USER_IDX = ?";
        Object[] modifyUserPhonenumberParams = new Object[]{patchUserPhonenumberReq.getPhonenumber(), patchUserPhonenumberReq.getUserIdx()};

        return this.jdbcTemplate.update(modifyUserPhonenumberQuery, modifyUserPhonenumberParams);
    }

    public List<GetItemInfoTapRes> getItemInfoTap(int itemId) {
        String getItemInfoTapQuery = "select coi.IMAGE as contents from CONTENT_ORGANIZATION_OF_ITEM as c LEFT JOIN CONTENT_OF_ITEM as coi on c.CONTENT_ID = coi.ID where c.ITEM_ID = ?";
        return this.jdbcTemplate.query(getItemInfoTapQuery,
                (rs, rowNum) -> new GetItemInfoTapRes(
                    rs.getString("contents")),
                itemId);
    }

    public int addBasket(PostAddBasketReq postAddBasketReq){

        String addBasketQuery = "insert into BASKET (USER_IDX, ITEM_ID) VALUES (?,?)";
        Object[] addBasketParams = new Object[]{postAddBasketReq.getUserIdx(), postAddBasketReq.getItemId()};

        return this.jdbcTemplate.update(addBasketQuery, addBasketParams);
    }

    public int deleteBasket(DeleteItemBasketReq deleteItemBasketReq){

        String deleteBasketQuery = "delete from BASKET where USER_IDX = ? and ITEM_ID =?";
        Object[] deleteBasketParams = new Object[]{deleteItemBasketReq.getUserIdx(), deleteItemBasketReq.getItemId()};

        return this.jdbcTemplate.update(deleteBasketQuery, deleteBasketParams);
    }

    public int addReview(PostReviewReq postReviewReq){

        String addReviewQuery = "insert into REVIEW (ITEM_ID, COLOR_ID, USER_IDX, STAR_RATING, CONTENTS, IMAGE) values (?,?,?,?,?,?)";
        Object[] addReviewParams = new Object[]{postReviewReq.getItemId(), postReviewReq.getColorId(), postReviewReq.getUserIdxByJwt(), postReviewReq.getStarRating(), postReviewReq.getContents(), postReviewReq.getImage()};

        return this.jdbcTemplate.update(addReviewQuery, addReviewParams);
    }

    public int deleteReview(int reviewId){

        String deleteReviewQuery = "delete from REVIEW where REVIEW_ID =?";
        Object[] deleteReviewParams = new Object[]{reviewId};

        return this.jdbcTemplate.update(deleteReviewQuery, deleteReviewParams);
    }

    public int addScrap(ScrapItemReq postAddScrapItemReq){

        String addScrapQuery = "insert into SCRAP_ITEM (USER_IDX, ITEM_ID) values (?,?)";
        Object[] addScrapParams = new Object[]{postAddScrapItemReq.getUserIdxByJwt(), postAddScrapItemReq.getItemId()};

        return this.jdbcTemplate.update(addScrapQuery, addScrapParams);
    }

    public int deleteScrap(ScrapItemReq deleteScrapItemReq){

        String deleteScrapQuery = "delete from SCRAP_ITEM where USER_IDX =? and ITEM_ID =?";
        Object[] deleteScrapParams = new Object[]{deleteScrapItemReq.getUserIdxByJwt(), deleteScrapItemReq.getItemId()};

        return this.jdbcTemplate.update(deleteScrapQuery, deleteScrapParams);
    }

    public int modifyReview(PatchReviewReq patchReviewReq){

        String modifyReviewQuery = "update REVIEW set CONTENTS = ? where REVIEW_ID = ?";
        Object[] modifyReviewParams = new Object[]{patchReviewReq.getContents(), patchReviewReq.getReviewId()};

        return this.jdbcTemplate.update(modifyReviewQuery, modifyReviewParams);
    }

    public List<GetStoreHomeAdRes> getStoreHomeAd(){
        String getStoreHomeAdQuery = "select URL as adUrl from STORE_ADVERTISEMENT";
        return this.jdbcTemplate.query(getStoreHomeAdQuery,
                (rs, rowNum) -> new GetStoreHomeAdRes(
                        rs.getString("adUrl"))
                );
    }

    public List<GetItemScrapBookRes> getItemScrapBook(int userIdx){
        String getItemScrapBookQuery = "select si.ITEM_ID as id,(select NAME as shopName from SHOP where ID = i.STORE_ID) as shopName, i.NAME as itemName, i.PRICE as price, i.DISCOUNT_RATE as discountRate, (select count(*) from REVIEW as r where ITEM_ID = i.ID group by ITEM_ID) as reviewCnt,(select round(sum(STAR_RATING)/count(*),1) from REVIEW as r where ITEM_ID = i.ID group by ITEM_ID) as starAvg, i.DELIVERY_FEE as deliveryFee, i.SPECIAL_PRICE as specialPrice from SCRAP_ITEM as si LEFT JOIN ITEM as i on si.ITEM_ID = i.ID where USER_IDX = ? ";
        return this.jdbcTemplate.query(getItemScrapBookQuery,
                (rs, rowNum) -> new GetItemScrapBookRes(
                        rs.getInt("id"),
                        rs.getString("shopName"),
                        rs.getString("itemName"),
                        rs.getInt("price"),
                        rs.getInt("discountRate"),
                        rs.getInt("reviewCnt"),
                        rs.getFloat("starAvg"),
                        rs.getInt("deliveryFee"),
                        rs.getInt("specialPrice")),
                    userIdx);
    }
    
    /* 

    public int createUser(PostUserReq postUserReq){
        String createUserQuery = "insert into USER (EMAIL, PASSWORD, NICKNAME) VALUES (?,?,?)";
        Object[] createUserParams = new Object[]{postUserReq.getEMAIL(), postUserReq.getPASSWORD(), postUserReq.getNICKNAME()};
        this.jdbcTemplate.update(createUserQuery, createUserParams);

        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
        
    }

    public int checkEmail(String email){
        String checkEmailQuery = "select exists(select EMAIL from USER where EMAIL = ?)";
        return this.jdbcTemplate.queryForObject(checkEmailQuery,
                int.class,
                email);

    }

    public int checkNickname(String nickname){
        String checkNicknameQuery = "select exists(select NICKNAME from USER where NICKNAME = ?)";
        return this.jdbcTemplate.queryForObject(checkNicknameQuery,
                int.class,
                nickname);

    }

    public int modifyUserName(PatchUserReq patchUserReq){
        String modifyUserNameQuery = "update UserInfo set userName = ? where userIdx = ? ";
        Object[] modifyUserNameParams = new Object[]{patchUserReq.getUserName(), patchUserReq.getUserIdx()};

        return this.jdbcTemplate.update(modifyUserNameQuery,modifyUserNameParams);
    }

    public User getUserInfo(PostLoginReq postLoginReq){
        String getPwdQuery = "select USER_IDX, EMAIL, PASSWORD, NICKNAME, MEMBERSHIP_LEVEL, POINTS from USER where EMAIL = ?";
        String getPwdParams = postLoginReq.getEMAIL();

        return this.jdbcTemplate.queryForObject(getPwdQuery,
                (rs,rowNum)-> new User(
                        rs.getInt("USER_IDX"),
                        rs.getString("EMAIL"),
                        rs.getString("PASSWORD"),
                        rs.getString("NICKNAME"),
                        rs.getString("MEMBERSHIP_LEVEL"),
                        rs.getInt("POINTS")),
                getPwdParams
                );

    }
    */


}
