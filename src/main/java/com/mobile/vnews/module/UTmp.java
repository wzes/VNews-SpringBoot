package com.mobile.vnews.module;

/**
 * @author Create by xuantang
 * @date on 12/21/17
 */
public class UTmp {
    private String username;
    private String password;
    public UTmp() {

    }
    public UTmp(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
