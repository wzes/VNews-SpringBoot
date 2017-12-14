package com.mobile.vnews.controller;
import com.mobile.vnews.module.BasicResponse;
import com.mobile.vnews.module.bean.User;
import com.mobile.vnews.service.UserPreferenceService;
import com.mobile.vnews.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
/**
*@Author:kaijie
*@description:
*@Date:10:56 2017/12/14
*/
import java.util.List;

@RequestMapping("/vnews")
public class UserPreferenceController {
    @Autowired
    UserPreferenceService userPreferenceService;

    /**
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "preference/type/{name}",method = RequestMethod.GET)
    public BasicResponse<Integer> getTypeIDByName(@PathVariable("name") String name){
        return userPreferenceService.getTypeIDByName(name);
    }

    /**
     *
     * @param user_id
     * @param type_id
     * @return
     */
    @RequestMapping(value="preference/{user_id}/{type_id}",method = RequestMethod.POST)
    public BasicResponse<String> addUserPreference(@PathVariable("user_id")String user_id ,@PathVariable("type_id")int type_id){
        return userPreferenceService.addUserPreference(user_id,type_id);
    }

    /**
     *
     * @param user_id
     * @return
     */
    @RequestMapping(value="preference/{user_id}",method = RequestMethod.GET)
    public BasicResponse<List<String>> getUserPreference(@PathVariable("user_id")String user_id ){

        return userPreferenceService.getUserPreference(user_id);
    }

    /**
     *
     * @param user_id
     * @param type_id
     * @return
     */
    @RequestMapping(value="preference/{user_id}/{type_id}",method = RequestMethod.DELETE)
    public BasicResponse<String> deleteUserPreference(@PathVariable("user_id")String user_id ,@PathVariable("type_id")int type_id){
        return userPreferenceService.deleteUserPreference(user_id,type_id);
    }

    /**
     *
     * @param user_id
     * @param type_id
     * @return
     */
    @RequestMapping(value="preference/check/{user_id}/{type_id}",method = RequestMethod.GET)
    public BasicResponse<String> checkUserPreference(@PathVariable("user_id")String user_id ,@PathVariable("type_id")int type_id){
        return userPreferenceService.checkUserPreference(user_id,type_id);
    }
}
