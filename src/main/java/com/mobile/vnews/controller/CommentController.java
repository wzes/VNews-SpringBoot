package com.mobile.vnews.controller;
import com.alibaba.fastjson.JSON;
import com.mobile.vnews.mapper.UserMapper;
import com.mobile.vnews.module.BasicResponse;
import com.mobile.vnews.module.bean.*;
import com.mobile.vnews.service.CommentService;
import com.mobile.vnews.service.NewsService;
import com.mobile.vnews.service.UserService;
import org.apache.logging.log4j.core.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RequestMapping("/vnews")
public class CommentController {
    @Autowired
    CommentService commentService;
    //得到主楼层的评论
    @RequestMapping("comment/{news_id}")
    public BasicResponse<List<Comment>> getMainComments(@PathVariable("news_id") int news_id){
        return commentService.getMainFloor(news_id);
    }
    //得到某一层的详细评论
    @RequestMapping("comment/{news_id}/{floor}")
    public BasicResponse<List<Comment>> getDetailComments(@PathVariable("news_id")int news_id,@PathVariable("floor")int floor){
        return  commentService.getCommentByNewsIDAndFloor(news_id, floor);
    }
}
