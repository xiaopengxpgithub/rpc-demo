package com.xp.rpc;

import com.xp.rpc.model.UserInfo;
import com.xp.rpc.proxy.RPCProxy;
import com.xp.rpc.request.Constant;
import com.xp.rpc.service.IUserService;

/**
 * 用户客户端类
 * ①.通过rpc调用server提供的服务
 */
public class UserClient {

    public static void main(String[] args) {
        System.out.println("client端启动...");

        //通过IUserService的代理对象调用server提供的服务
        RPCProxy rpcProxy = new RPCProxy();
        IUserService iUserService = rpcProxy.getProxy(IUserService.class, Constant.IP_ADDRESS, Constant.PORT);

        UserInfo userInfo = iUserService.getUserInfo();
        System.out.println("返回的结果:" + userInfo.toString());
    }
}

