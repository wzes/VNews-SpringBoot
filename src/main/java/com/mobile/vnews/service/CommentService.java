package com.mobile.vnews.service;

import com.mobile.vnews.mapper.CommentMapper;
import com.mobile.vnews.mapper.NewsMapper;
import com.mobile.vnews.module.BasicResponse;
import com.mobile.vnews.module.bean.News;
import com.mobile.vnews.module.bean.Comment;
import com.mobile.vnews.module.bean.UserPreference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
 * @author xuantang
 */
@Service
public class CommentService {
    @Autowired
    CommentMapper commentMapper;

    /**
     * 只返回主楼层的评论
     * @param news_id
     * @return
     */
    public BasicResponse<List<Comment>> getMainFloor(int news_id) {
        BasicResponse<List<Comment>> response = new BasicResponse<>();
        int code = 200;
        String message="return main floor information success";
        try{
            List<Comment> comments=commentMapper.getCommentByNewsID(news_id);
            if(comments.isEmpty()){
                code = 400;
                message = "数据库查询语句错误";
            }else{
                response.setContent(comments);
            }
        }catch (Exception e){
            code = 500;
            message = e.getMessage();
        }
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

    /**
     * 返回某个新闻某个楼层所有的评论
     * @param news_id
     * @param floor
     * @return
     */
    public BasicResponse<List<Comment>> getCommentByNewsIDAndFloor(int news_id, int floor) {
        BasicResponse<List<Comment>> response = new BasicResponse<>();
        int code = 200;
        String message = "return all comments in one floor success";
        try {
            List<Comment> comments = commentMapper.getCommentByNewsIDAndFloor(news_id, floor);
            if (comments.isEmpty()) {
                code = 400;
                message = "数据库语句查询错误";
            } else {
                response.setContent(comments);
            }
        } catch ( Exception e) {
            code=500;
            message=e.getMessage();
        }
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

    /**
     * 给某条评论点赞
     * @param user_id
     * @param comment_id
     * @return
     */
    public BasicResponse<String> likeComments(String user_id,int comment_id) {
        BasicResponse<String> response=new BasicResponse<>();
        int code=200;
        String message="like comment success";
        try {
            int res=commentMapper.addLikeComment(user_id,comment_id);
            if(res==0) {
                code=400;
                message="数据库查询错误";
            }
        } catch ( Exception e) {
            code=500;
            message=e.getMessage();
        }
        response.setCode(code);
        response.setMessage(message);
        return  response;
    }

    /**
     * 取消点赞
     * @param user_id
     * @param comment_id
     * @return
     */
    public BasicResponse<String> dislikeComments(String user_id,int comment_id) {
        BasicResponse<String> response=new BasicResponse<>();
        int code=200;
        String message="dislike comment success";
        try {
            int res=commentMapper.deleteLikeComment(user_id,comment_id);
            if(res==0) {
                code=400;
                message="数据库查询错误";
            }
        }catch (Exception e) {
            code=500;
            message=e.getMessage();
        }
        response.setCode(code);
        response.setMessage(message);
        return  response;
    }

    /**
     * 检查是否点赞
     * @param user_id
     * @param comment_id
     * @return
     */
    public BasicResponse<String> checkComments(String user_id, int comment_id) {
        BasicResponse<String> response = new BasicResponse<>();
        int code = 200;
        String message="have liked the comment";
        try {
            Comment comment = commentMapper.checkLikeComment(user_id, comment_id);
            if(comment==null) {
                code = 400;
                message = "have not liked the comment";
            }
        }catch ( Exception e) {
            code = 500;
            message = e.getMessage();
        }
        response.setCode(code);
        response.setMessage(message);
        return  response;
    }
    public BasicResponse<String> addComments(Comment comment) {
        BasicResponse<String> response = new BasicResponse<>();
        int code = 200;
        String message = "add comment success";
        try {
            int res = commentMapper.addComment(comment);
            if (res == 0) {
                code = 400;
                message = "数据库插入错误";
            }
        } catch (Exception e) {
            code = 500;
            message = e.getMessage();
        }
        response.setCode(code);
        response.setMessage(message);
        return  response;
    }
}
