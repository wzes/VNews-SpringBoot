package com.mobile.vnews.mapper;

import com.mobile.vnews.module.bean.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author xuantang
 */
@Mapper
public interface CommentMapper {

    /**
     * Only return main floor
     * @param newsID
     * @return
     */
    @Select("SELECT comment.ID AS id, fromID, fromUser.image as fromImage, fromUser.username as fromUsername,\n" +
            "  content, timestamp, newsID, floor,\"toID\",\"toUsername\", \"toImage\", \n" +
            "  (SELECT count(*) FROM like_comment WHERE commentID = comment.ID) as likeCount\n" +
            "FROM comment, user as fromUser\n" +
            "WHERE fromUser.ID = comment.fromID AND newsID = #{newsID} " +
            "AND (toID IS NULL OR toID = '') ORDER BY floor ASC")
    List<Comment> getCommentByNewsID(int newsID);

    /**
     *
     * @param newsID
     * @param userID
     * @return
     */
    @Select("SELECT comment.ID AS id, fromID, fromUser.image as fromImage, fromUser.username as fromUsername,\n" +
            "  content, timestamp, newsID, floor, \"toID\",\"toUsername\", \"toImage\",\n" +
            "  (SELECT count(*) FROM like_comment WHERE commentID = comment.ID) as likeCount,\n" +
            "  CASE (SELECT count(*)\n" +
            "              FROM like_comment\n" +
            "              WHERE commentID = comment.ID AND userID = #{userID})\n" +
            "           WHEN 0 THEN FALSE\n" +
            "           ELSE TRUE\n" +
            "          END AS isLike\n" +
            "FROM comment, user as fromUser\n" +
            "WHERE fromUser.ID = comment.fromID AND newsID = #{newsID} AND (toID IS NULL OR toID = '') ORDER BY floor ASC")
    List<Comment> getCommentByNewsIDAndUserID(@Param("newsID") int newsID,
                                              @Param("userID") String userID);
    /**
     *
     * @param userID
     * @return
     */
    @Select("SELECT comment.ID, fromID, fromUser.image as fromImage, fromUser.username as fromUsername,\n" +
            "  toID, toUser.image as toImage, toUser.username as toUsername,\n" +
            "  content, timestamp, newsID, floor,\n" +
            "  (SELECT count(*) FROM like_comment WHERE commentID = comment.ID) as likeCount\n" +
            "FROM comment, user as fromUser, user as toUser\n" +
            "WHERE fromUser.ID = comment.fromID AND toUser.ID = comment.toID AND\n" +
            "      comment.fromID = #{userID} ORDER BY timestamp ASC")
    List<Comment> getCommentsByUserID(@Param("userID") String userID);

    /**
     *
     * @param userID
     * @return
     */
    @Select("SELECT comment.ID, fromID, fromUser.image as fromImage, fromUser.username as fromUsername,\n" +
            "  toID, toUser.image as toImage, toUser.username as toUsername,\n" +
            "  content, timestamp, newsID, floor,\n" +
            "  (SELECT count(*) FROM like_comment WHERE commentID = comment.ID) as likeCount,\n" +
            "   CASE (SELECT count(*)\n" +
            "              FROM like_comment\n" +
            "              WHERE commentID = comment.ID AND userID = #{userID})\n" +
            "           WHEN 0 THEN FALSE\n" +
            "           ELSE TRUE\n" +
            "          END AS isLike\n" +
            "FROM comment, user as fromUser, user as toUser\n" +
            "WHERE fromUser.ID = comment.fromID AND toUser.ID = comment.toID AND\n" +
            "      comment.fromID = #{userID} ORDER BY timestamp ASC")
    List<Comment> getCommentsByUserIDAndUserID(@Param("userID") String userID);

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
            "WHERE fromUser.ID = comment.fromID AND toUser.ID = comment.toID AND newsID =#{newsID}" +
            " AND floor = #{floor} ORDER BY timestamp ASC")
    List<Comment> getCommentByNewsIDAndFloor(@Param("newsID") int newsID,
                                             @Param("floor") int floor);

    /**
     *
     * @param newsID
     * @param floor
     * @param userID
     * @return
     */
    @Select("SELECT comment.ID, fromID, fromUser.image as fromImage, fromUser.username as fromUsername,\n" +
            "  toID, toUser.image as toImage, toUser.username as toUsername,\n" +
            "  content, timestamp, newsID, floor,\n" +
            "  (SELECT count(*) FROM like_comment WHERE commentID = comment.ID) as likeCount," +
            "CASE (SELECT count(*)\n" +
            "              FROM like_comment\n" +
            "              WHERE commentID = comment.ID AND userID = #{userID})\n" +
            "           WHEN 0 THEN FALSE\n" +
            "           ELSE TRUE\n" +
            "          END AS isLike\n" +
            "FROM comment, user as fromUser, user as toUser\n" +
            "WHERE fromUser.ID = comment.fromID AND toUser.ID = comment.toID AND newsID = #{newsID}" +
            " AND floor = #{floor} ORDER BY timestamp ASC")
    List<Comment> getCommentByNewsIDAndFloorAndUserID(@Param("newsID") int newsID,
                                                      @Param("floor") int floor,
                                                      @Param("userID") String userID);

    /**
     * 点赞
     * @param userID
     * @param commentID
     * @return
     */
    @Insert("INSERT INTO like_comment(userID, commentID, timestamp) VALUES (#{arg0}, #{arg1}, #{arg2})")
    int addLikeComment(String userID, int commentID, long timestamp);

    /**
     * 取消点赞
     * @param userID
     * @param commentID
     * @return
     */
    @Delete("DELETE FROM like_comment WHERE userID = #{arg0} AND commentID = #{arg1}")
    int deleteLikeComment(String userID, int commentID);

    /**
     * 检查是否点赞
     * @param userID
     * @param commentID
     * @return
     */
    @Select("SELECT count(*) FROM like_comment WHERE userID = #{arg0} AND commentID = #{arg1}")
    int checkLikeComment(String userID, int commentID);

    /**
     * 添加评论
     * @param comment
     * @return
     */
    @Insert("INSERT INTO comment (fromID,toID,content,newsID,floor) VALUES (#{fromID}, #{toID}, #{content}, #{newsID}, #{floor})")
    int addComment(Comment comment);
}
