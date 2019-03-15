package com.mengzhidu.dream.nuff.remote.rpc.server;

import com.mengzhidu.dream.nuff.remote.rpc.handler.ServerRequestHandler;
import com.mengzhidu.dream.nuff.remote.rpc.hook.RPCInvokeHook;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 抽象服务类
 * 它并不能进行真正意义上的初始化，它没有传输层对应的协议
 * 它主要做两个方面的工作:
 * 1.提供默认值,避免类似空指针的错误
 * 2.提供基础的实现，对基本概念进行了定义
 */
public abstract class AbstractServer implements Server {

    protected Map<Class<?>, Object> classObjectMap = new ConcurrentHashMap<>();

    protected Class interfaceClass;

    protected Object beanObject;

    protected int port;

    protected int threads;

    protected RPCInvokeHook rpcInvokeHook;

    protected ServerRequestHandler serverRequestHandler;

    @Override
    public void init(ServerConfig serverConfig) {
        serverConfig = ServerConfigHelper.fillServerConfig(serverConfig);
        doInit(serverConfig);
    }

    abstract protected void doInit(ServerConfig serverConfig);

    @Override
    public void putInterfaceObject(Class<?> clazz, Object object) {
        classObjectMap.put(clazz, object);
        doPutInterfaceObject(clazz, object);
    }

    abstract protected void doPutInterfaceObject(Class<?> clazz, Object object);
}
