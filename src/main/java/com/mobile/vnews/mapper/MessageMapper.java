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
    @Select("SELECT notice.ID, notice.fromID, fromUser.username AS fromUsername, fromUser.image AS fromImage,\n" +
            "notice.timestamp, notice.content, news.title, notice.toID\n" +
            "FROM notice, news, user as fromUser\n" +
            "WHERE news.ID = notice.newsID AND fromUser.ID = notice.fromID AND\n" +
            "notice.newsID IN (SELECT newsID FROM comment\n" +
            "WHERE comment.fromID = #{userID} OR comment.toID = #{userID})\n" +
            "ORDER BY timestamp DESC")
    List<Message> getMessagesByUserID(String userID);

    /**
     * delete it by submit people
     * @param ID
     * @param fromID
     */
    @Delete("DELETE FROM notice WHERE ID = #{arg0} AND fromID = #{arg1}")
    void deleteMessageByID(int ID, String fromID);

}
