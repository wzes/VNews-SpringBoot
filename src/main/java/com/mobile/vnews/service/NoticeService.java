package com.mobile.vnews.service;
/**
*@Author:kaijie
*@description:
*@Date:11:33 2017/12/14
*/
import com.mobile.vnews.module.BasicResponse;
import com.mobile.vnews.mapper.MessageMapper;
import com.mobile.vnews.module.bean.Message;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class NoticeService{
    @Autowired
    MessageMapper messageMapper;

    /**
     *
     * @param user_id
     * @return
     */
    public BasicResponse<List<Message>> getMessagesByID(String user_id) {
        BasicResponse<List<Message>> response = new BasicResponse<>();
        int code = 200;
        String message = "get notice by user ID success";
        try{
            List<Message> notice = messageMapper.getMessagesByUserID(user_id);
            if(notice.isEmpty()) {
                code = 400;
                message = "didn't have notice";
            }
        }catch (Exception e) {
            code = 500;
            message = e.getMessage();
        }
        return response;
    }
}
