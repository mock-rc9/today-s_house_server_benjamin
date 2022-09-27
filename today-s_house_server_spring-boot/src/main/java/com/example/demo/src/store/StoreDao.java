package com.example.demo.src.store;


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
        String getReviewQuery = "select (select NICKNAME from USER as u where u.USER_IDX = r.USER_IDX), STAR_RATING, (select NAME as optional from ITEM_COLOR as c where c.ID = r.COLOR_ID), CREATED_AT, CONTENTS from REVIEW as r where ITEM_ID = ?";
        return this.jdbcTemplate.query(getReviewQuery,
                (rs, rowNum) -> new GetReviewRes(
                    rs.getString("nickname"),
                    rs.getInt("starRating"),
                    //rs.getDate("createdDate").toLocalDate(), //문제다문제
                    rs.getTimestamp("createdDate"),
                    rs.getString("optional"), //DB -color
                    rs.getString("writing")),
                itemId);
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
