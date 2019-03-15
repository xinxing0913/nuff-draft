package com.mengzhidu.dream.nuff.remote.rpc.netty4.proxy;

import com.mengzhidu.dream.nuff.remote.rpc.hook.RPCInvokeHook;
import com.mengzhidu.dream.nuff.remote.rpc.netty4.server.RPCServer;

/**
 * Created by xinxing on 2018/12/16
 */
public class RPCServerBuilder {

    private Class<?> interfaceClass;

    private Object serviceProvider;

    private int port;

    private int threads;

    private RPCInvokeHook rpcInvokeHook;

    public static RPCServerBuilder create() {
        return new RPCServerBuilder();
    }

    public RPCServerBuilder setInterfaceClass(Class<?> interfaceClass) {
        this.interfaceClass = interfaceClass;
        return this;
    }

    public RPCServerBuilder serviceProvider(Object serviceProvider) {
        this.serviceProvider = serviceProvider;
        return this;
    }

    public RPCServerBuilder port(int port) {
        this.port = port;
        return this;
    }

    public RPCServerBuilder setThreads(int threads) {
        this.threads = threads;
        return this;
    }

    public RPCServerBuilder rpcInvokeHook(RPCInvokeHook rpcInvokeHook) {
        this.rpcInvokeHook = rpcInvokeHook;
        return this;
    }

    public RPCServer build() {
        if (threads <= 0) {
            threads = Runtime.getRuntime().availableProcessors();
        }

        //RPCServer rpcServer = new RPCServer(interfaceClass, serviceProvider, port, threads, rpcInvokeHook);
        //return rpcServer;
        return null;
    }
}
