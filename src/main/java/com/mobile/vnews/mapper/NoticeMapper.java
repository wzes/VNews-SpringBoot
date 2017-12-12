package com.mobile.vnews.mapper;

import com.mobile.vnews.module.bean.Notice;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Create by xuantang
 * @date on 10/31/17
 */

@Mapper
public interface NoticeMapper {

    /**
     * Get notice of user
     * @param userID
     * @return
     */
    @Select("SELECT notice.ID, notice.fromID, fromUser.username AS fromUsername, fromUser.image AS fromImage,\n" +
            "  notice.toID, news.title, notice.timestamp, notice.content, newsID\n" +
            "FROM notice, news, user as fromUser\n" +
            "WHERE news.ID = notice.newsID AND notice.fromID = #{userID}\n" +
            " AND fromUser.ID = notice.fromID ORDER BY timestamp DESC")
    List<Notice> getNoticesByUserID(String userID);


    @Delete("DELETE FROM notice WHERE ID = #{ID}")
    void deleteNoticeByID(int ID);
}
