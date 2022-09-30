package com.example.demo.src.user;


import com.example.demo.src.user.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

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

    public GetUserRes getUser(int userIdx){
        String getUserQuery = "select * from UserInfo where userIdx = ?";
        int getUserParams = userIdx;
        return this.jdbcTemplate.queryForObject(getUserQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("userIdx"),
                        rs.getString("userName"),
                        rs.getString("ID"),
                        rs.getString("Email"),
                        rs.getString("password")),
                getUserParams);
    }
    

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
        String getPwdQuery = "select USER_IDX, EMAIL, PASSWORD, NICKNAME, (select NAME from MEMBERSHIP_LEVEL as ml where ml.ID = USER.LEVEL_ID) as MEMBERSHIP_LEVEL, POINTS from USER where EMAIL = ?";
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

    public int addFollow(PostFollowReq postFollowReq){

        String addFollowQuery = "insert into FOLLOW (USER_IDX, FOLLOWED_USER_IDX) VALUES (?,?)";
        Object[] addFollowParams = new Object[]{postFollowReq.getUserIdxByJwt(), postFollowReq.getFollowedId()};

        return this.jdbcTemplate.update(addFollowQuery, addFollowParams);
    }

    public int deleteFollow(DeleteFollowReq deleteFollowReq){

        String deleteFollowQuery = "delete from FOLLOW where USER_IDX = ? and FOLLOWED_USER_IDX =?";
        Object[] deleteFollowParams = new Object[]{deleteFollowReq.getUserIdxByJwt(), deleteFollowReq.getUnfollowedId()};

        return this.jdbcTemplate.update(deleteFollowQuery, deleteFollowParams);
    }

    public int modifyProfile(PatchProfileReq patchProfileReq){
        String modifyProfileQuery = "update USER set PROFILE_URL= ? where USER_IDX = ? ";
        Object[] modifyProfileParams = new Object[]{patchProfileReq.getProfile(), patchProfileReq.getUserIdx()};

        return this.jdbcTemplate.update(modifyProfileQuery,modifyProfileParams);
    }

    public int levelUp(int userIdx){
        String levelUpQuery = "update USER set LEVEL_ID= 2 where USER_IDX = ? ";
        Object[] levelUpParams = new Object[]{userIdx};

        return this.jdbcTemplate.update(levelUpQuery,levelUpParams);
    }

    public int levelDown(int userIdx){
        String levelDownQuery = "update USER set LEVEL_ID= 1 where USER_IDX = ? ";
        Object[] levelDownParams = new Object[]{userIdx};

        return this.jdbcTemplate.update(levelDownQuery,levelDownParams);
    }

    public int patchBackImage(PatchBackReq patchBackReq){
        String patchBackImageQuery = "update USER set BACKGROUND_IMAGE = ? where USER_IDX = ? ";
        Object[] patchBackImageParams = new Object[]{patchBackReq.getBackgroundImage(), patchBackReq.getUserIdx()};

        return this.jdbcTemplate.update(patchBackImageQuery,patchBackImageParams);
    }

    public int patchUrl(PatchMyUrlReq patchMyUrlReq){
        String levelDownQuery = "update USER set MY_URL= ? where USER_IDX = ? ";
        Object[] levelDownParams = new Object[]{patchMyUrlReq.getMyUrl(), patchMyUrlReq.getUserIdx()};

        return this.jdbcTemplate.update(levelDownQuery,levelDownParams);
    }

    public int patchIntroduction(PatchIntroductionReq patchIntroductionReq){
        String levelDownQuery = "update USER set INTRODUCTION= ? where USER_IDX = ? ";
        Object[] levelDownParams = new Object[]{patchIntroductionReq.getIntroduction(), patchIntroductionReq.getUserIdx()};

        return this.jdbcTemplate.update(levelDownQuery,levelDownParams);
    }

    public int patchPwd(PatchPwdReq patchPwdReq){
        String patchPwdQuery = "update USER set PASSWORD = ? where USER_IDX = ? ";
        Object[] patchPwdParams = new Object[]{patchPwdReq.getPwd(), patchPwdReq.getUserIdx()};

        return this.jdbcTemplate.update(patchPwdQuery,patchPwdParams);
    }

    public int addShippingList(PostShipAddressReq postShipAddressReq){
        String addShippingListQuery = "insert into SHIPPING_ADDRESS_LIST (USER_IDX, TITLE, RECIPIENT, PHONE_NUMBER, ADDRESS) VALUES (?,?,?,?,?)";
        Object[] addShippingListParams = new Object[]{postShipAddressReq.getUserIdx(), postShipAddressReq.getShippingName(), postShipAddressReq.getRecipient(), postShipAddressReq.getPhonenumber(), postShipAddressReq.getAddress()};
        return this.jdbcTemplate.update(addShippingListQuery, addShippingListParams);
        
    }

    public int deleteShippingList(DeleteShipAddressReq deleteShipAddressReq){

        String deleteShippingListQuery = "delete from SHIPPING_ADDRESS_LIST where ID = ?";
        Object[] deleteShippingListParams = new Object[]{deleteShipAddressReq.getShipAddId()};

        return this.jdbcTemplate.update(deleteShippingListQuery, deleteShippingListParams);
    }

    public List<GetShippingListRes> getShippingList(int userIdx){
        String getShippingListQuery = "select ID as shipListId, TITLE as shipListName, ADDRESS, RECIPIENT, PHONE_NUMBER as phonenumber, BASIC from SHIPPING_ADDRESS_LIST where USER_IDX = ?";
        return this.jdbcTemplate.query(getShippingListQuery,
                (rs, rowNum) -> new GetShippingListRes(
                        rs.getInt("shipListId"),
                        rs.getString("shipListName"),
                        rs.getString("address"),
                        rs.getString("recipient"),
                        rs.getString("phonenumber"),
                        rs.getInt("basic")),
                    userIdx);
    }

    public int addPoints(PostAddPointsReq postAddPointsReq){
        String addPointsQuery = "update USER SET POINTS = POINTS + ? where USER_IDX = ?";
        Object[] addPointsParams = new Object[]{postAddPointsReq.getPlusPoint(), postAddPointsReq.getUserIdx()};
        return this.jdbcTemplate.update(addPointsQuery, addPointsParams);
        
    }

    public int minusPoints(PostMinusPointsReq postMinusPointsReq){
        String minusPointsQuery = "update USER SET POINTS = POINTS - ? where USER_IDX = ?";
        Object[] minusPointsParams = new Object[]{postMinusPointsReq.getMinusPoint(), postMinusPointsReq.getUserIdx()};
        return this.jdbcTemplate.update(minusPointsQuery, minusPointsParams);
        
    }

    public int postAlarm(PostAlarmReq postAlarmReq){

        String postAlarmQuery = "insert into USER_NOTIFICATION (USER_IDX, NOTIFICATION_ID) values (?,?)";
        Object[] postAlarmParams = new Object[]{postAlarmReq.getUserIdx(), postAlarmReq.getAlarmId()};

        return this.jdbcTemplate.update(postAlarmQuery, postAlarmParams);
    }

    public int deleteAlarm(PostAlarmReq postAlarmReq){

        String postAlarmQuery = "delete from USER_NOTIFICATION where USER_IDX = ? and NOTIFICATION_ID = ?";
        Object[] postAlarmParams = new Object[]{postAlarmReq.getUserIdx(), postAlarmReq.getAlarmId()};

        return this.jdbcTemplate.update(postAlarmQuery, postAlarmParams);
    }

    public int patchAutoPlay(PatchPalySetReq  patchPalySetReq){

        String patchAutoPlayQuery = "update USER set AUTO_PLAY_ID = ? where USER_IDX =?";
        Object[] patchAutoPlayParams = new Object[]{patchPalySetReq.getAutoPlayId(), patchPalySetReq.getUserIdx()};

        return this.jdbcTemplate.update(patchAutoPlayQuery, patchAutoPlayParams);
    }


}
