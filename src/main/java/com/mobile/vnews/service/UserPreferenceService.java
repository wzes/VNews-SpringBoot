package com.mobile.vnews.service;

import com.mobile.vnews.mapper.UserPreferenceMapper;
import com.mobile.vnews.module.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
/**
*@Author:kaijie
*@description:
*@Date:10:56 2017/12/14
*/
public class UserPreferenceService {
    @Autowired
    UserPreferenceMapper userPreferenceMapper;

    /**
     * 通过种类名称寻找ID
     * @param name
     * @return
     */
    public BasicResponse<Integer> getTypeIDByName (String name) {
        BasicResponse<Integer> response = new BasicResponse<>();
        int code = 200;
        String message = "get typeID success";
        try{
            Integer res = userPreferenceMapper.getTypeIDByName(name);
            if(res == 0){
                code = 400;
                message = "数据库查询错误";
            }else{
                response.setContent(res);
            }
        }catch (Exception e){
            code = 500;
            message = e.getMessage();
        }
        response.setCode(code);
        response.setMessage(message);
        return  response;
    }

    /**
     * 添加用户喜爱新闻的种类
     * @param user_id
     * @param type_id
     * @return
     */
    public BasicResponse<String> addUserPreference(String user_id,int type_id) {
        BasicResponse<String> response = new BasicResponse<>();
        int code = 200;
        String message = "add user preference success";
        try {
            int res=userPreferenceMapper.addUserPreference(user_id, type_id);
            if (res == 0) {
                code = 400;
                message = "数据库语句执行错误";
            }
        } catch ( Exception e) {
            code=500;
            message=e.getMessage();
        }
        response.setCode(code);
        response.setMessage(message);
        response.setContent("");
        return  response;
    }

    /**
     * 得到一个用户喜爱新闻种类的列表
     * @param user_id
     * @return
     */
    public BasicResponse<List<String>> getUserPreference(String user_id) {
        BasicResponse<List<String>> response = new BasicResponse<>();
        int code = 200;
        String message = "get user preference success";
        try {
            List<String> res=userPreferenceMapper.getUserPreference(user_id);
            if (res.isEmpty()) {
                code = 400;
                message="there is no preference category";
            } else {
                response.setContent(res);
            }
        } catch (Exception e) {
            code = 500;
            message = e.getMessage();
        }
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

    /**
     * 检查用户喜爱新闻的种类
     * @param user_id
     * @param type_id
     * @return
     */
    public BasicResponse<String> checkUserPreference(String user_id, int type_id) {
        BasicResponse<String> response = new BasicResponse<>();
        int code = 200;
        String message = "the preference exists";
        try {
            int res = userPreferenceMapper.checkUserPreference(user_id, type_id);
            if (res == 0) {
                code = 400;
                message = "the preference does not exist";
            }
        } catch (Exception e) {
            code = 500;
            message = e.getMessage();
        }
        response.setCode(code);
        response.setMessage(message);
        return  response;
    }

    /**
     * 删除用户喜爱新闻的种类删
     * @param user_id
     * @param type_id
     * @return
     */
    public BasicResponse<String> deleteUserPreference(String user_id, int type_id) {
        BasicResponse<String> response = new BasicResponse<>();
        int code = 200;
        String message = "delete the preference success";
        try{
            int res = userPreferenceMapper.deleteUserPreference(user_id, type_id);
            if (res == 0) {
                code = 400;
                message = "数据库语句执行错误";
            }
        } catch (Exception e) {
            code = 500;
            message = e.getMessage();
        }
        response.setCode(code);
        response.setMessage(message);
        return response;
    }
}
