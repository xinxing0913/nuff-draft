package com.mengzhidu.dream.nuff.remote.rpc.builder;

import com.mengzhidu.dream.nuff.remote.rpc.client.Client;
import com.mengzhidu.dream.nuff.remote.rpc.hook.RPCInvokeHook;

import java.lang.reflect.Proxy;

/**
 * RPC客户端代理的构建器
 */
public class ClientProxyBuilder<T> {

    private Class<T> clazz;

    private Client rpcClient;

    private long timeout = 0;

    private RPCInvokeHook rpcInvokeHook = null;

    private String host;

    private Integer port;

    private ClientProxyBuilder(Class<T> clazz) {
        this.clazz = clazz;
    }

    public ClientProxyBuilder<T> timeout(long timeout) {
        if (timeout < 0) {
            throw new IllegalArgumentException("timeout can not be minus");
        }
        this.timeout = timeout;
        return this;
    }

    public ClientProxyBuilder<T> hook(RPCInvokeHook hook) {
        this.rpcInvokeHook = hook;
        return this;
    }

    public ClientProxyBuilder<T> connect(String host, Integer port) {
        this.host = host;
        this.port = port;
        return this;
    }

    public ClientProxyBuilder<T> client(Client client) {
        this.rpcClient = client;
        return this;
    }

    @SuppressWarnings("unchecked")
    public T build()  {
        rpcClient.connect();
        Object object = Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class<?>[]{clazz},
                rpcClient
        );

        return (T)object;
    }

    public static <T> ClientProxyBuilder<T> create(Class<T> targetClass) {
        return new ClientProxyBuilder<>(targetClass);
    }
}
