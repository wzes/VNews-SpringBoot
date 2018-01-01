package com.mobile.vnews.mapper;

import com.mobile.vnews.module.bean.Message;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Create by xuantang
 * @date on 10/31/17
 */

@Mapper
public interface MessageMapper {


    /**
     * TODO
     * Get notice of user
     * @param userID
     * @return
     */
    @Select("SELECT notice.ID AS id, notice.fromID, fromUser.username AS fromUsername, fromUser.image AS fromImage,\n" +
            "  notice.toID, news.title, notice.timestamp, notice.content, newsID,\n" +
            "  (SELECT username FROM user WHERE ID = notice.toID) AS toUsername\n" +
            " FROM notice, news, user as fromUser\n" +
            " WHERE news.ID = notice.newsID AND timestamp >= #{timestamp} AND notice.fromID = #{userID}\n" +
            " AND fromUser.ID = notice.fromID ORDER BY timestamp DESC")
    List<Message> getMessagesByUserID(@Param("userID") String userID,
                                      @Param("timestamp") long timestamp);

    /**
     * delete it by submit people
     * @param ID
     * @param fromID
     */
    @Delete("DELETE FROM notice WHERE ID = #{arg0} AND fromID = #{arg1}")
    void deleteMessageByID(int ID, String fromID);

}
