package com.xp.rpc.server;

import com.xp.rpc.handler.ServerHandler;
import com.xp.rpc.request.Constant;
import com.xp.rpc.service.UserServiceImpl;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * rpc服务端
 */
public class SocketServer {

    public static void main(String[] args) {
        System.out.println("server端启动...");
        //处理客户端请求
        ServerHandler serverHandler=new ServerHandler();
        serverHandler.handler(new UserServiceImpl(), Constant.PORT);
    }
}
