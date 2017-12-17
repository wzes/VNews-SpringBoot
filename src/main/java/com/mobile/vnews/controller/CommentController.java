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
public class CommentController {
    @Autowired
    CommentService commentService;

    /**
     * 得到主楼层的评论
     * @param news_id
     * @return
     */
    @RequestMapping("comment/{news_id}")
    public BasicResponse<List<Comment>> getMainComments(@PathVariable("news_id") int news_id) {
        return commentService.getMainFloor(news_id);
    }

    /**
     * 得到某一层的详细评论
     * @param news_id
     * @param floor
     * @return
     */
    @RequestMapping("comment/{news_id}/{floor}")
    public BasicResponse<List<Comment>> getDetailComments(@PathVariable("news_id")int news_id,
                                                          @PathVariable("floor")int floor) {
        return commentService.getCommentByNewsIDAndFloor(news_id, floor);
    }
}
