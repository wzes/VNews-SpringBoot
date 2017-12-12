package com.mobile.vnews;

import okhttp3.*;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Create by xuantang
 * @date on 12/12/17
 */
public class HttpTest {

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
}
