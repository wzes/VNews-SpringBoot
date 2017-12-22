package com.mobile.vnews.module;

/**
 * @author Create by xuantang
 * @date on 12/21/17
 */
public class UNTmp {
    private String user_id;
    private String news_id;
    public UNTmp() {

    }
    public UNTmp(String user_id, String news_id) {
        this.user_id = user_id;
        this.news_id = news_id;
    }
    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getNews_id() {
        return news_id;
    }

    public void setNews_id(String news_id) {
        this.news_id = news_id;
    }
}
