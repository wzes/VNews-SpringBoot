package com.mobile.vnews.service;


import com.mobile.vnews.mapper.UserMapper;
import com.mobile.vnews.module.BasicResponse;
import com.mobile.vnews.module.bean.User;
import com.mobile.vnews.util.IdUtils;

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
            user.setID(IdUtils.getUUID());
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
    public BasicResponse<String> login(String username) {
        BasicResponse<String> response = new BasicResponse<>();
        int code = 200;
        String message = "login success";
        try {
            User res = userMapper.findUserByUsername(username);
            response.setContent("");
            log.info(res);
        } catch (Exception e) {
            code = 500;
            response.setContent("");
            message = "login fail";
        }
        response.setCode(code);
        response.setMessage(message);
        log.info(response.toString());
        return response;
    }

    /**
     *
     * @param telephone
     * @return
     */
    public BasicResponse<String> checkPhone(String telephone) {
        BasicResponse<String> response = new BasicResponse<>();
        int code = 400;
        String message = "telephone has been used";
        String content = null;
        try {
            int res = userMapper.checkTelephone(telephone);
            if(res > 0) {
                code = 200;
                message = "telephone available";
                content = "false";
            } else {
                content = "true";
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
    public BasicResponse<String> updateUser(User user)  {
        BasicResponse<String> response = new BasicResponse<>();
        int code = 200;
        String message = "update success";
        response.setContent("");
        try{
            int res = userMapper.updateUser(user);
            if(res == 0) {
                code = 400;
                message = "update fail";
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
     *
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
            byte[] bytes = file.getBytes();
            BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(
                    new File("image/user/" + filename)));
            buffStream.write(bytes);
            buffStream.close();
            userMapper.updatePhoto(userID);

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
