package com.xp.rpc.model;

import java.io.Serializable;

/**
 * 用户信息类,由于需要在网络上传输,所以要进行序列化
 */
public class UserInfo implements Serializable {

    private Integer id;
    private String userName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserInfo(){

    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }
}
