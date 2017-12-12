package com.mobile.vnews.mapper;

import com.mobile.vnews.module.bean.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {

    /**
     * Only return main floor
     * @param newsID
     * @return
     */
    @Select("SELECT comment.ID, fromID, fromUser.image as fromImage, fromUser.username as fromUsername,\n" +
            "  content, timestamp, newsID, floor,\n" +
            "  (SELECT count(*) FROM like_comment WHERE commentID = comment.ID) as likeCount\n" +
            "FROM comment, user as fromUser\n" +
            "WHERE fromUser.ID = comment.fromID AND newsID = #{newsID} " +
            "AND (toID IS NULL OR toID = '') ORDER BY floor ASC")
    List<Comment>getCommentByNewsID(int newsID);

    /**
     * Get comments from one floor
     * @param floor
     * @return
     */
    @Select("SELECT comment.ID, fromID, fromUser.image as fromImage, fromUser.username as fromUsername,\n" +
            "  toID, toUser.image as toImage, toUser.username as toUsername,\n" +
            "  content, timestamp, newsID, floor,\n" +
            "  (SELECT count(*) FROM like_comment WHERE commentID = comment.ID) as likeCount\n" +
            "FROM comment, user as fromUser, user as toUser\n" +
            "WHERE fromUser.ID = comment.fromID AND toUser.ID = comment.toID AND newsID = #{arg0}" +
            " AND floor = #{arg1} ORDER BY timestamp ASC")
    List<Comment>getCommentByNewsIDAndFloor(int newsID, int floor);

    // TODO
    @Insert("INSERT INTO like_comment (userID, commentID) VALUES (#{arg0}, #{arg1})")
    int addLikeComment(String userID, int commentID);

    // TODO
    @Delete("DELETE FROM like_comment WHERE userID = #{arg0} AND commentID = #{arg1}")
    int deleteLikeComment(String userID, int commentID);

    // TODO
    @Select("SELECT count(*) FROM like_comment WHERE userID = #{arg0} AND commentID = #{arg1}")
    int checkLikeComment(String userID, int commentID);
}
