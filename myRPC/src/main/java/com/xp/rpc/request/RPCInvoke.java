package com.xp.rpc.request;

import java.io.*;
import java.net.Socket;

/**
 * rpc远程调用
 */
public class RPCInvoke {

    private String IP;
    private int port;

    public RPCInvoke(String IP, int port) {
        this.IP = IP;
        this.port = port;
    }

    //通过rpcRequest对象中指定的参数,实现对远程服务的调用
    public Object invoke(RPCRequest rpcRequest) {

        Socket socket = null;

        OutputStream os = null;
        ObjectOutputStream oos = null;

        InputStream is = null;
        ObjectInputStream ois = null;

        try {
            //通过socket建立网络连接
            socket = new Socket(this.IP, this.port);

            //获取一个输出流,用户向远程服务器发送数据(请求参数)
            os = socket.getOutputStream();
            //转换成对象输出流,用户传输对象数据
            oos = new ObjectOutputStream(os);
            oos.writeObject(rpcRequest);
            oos.flush();

            //获取远程服务器的响应数据,也就是远程服务调用对应的方法之后的返回值
            //先获取一个对象输入流
            is = socket.getInputStream();
            ois = new ObjectInputStream(is);

            Object object = ois.readObject();

            return object;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }
}
