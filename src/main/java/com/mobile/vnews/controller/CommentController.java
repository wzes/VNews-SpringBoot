package com.mobile.vnews.controller;
import com.mobile.vnews.module.BasicResponse;
import com.mobile.vnews.module.bean.*;
import com.mobile.vnews.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author xuantang
 */
@RequestMapping("/vnews")
@RestController
public class CommentController {

    @Autowired
    CommentService commentService;
    /**
     * 得到主楼层的评论
     * @param news_id
     * @return
     */
    @RequestMapping(value = "/comment/{news_id}", method = RequestMethod.GET)
    public BasicResponse<List<Comment>> getMainComments(@PathVariable("news_id") int news_id) {
        return commentService.getMainFloor(news_id);
    }

    /**
     * 得到某一层的详细评论
     * @param news_id
     * @param floor
     * @return
     */
    @RequestMapping(value = "/comment/{news_id}/{floor}",method = RequestMethod.GET)
    public BasicResponse<List<Comment>> getDetailComments(@PathVariable("news_id")int news_id,
                                                          @PathVariable("floor")int floor) {
        return commentService.getCommentByNewsIDAndFloor(news_id, floor);
    }

    /**
     * 给某条评论点赞
     * @param user_id
     * @param comment_id
     * @return
     */
    @RequestMapping(value = "/comment/{user_id}/like/{comment_id}", method = RequestMethod.GET)
    public BasicResponse<String> likeComment(@PathVariable("user_id") String user_id,
                                             @PathVariable("comment_id") int comment_id) {
        return commentService.likeComments(user_id, comment_id);
    }

    /**
     * 取消点赞
     * @param user_id
     * @param comment_id
     * @return
     */
    @RequestMapping(value = "/comment/{user_id}/dislike/{comment_id}", method = RequestMethod.GET)
    public BasicResponse<String> dislikeComment(@PathVariable("user_id")String user_id,
                                                  @PathVariable("comment_id") int comment_id) {
        return commentService.dislikeComments(user_id,comment_id);
    }

    /**
     * 查看是否点赞
     * @param user_id
     * @param comment_id
     * @return
     */
    @RequestMapping(value="/comment/{user_id}/check/{comment_id}", method = RequestMethod.GET)
    public BasicResponse<String> checkComment(@PathVariable("user_id") String user_id,
                                               @PathVariable("comment_id") int comment_id) {
        return commentService.checkComments(user_id, comment_id);
    }

    /**
     * 获取
     * TODO
     * @param user_id
     * @return
     */
    @RequestMapping(value="/comment/user/{user_id}", method = RequestMethod.GET)
    public BasicResponse<List<Comment>> getMyComments(@PathVariable("user_id") String user_id) {
        return commentService.getMyComment(user_id);
    }
}
