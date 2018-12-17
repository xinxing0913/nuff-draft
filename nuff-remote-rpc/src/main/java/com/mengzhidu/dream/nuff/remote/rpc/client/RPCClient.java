package com.mengzhidu.dream.nuff.remote.rpc.client;

import com.mengzhidu.dream.nuff.remote.rpc.DaemonThread;
import com.mengzhidu.dream.nuff.remote.rpc.request.RPCFuture;
import com.mengzhidu.dream.nuff.remote.rpc.hook.RPCInvokeHook;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * RPC客户端
 */
public class RPCClient implements InvocationHandler {

    protected AtomicInteger atomicInteger = new AtomicInteger(1);

    private long timeout = 0;

    private RPCInvokeHook rpcInvokeHook;

    private String host;

    private Integer port;

    /**
     * 代理对象
     * 通常是由Spring管理的，所以这里必须支持赋值的操作方式
     * 在调用的时候使用强制类型转换的方式进行处理
     */
    private Object object;


    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return invoke(proxy, method.getClass(), method, args);
    }

    /**
     * 连接
     */
    public void connect() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("正在连接中....");


    }

    /**
     * todo 待实现
     * @return
     */
    public RPCFuture call() {
        return new RPCFuture();
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

    public Object invoke(Object proxy, Class clazz, Method method, Object[] args) throws Throwable {
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
}
