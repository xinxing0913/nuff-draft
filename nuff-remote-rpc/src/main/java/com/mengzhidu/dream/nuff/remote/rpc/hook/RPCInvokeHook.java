package com.mengzhidu.dream.nuff.remote.rpc.hook;

/**
 * Created by xinxing on 2018/12/16
 */
public interface RPCInvokeHook {

    void beforeInvoke(Class Clazz, String methodName, Object[] args);

    void afterInvoke(Class clazz, String methodName, Object[] args, Object result);
}
