package com.mobile.vnews;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mobile.vnews.module.BasicResponse;
import com.mobile.vnews.module.UNTmp;
import com.mobile.vnews.module.bean.Comment;
import okhttp3.*;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @author Create by xuantang
 * @date on 12/28/17
 */
public class HttpCommentTest {

    /**
     * Login Test
     */
    @Test
    public void GetCommentTest() {
        OkHttpClient mOkHttpClient = new OkHttpClient();

        //Log.i(TAG, "view: " + userID + " " + newID + " " + JSON.toJSONString(map));

        Request request = new Request.Builder()
                .url("http://localhost:9909/vnews/comment/user/013cc199af6c4d56b9d9")
                .build();
        Call call = mOkHttpClient.newCall(request);

        try {
            Response response = call.execute();
            JSONObject jsonObject = JSONObject.parseObject("{\"toID\":\"e8dbf7128a90432bbc80\",\"fromImage\":\"http://118.89.111.157/vnews/users/placeholder.png\",\"like\":true,\"newID\":0,\"toUsername\":\"Phoenix\",\"likeCount\":1,\"fromID\":\"013cc199af6c4d56b9d9\",\"content\":\"this is reply comment\",\"toImage\":\"http://118.89.111.157/vnews/users/placeholder.png\",\"id\":127,\"floor\":2,\"fromUsername\":\"Hadoop\",\"timestamp\":2017}");

            Comment comment = JSON.toJavaObject(jsonObject, Comment.class);

            System.out.println(comment.isLike());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
