package com.mengzhidu.dream.nuff.remote.rpc.request;

import java.util.Arrays;

/**
 * Created by xinxing on 2018/12/16
 */
public class RPCRequest {

    private int id;

    private Class clazz;

    private String methodName;

    private Object[] args;

    public RPCRequest() {
    }

    public RPCRequest(int id, Class clazz, String methodName, Object[] args) {
        this.id = id;
        this.clazz = clazz;
        this.methodName = methodName;
        this.args = args;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return "RPCRequest{" +
                "id=" + id +
                ", clazz=" + clazz +
                ", methodName='" + methodName + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
