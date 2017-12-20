package com.mobile.vnews;

import okhttp3.*;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Create by xuantang
 * @date on 12/12/17
 */
public class HttpNewsTest {

    /**
     * Get News Test
     */
    @Test
    public void GetTest() {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://localhost:9909/vnews/news/hots?count=5")
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
     *
     */
    @Test
    public void GetDetailTest() {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://localhost:9909/vnews/news/detail/4")
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
     * Get News Test
     */
    @Test
    public void GetNewsTest() {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://localhost:9909/vnews/news/america?start=0&count=5")
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
     * Get User like News Test
     */
    @Test
    public void GetUserLikeNewsTest() {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://localhost:9909/vnews/news/e8dbf7128a90432bbc80/likes")
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
     * Check User like News Test
     */
    @Test
    public void CheckUserLikeNewsTest() {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://localhost:9909/vnews/news/e8dbf7128a90432bbc80/like/4")
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
     * Check User like News Test
     */
    @Test
    public void DeleteUserLikeNewsTest() {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://localhost:9909/vnews/news/e8dbf7128a90432bbc80/like/2")
                .header("Content-Type", "application/json")
                .delete()
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
     * Check User like News Test
     */
    @Test
    public void GetUserViewNewsTest() {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://localhost:9909/vnews/news/e8dbf7128a90432bbc80/views")
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
    public void PostLikeTest() {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("user_id", "e8dbf7128a90432bbc80")
                .add("news_id", "1")
                .build();
        Request request = new Request.Builder()
                .url("http://localhost:9909/vnews/news/like")
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
     * Login Test
     */
    @Test
    public void PostViewTest() {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("user_id", "e8dbf7128a90432bbc80")
                .add("news_id", "1")
                .build();
        Request request = new Request.Builder()
                .url("http://localhost:9909/vnews/news/view")
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