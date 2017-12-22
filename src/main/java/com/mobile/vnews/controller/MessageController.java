package com.mobile.vnews.controller;
/**
*@Author:kaijie
*@description:
*@Date:11:26 2017/12/14
*/
import com.mobile.vnews.module.BasicResponse;
import com.mobile.vnews.module.bean.Message;
import com.mobile.vnews.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/vnews")
@RestController
public class MessageController {
    @Autowired
    NoticeService noticeService;

    /**
     * 通过用户id返回提醒消息
     * @param user_id
     * @return
     */
    @RequestMapping(value = "/notice/{user_id}",method = RequestMethod.GET)
    public BasicResponse<List<Message>> getNoticeByUserID(@PathVariable("user_id") String user_id) {
        return noticeService.getMessagesByID(user_id);
    }
}
