package com.mobile.vnews.service;
/**
*@Author:kaijie
*@description:
*@Date:11:33 2017/12/14
*/
import com.mobile.vnews.module.BasicResponse;
import com.mobile.vnews.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class NoticeService{
    @Autowired
    NoticeMapper noticeMapper;
}
