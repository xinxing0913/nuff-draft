package com.mengzhidu.dream.nuff.remote.rpc.client;

import com.mengzhidu.dream.nuff.remote.rpc.config.ClientConfig;
import com.mengzhidu.dream.nuff.remote.rpc.task.DaemonThread;
import com.mengzhidu.dream.nuff.remote.rpc.handler.ClientChannelInactiveHandler;
import com.mengzhidu.dream.nuff.remote.rpc.handler.ClientResponseHandler;
import com.mengzhidu.dream.nuff.remote.rpc.hook.RPCInvokeHook;
import com.mengzhidu.dream.nuff.remote.rpc.request.RPCFuture;

import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by xinxing on 2019/3/15
 */
public abstract class AbstractClient implements Client{

    protected AtomicInteger atomicInteger = new AtomicInteger(1);

    protected ClientChannelInactiveHandler inactiveHandler;

    protected ClientResponseHandler responseHandler = new ClientResponseHandler(1);

    protected ClientConfig clientConfig;

    private RPCInvokeHook rpcInvokeHook;

    protected int timeout;

    @Override
    public void connect() {

    }

    @Override
    public void config(ClientConfig clientConfig) {
        this.clientConfig = clientConfig;
    }

    @Override
    public void init(ClientConfig clientConfig) throws Exception{
        if (inactiveHandler == null) {
            inactiveHandler = new ClientChannelInactiveHandler(this);
        }
        this.clientConfig = clientConfig;
        this.timeout = timeout;
        doInit();
    }

    protected abstract boolean doInit() throws Exception;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class clazz = method.getDeclaringClass();

        if (rpcInvokeHook != null) {
            rpcInvokeHook.beforeInvoke(clazz, method.getName(), args);
        }

        RPCFuture rpcFuture = call(clazz, method.getName(), args);
        Object result;
        if (timeout == 0) {
            result = rpcFuture.get();
        } else {
            result = rpcFuture.get(timeout);
        }

        if (rpcInvokeHook != null) {
            rpcInvokeHook.afterInvoke(clazz, method.getName(), args, result);
        }

        return result;
    }


    /**
     * 异步调用
     */
    public RPCFuture call(Class clazz, String methodName, Object ... args) {

        RPCFuture rpcFuture = new RPCFuture();
        DaemonThread thread = new DaemonThread(rpcFuture, clazz, methodName, args);
        thread.start();;

        return rpcFuture;
    }
}
