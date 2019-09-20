package com.xp.rpc.service;

import com.xp.rpc.model.UserInfo;

/**
 * 用户服务端类
 * ①.对用户服务接口的操作进行具体实现
 * ②.对外提供user对应的服务
 */
public class UserServiceImpl implements IUserService {

    @Override
    public UserInfo getUserInfo() {
        //执行具体的业务逻辑
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1000);
        userInfo.setUserName("李四");

        return userInfo;
    }
}
