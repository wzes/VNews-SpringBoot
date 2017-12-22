package com.mobile.vnews.mapper;

import com.mobile.vnews.module.bean.User;
import org.apache.ibatis.annotations.*;

/**
 * @author Create by xuantang
 * @date on 10/31/17
 */

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO user (ID, username, password, telephone) " +
            "VALUES (#{id}, #{username}, #{password}, #{telephone})")
    int addUser(User user);

    @Select("SELECT ID, username, email, sex, birthday, image, telephone, motto, info, " +
            "(SELECT count(*) FROM like_news WHERE ID = like_news.userID) AS likeNewsCount, " +
            "(SELECT count(*) FROM view_news WHERE ID = view_news.userID) AS viewNewsCount " +
            "FROM user " +
            "WHERE username = #{arg0} AND password = #{arg1}")
    User findUserByUsername(String username, String password);

    @Delete("DELETE FROM user WHERE username = #{username}")
    int removeUserByUsername(String username);

    @Delete("DELETE FROM user WHERE telephone = #{telephone}")
    int removeUserByTelaphone(String telephone);


    @Update("UPDATE user SET image = #{image} WHERE ID = #{userID}")
    int updatePhoto(@Param("userID") String userID,
                    @Param("image")String image);

    // TODO
    @Update("UPDATE user SET password = #{password}, " +
            "email = #{email}, sex = #{sex}, birthday= #{birthday}, motto = #{motto}, username = #{username}" +
            "WHERE ID = #{id}")
    int updateUser(User user);

    @Select("SELECT count(telephone) FROM user WHERE telephone = #{telephone}")
    int checkTelephone(String telephone);

    /**
     *
     * @param telephone
     * @param password
     * @return
     */
    @Select("SELECT ID, username, email, sex, birthday, image, telephone, motto, info,\n" +
            "  (SELECT count(*) FROM like_news WHERE ID = like_news.userID) AS likeNewsCount,\n" +
            "  (SELECT count(*) FROM view_news WHERE ID = view_news.userID) AS viewNewsCount\n" +
            "FROM user\n" +
            "WHERE telephone = #{arg0} AND password = #{arg1}")
    User findUserByTelephone(String telephone, String password);

    @Select("SELECT ID, username, email, sex, birthday, image, telephone, motto, info,\n" +
            "  (SELECT count(*) FROM like_news WHERE ID = like_news.userID) AS likeNewsCount,\n" +
            "  (SELECT count(*) FROM view_news WHERE ID = view_news.userID) AS viewNewsCount\n" +
            "FROM user\n" +
            "WHERE ID = #{userID}")
    User getUser(String userID);

}
