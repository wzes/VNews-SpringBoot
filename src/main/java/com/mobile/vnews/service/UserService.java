package com.mobile.vnews.service;


import com.alibaba.fastjson.JSON;
import com.aliyuncs.exceptions.ClientException;
import com.mobile.vnews.mapper.UserMapper;
import com.mobile.vnews.module.BasicResponse;
import com.mobile.vnews.module.bean.User;
import com.mobile.vnews.util.IdUtils;
import com.mobile.vnews.util.SmsSender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Create by xuantang
 * @date on 12/9/17
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    /**
     * log output
     */
    private static Logger log = LogManager.getLogger(UserService.class);

    /**
     *
     * @param user
     * @return
     */
    public BasicResponse<String> register(User user) {
        BasicResponse<String> response = new BasicResponse<>();
        int code = 200;
        String message = "success";
        try {
            // add user
            user.setId(IdUtils.getUUID());
            int res = userMapper.addUser(user);
            log.info(res);
        } catch (Exception e) {
            code = 400;
            message = e.getMessage();
        }
        // set values
        response.setCode(code);
        response.setMessage(message);
        // log
        log.info(response.toString());
        return response;
    }

    /**
     *
     * @param username
     * @return
     */
    public BasicResponse<User> login(String username, String password) {
        BasicResponse<User> response = new BasicResponse<>();
        int code = 200;
        String message = "login success";
        try {
            User res = userMapper.findUserByUsername(username, password);
            if (res == null) {
                code = 403;
                message = "login false";
            }
            response.setContent(res);
            log.info(res);
        } catch (Exception e) {
            code = 500;
            response.setContent(null);
            message = e.getMessage();
        }
        response.setCode(code);
        response.setMessage(message);
        log.info(response.toString());
        return response;
    }

    /**
     *
     * @param phone
     * @return
     */
    public BasicResponse<String> checkPhone(String phone) {
        BasicResponse<String> response = new BasicResponse<>();
        int code = 200;
        String message = "phone available";
        String content = null;
        try {
            int res = userMapper.checkTelephone(phone);
            if(res > 0) {
                code = 200;
                message = "phone not available";
                content = "false";
            } else {
                SmsSender smsSender = new SmsSender();
                try{
                    int randNum = 1 + (int)(Math.random()*((999999-1) + 1));
                    new Thread(() -> {
                        try {
                            smsSender.sendMessage(phone, randNum);
                        } catch (ClientException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }).start();
                    content = String.valueOf(randNum);
    		    }catch (Exception e){
    		        e.getMessage();
    		        content = "false";
            	}
            }
        } catch (Exception e) {
            message = e.getMessage();
            code = 500;
        }
        response.setCode(code);
        response.setMessage(message);
        response.setContent(content);
        return response;
    }

    /**
     *
     * @param user
     * @return
     */
    public BasicResponse<User> updateUser(User user)  {
        BasicResponse<User> response = new BasicResponse<>();
        int code = 200;
        String message = "update success";
        try{
            // System.out.println(JSON.toJSONString(user));
            userMapper.updateUser(user);
            response.setContent(userMapper.getUser(user.getId()));
        }catch (Exception e){
            code = 500;
            message = "update fail";
        }
        response.setCode(code);
        response.setMessage(message);

        return response;
    }

    /**
     * TODO
     * @param userID
     * @param file
     * @return
     */
    public BasicResponse<String> updatePhoto(String userID, MultipartFile file) {
        BasicResponse<String>response=new BasicResponse<>();
        int code = 200;
        String message = "update photo success";
        response.setContent("");
        try {
            String filename = file.getOriginalFilename();
            System.out.println();
            byte[] bytes = file.getBytes();
            BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(
                    new File("/var/www/html/vnews/users/" + userID + filename.substring(filename.lastIndexOf(".")))));
            buffStream.write(bytes);
            buffStream.close();
            String mFilename = "http://118.89.111.157/vnews/users/" + userID +
                    filename.substring(filename.lastIndexOf("."));
            userMapper.updatePhoto(userID, mFilename);

        } catch (IOException |RuntimeException e ) {
            code = 500;
            message = e.getMessage();
        }
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

    /**
     *
     * @param userID
     * @return
     */
    public BasicResponse<User> getUser(String userID) {
        BasicResponse<User> response = new BasicResponse<>();
        int code = 200;
        String message = "get information success";
        response.setContent(new User());
        try {
            User user = userMapper.getUser(userID);
            response.setContent(user);
        } catch (Exception e) {
            code = 500;
            message = e.getMessage();
        }
        response.setCode(code);
        response.setMessage(message);
        return response;
    }
}
