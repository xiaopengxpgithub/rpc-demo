package com.xp.rpc.handler;

import com.xp.rpc.request.RPCRequest;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

//线程处理类
public class ServiceProcessor {

    //处理请求
    //target:客户端要调用的目的对象
    public void process(Socket socket, Object target) {

        OutputStream os = null;
        ObjectOutputStream oos = null;
        try {
            //对象输入流
            InputStream is = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            //获取发送过来的数据
            RPCRequest rpcRequest = (RPCRequest) ois.readObject();

            //调用service接口实现类target中对应的方法
            Object o = invoke(rpcRequest, target);

            //将服务器响应结果写到socket中,返回给客户端
            os = socket.getOutputStream();
            oos = new ObjectOutputStream(os);
            oos.writeObject(o);
            oos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
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
        }

    }

    //实现对userServiceImpl类的方法getUserInfo()的调用
    public Object invoke(RPCRequest rpcRequest, Object target) {
        //客户端传递的方法名称
        String methodName = rpcRequest.getMethodName();
        //客户端传递的参数
        Object[] params = rpcRequest.getParams();

        System.out.printf("正在执行{%s}方法", methodName);

        //获取请求参数类型
        Class[] paramterTypes = new Class[params.length];
        for (int i = 0; i < params.length; i++) {
            paramterTypes[i] = params[i].getClass();
        }

        //定义客户端最终要调用服务器端的目标方法
        try {
            //指定方法的反射对象
            Method method = target.getClass().getDeclaredMethod(methodName, paramterTypes);

            //利用反射实现服务器端指定方法的调用
            Object result = method.invoke(target, params);

            return result;

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }
}
