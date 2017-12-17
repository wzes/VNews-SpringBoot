package com.mobile.vnews.controller;
/**
*@Author:kaijie
*@description:
*@Date:11:26 2017/12/14
*/
import com.mobile.vnews.module.BasicResponse;
import com.mobile.vnews.module.bean.Notice;
import com.mobile.vnews.module.bean.User;
import com.mobile.vnews.service.NoticeService;
import com.mobile.vnews.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/vnews")
public class NoticeController {
    @Autowired
    NoticeService noticeService;

    /**
     * 通过用户id返回提醒消息
     * @param user_id
     * @return
     */
    @RequestMapping(value = "notice/{user_id}",method = RequestMethod.GET)
    public BasicResponse<List<Notice>> getNoticeByUserID(@PathVariable("user_id") String user_id) {
        return noticeService.getNoticeByID(user_id);
    }
}
