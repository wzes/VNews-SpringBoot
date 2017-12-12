package com.mobile.vnews.mapper;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserPreferenceMapper {

    /**
     *
     * @param userID
     * @param typeID
     * @return
     */
    @Insert("INSERT INTO user_preference (userID, typeID) VALUES (#{userID}, #{typeID})")
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
            "WHERE userID = #{userID} AND typeID = #{typeID}")
    void updateUserPreference(String userID, int typeID);

    /**
     *
     * @param userID
     * @param typeID
     * @return
     */
    @Select("SELECT count(*) user_preference " +
            "WHERE userID = #{userID} AND typeID = #{typeID}")
    int checkUserPreference(String userID, int typeID);

    /**
     *
     * @param userID
     * @param typeID
     * @return
     */
    @Delete("DELETE FROM user_preference WHERE userID = #{userID} AND typeID = #{typeID}")
    int deleteUserPreference(String userID, int typeID);

}
