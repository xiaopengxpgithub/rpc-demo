package com.xp.rpc.proxy;

import com.xp.rpc.request.RPCInvoke;
import com.xp.rpc.request.RPCRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * RPC代理类,使用jdk对接口生成代理对象
 */
public class RPCProxy implements InvocationHandler {

    private String ip;
    private int port;


    //得到被代理对象
    public <T> T getProxy(Class<T> classInterfaces, String ip, int port) {
        this.ip = ip;
        this.port = port;

        return (T) Proxy.newProxyInstance(
                classInterfaces.getClassLoader(),
                new Class<?>[]{classInterfaces},
                this);
    }

    //通过此方法实现具体的rpc远程调用
    //在调用被代理的接口中的某个方法时,会被该invoke()方法拦截,实际上就是在执行代理对象中的invoke()方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //request请求对象
        String className = method.getDeclaringClass().getName(); //通过被代理的接口当前执行的方法获取到具体的被代理的接口的名称
        String methodName = method.getName();  //获取被代理的接口当前执行的方法的名称
        RPCRequest rpcRequest = new RPCRequest(className, methodName, args == null ? new Object[0] : args);

        //调用远程方法
        RPCInvoke rpcInvoke = new RPCInvoke(this.ip, this.port);
        Object result = rpcInvoke.invoke(rpcRequest);

        return result;
    }
}
