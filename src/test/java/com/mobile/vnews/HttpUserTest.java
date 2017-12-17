package com.mobile.vnews;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mobile.vnews.module.bean.User;
import okhttp3.*;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Create by xuantang
 * @date on 12/12/17
 */
public class HttpUserTest {

    /**
     * Get User Test
     */
    @Test
    public void GetTest() {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://localhost:9909/vnews/user/e8dbf7128a90432bbc80")
                .header("Content-Type", "application/json")
                .build();
        Call call = mOkHttpClient.newCall(request);

        try {
            Response response = call.execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Login Test
     */
    @Test
    public void PostTest() {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("username", "Hadoop")
                .add("password", "12346")
                .build();
        Request request = new Request.Builder()
                .url("http://localhost:9909/vnews/login")
                .header("Content-Type", "application/json")
                .post(formBody)
                .build();
        Call call = mOkHttpClient.newCall(request);

        try {
            Response response = call.execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get User Test
     */
    @Test
    public void CheckPhoneTest() {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://localhost:9909/vnews/user/phone/18001997427")
                .header("Content-Type", "application/json")
                .build();
        Call call = mOkHttpClient.newCall(request);

        try {
            Response response = call.execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Login Test
     */
    @Test
    public void PutUpdateTest() {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        User user = new User();
        user.setId("e8dbf7128a90432bbc80");
        user.setMotto("Hello World!");
        user.setUsername("Phoenix");
        user.setPassword("123456");
        user.setBirthday("1996-04-27");
        user.setImage("abc.jpg");
        user.setEmail("test@163.com");
        user.setInfo("Hello World");
        user.setSex("male");
        user.setTelephone("18001997427");
        System.out.println(user.toString());

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"),
                user.toString());
        Request request = new Request.Builder()
                .url("http://localhost:9909/vnews/user")
                .put(requestBody)
                .build();
        Call call = mOkHttpClient.newCall(request);

        try {
            Response response = call.execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
