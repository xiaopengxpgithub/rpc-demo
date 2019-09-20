package com.xp.rpc.request;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * rpc请求对象,通过该对象对数据进行包装,在网络中传输
 */
public class RPCRequest implements Serializable {

    private String className;  //被代理的接口
    private String methodName; //被代理的接口当前所执行的方法
    private Object[] params;   //被代理的接口当前所执行的方法的参数

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public RPCRequest(String className, String methodName, Object[] params) {
        this.className = className;
        this.methodName = methodName;
        this.params = params;
    }
}
