package com.mengzhidu.dream.nuff.remote.rpc.netty4.client;

import com.mengzhidu.dream.nuff.remote.rpc.hook.RPCInvokeHook;
import com.mengzhidu.dream.nuff.remote.rpc.proxy.RPCClientAsyncProxy;

import java.lang.reflect.Proxy;

/**
 * RPC客户端代理的构建器
 */
public class RPCClientProxyBuilder<T> {

    private Class<T> clazz;

    private Client rpcClient;

    private long timeout = 0;

    private RPCInvokeHook rpcInvokeHook = null;

    private String host;

    private Integer port;

    private RPCClientProxyBuilder(Class<T> clazz) {
        this.clazz = clazz;
    }

    public RPCClientProxyBuilder<T> timeout(long timeout) {
        if (timeout < 0) {
            throw new IllegalArgumentException("timeout can not be minus");
        }
        this.timeout = timeout;
        return this;
    }

    public RPCClientProxyBuilder<T> hook(RPCInvokeHook hook) {
        this.rpcInvokeHook = hook;
        return this;
    }

    public RPCClientProxyBuilder<T> connect(String host, Integer port) {
        this.host = host;
        this.port = port;
        return this;
    }

    @SuppressWarnings("unchecked")
    public T build()  {
        rpcClient = new Client();
        rpcClient.connect();
        try {
            rpcClient.doInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Object object = Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class<?>[]{clazz},
                rpcClient
        );

        return (T)object;
    }

    public RPCClientAsyncProxy buildAsyncProxy() {
        rpcClient = new Client();
        rpcClient.connect();

        return new RPCClientAsyncProxy(rpcClient);
    }

    public static <T> RPCClientProxyBuilder<T> create(Class<T> targetClass) {
        return new RPCClientProxyBuilder<>(targetClass);
    }
}
