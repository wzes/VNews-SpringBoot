package com.mobile.vnews.controller;

import com.mobile.vnews.module.BasicResponse;
import com.mobile.vnews.module.UPmp;
import com.mobile.vnews.module.UTmp;
import com.mobile.vnews.module.bean.User;
import com.mobile.vnews.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Create by xuantang
 * @date on 10/31/17
 */

@RestController
@RequestMapping({"/vnews"})
public class UserController {


    @Autowired
    UserService userService;

    
    /**
     * 用户注册
     * @param uPmp
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public BasicResponse<String> register(@RequestBody UPmp uPmp){
        User user = new User(uPmp.getUsername(), uPmp.getPassword(), uPmp.getPhone());
        return userService.register(user);
    }

    /**
     *
     * @param uTmp
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BasicResponse<User> login(@RequestBody UTmp uTmp){
        return userService.login(uTmp.getUsername(), uTmp.getPassword());
    }

    /**
     * TODO
     * 检查电话号码是否能被使用
     * 如果可用则发短信，并且返回验证码
     * @param phone
     * @return
     */
    @RequestMapping(value = "/user/phone/{phone}", method = RequestMethod.GET)
    public BasicResponse<String> checkPhone(@PathVariable("phone") String phone){
        return userService.checkPhone(phone);
    }

    /**
     * TODO
     * 更新用户信息
     * @param user
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    @ResponseBody
    public BasicResponse<String> updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    /**
     * 上传图片
     * @param ID
     * @param file
     * @return
     */
    @RequestMapping(value = "/user/{user_id}/photo", method = RequestMethod.POST)
    public BasicResponse<String>updatePhoto(@PathVariable("user_id")String user_id,
                                            @RequestParam("photo") MultipartFile file){
        return userService.updatePhoto(user_id, file);
    }

    /**
     * 得到用户具体信息
     * @param ID
     * @return
     */
    @RequestMapping(value = "/user/{user_id}", method = RequestMethod.GET)
    public BasicResponse<User> getUser(@PathVariable("user_id") String user_id){
        return userService.getUser(user_id);
    }
}
