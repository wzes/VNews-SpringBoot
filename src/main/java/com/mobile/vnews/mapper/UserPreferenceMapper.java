package com.mobile.vnews.mapper;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;
/**
*@Author:kaijie
*@description:
*@Date:10:56 2017/12/14
*/
@Mapper
@Service
public interface UserPreferenceMapper {


    /**
     *
     * @param typeName
     * @return
     */
    @Select("SELECT ID\n" +
            "FROM type\n" +
            "WHERE name = #{typeName}")
    int getTypeIDByName(String typeName);

    /**
     *
     * @param userID
     * @param typeID
     * @return
     */
    @Insert("INSERT INTO user_preference (userID, typeID) VALUES (#{arg0}, #{arg1})")
    int addUserPreference(String userID, int typeID);

    /**
     * Get preferences list of user
     * @param userID
     * @return
     */
    @Select("SELECT DISTINCT name\n" +
            " FROM user_preference NATURAL JOIN user, type\n" +
            " WHERE user.ID = #{userID} AND typeID = type.ID")
    List<String> getUserPreference(String userID);



    /**
     * Update
     * @param userID
     * @return
     */
    @Select("UPDATE user_preference\n" +
            "SET preference = preference + 1\n" +
            "WHERE userID = #{arg0} AND typeID = #{arg1}")
    void updateUserPreference(String userID, int typeID);

    /**
     *
     * @param userID
     * @param typeID
     * @return
     */
    @Select("SELECT count(*) user_preference " +
            "WHERE userID = #{arg0} AND typeID = #{arg1}")
    int checkUserPreference(String userID, int typeID);

    /**
     *
     * @param userID
     * @param typeID
     * @return
     */
    @Delete("DELETE FROM user_preference WHERE userID = #{arg0} AND typeID = #{arg1}")
    int deleteUserPreference(String userID, int typeID);

}
