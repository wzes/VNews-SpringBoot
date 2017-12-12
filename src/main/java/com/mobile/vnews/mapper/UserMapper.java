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
            "VALUES (#{ID}, #{username}, #{password}, #{telephone})")
    int addUser(User user);

    @Select("SELECT ID, username, email, sex, birthday, image, telephone, motto, info,\n" +
            "  (SELECT count(*) FROM like_news WHERE ID = like_news.userID) AS likeNewsCount,\n" +
            "  (SELECT count(*) FROM view_news WHERE ID = view_news.userID) AS viewNewsCount\n" +
            "FROM user\n" +
            "WHERE username = #{username} AND password = #{password}")
    User findUserByUsername(String username, String password);

    @Delete("DELETE FROM user WHERE username = #{username}")
    int removeUserByUsername(String username);

    @Delete("DELETE FROM user WHERE telephone = #{telephone}")
    int removeUserByTelaphone(String telephone);


    @Update("UPDATE user SET image=#{image} WHERE username = #{username}")
    int updatePhoto(String username);

    // TODO
    @Update("UPDATE user SET password = #{password}, " +
            "email = #{email}, sex = #{sex}, birthday= #{birthday} " +
            "WHERE username = #{username}")
    int updateUser(User user);


    @Select("SELECT count(telephone) FROM user WHERE telephone = #{telephone}")
    int checkTelephone(String telephone);

    @Select("SELECT ID, username, email, sex, birthday, image, telephone, motto, info,\n" +
            "  (SELECT count(*) FROM like_news WHERE ID = like_news.userID) AS likeNewsCount,\n" +
            "  (SELECT count(*) FROM view_news WHERE ID = view_news.userID) AS viewNewsCount\n" +
            "FROM user\n" +
            "WHERE telephone = #{telephone} AND password = #{password}")
    User findUserByTelephone(String telephone);

    @Select("SELECT ID, username, email, sex, birthday, image, telephone, motto, info,\n" +
            "  (SELECT count(*) FROM like_news WHERE ID = like_news.userID) AS likeNewsCount,\n" +
            "  (SELECT count(*) FROM view_news WHERE ID = view_news.userID) AS viewNewsCount\n" +
            "FROM user\n" +
            "WHERE ID = #{userID}")
    User getUser(String userID);

}
