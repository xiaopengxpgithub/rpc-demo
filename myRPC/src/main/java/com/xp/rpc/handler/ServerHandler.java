package com.xp.rpc.handler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 在服务器端执行,处理客户端请求
 */
public class ServerHandler {

    //target:表示客户端最终要调用的服务器端的对象
    public void handler(Object target, int port) {
        //线程处理类
        ServiceProcessor serviceProcessor = new ServiceProcessor();

        try {
            //指定服务器要监听的(客户端)端口
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("server端启动完成,等待接收客户端请求:");
            while (true) {
                //接受客户端请求(阻塞)
                //等待接收客户端请求
                Socket socket = serverSocket.accept();

                //异步处理客户端请求
                new Thread(() -> {
                    serviceProcessor.process(socket, target);
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
