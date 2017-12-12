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
    @Select("SELECT comment.ID, fromID, toID, content, timestamp, newsID, floor, user.username, " +
            "user.image, user.ID,\n" +
            "(SELECT count(*) FROM like_comment WHERE commentID = comment.ID) as likeCount\n" +
            "FROM comment, user\n" +
            "WHERE user.ID = comment.fromID AND newsID = #{newsID} AND (toID IS NULL OR toID = '') " +
            "ORDER BY floor ASC;")
    List<Comment>getComment(int newsID);

    /**
     * Get comments from one floor
     * @param floor
     * @return
     */
    @Select("SELECT comment.ID, fromID, toID, content, timestamp, newsID, floor, user.username, " +
            "user.image, user.ID,\n" +
            "  (SELECT count(*) FROM like_comment WHERE commentID = comment.ID) as likeCount\n" +
            "FROM comment, user\n" +
            "WHERE user.ID = comment.fromID AND newsID = #{newsID} AND floor = #{floor} " +
            "ORDER BY timestamp ASC;")
    List<Comment>getCommentByFloor(int newsID, int floor);

    // TODO
    @Insert("INSERT INTO like_comment (userID, commentID) VALUES (#{userID}, #{commentID})")
    int addLikeComment(String userID, int commentID);

    // TODO
    @Delete("DELETE FROM like_comment WHERE userID = #{userID} AND commentID = #{commentID}")
    int deleteLikeComment(String userID, int commentID);

    // TODO
    @Select("SELECT count(*) FROM like_comment WHERE userID = #{userID} AND commentID = #{commentID}")
    int checkLikeComment(String userID, int commentID);
}
