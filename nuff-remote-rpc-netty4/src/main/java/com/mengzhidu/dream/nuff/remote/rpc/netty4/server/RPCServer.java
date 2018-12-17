package com.mengzhidu.dream.nuff.remote.rpc.netty4.server;

import com.mengzhidu.dream.nuff.remote.rpc.hook.RPCInvokeHook;
import com.mengzhidu.dream.nuff.remote.rpc.netty4.common.RPCRequestWrapper;


/**
 * Created by xinxing on 2018/12/16
 */
public class RPCServer {

    protected Class<?> interfaceClass;

    protected Object beanObject;

    protected int port;

    protected int threads;

    protected RPCInvokeHook rpcInvokeHook;

    protected ServerRequestHandler serverRequestHandler;

    public RPCServer(Class<?> interfaceClass, Object beanObject, int port, int threads,
                     RPCInvokeHook rpcInvokeHook) {
        this.interfaceClass = interfaceClass;
        this.beanObject = beanObject;
        this.port = port;
        this.threads = threads;
        this.rpcInvokeHook = rpcInvokeHook;
    }

    public void init() {
        if (serverRequestHandler == null) {
            serverRequestHandler = new ServerRequestHandler(interfaceClass, beanObject, rpcInvokeHook, threads);
            serverRequestHandler.start();
        }
    }

    /**
     * 添加请求
     * @param rpcRequest
     */
    public void addRequest(RPCRequestWrapper rpcRequest) {
        serverRequestHandler.addRequest(rpcRequest);
    }

    public void stop() {
        System.out.println("server stop...");
    }
}
