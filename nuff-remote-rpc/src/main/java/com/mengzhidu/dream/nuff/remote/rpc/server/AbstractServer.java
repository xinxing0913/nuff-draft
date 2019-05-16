package com.mengzhidu.dream.nuff.remote.rpc.server;

import com.mengzhidu.dream.nuff.remote.rpc.config.ServerConfig;
import com.mengzhidu.dream.nuff.remote.rpc.handler.ServerRequestHandler;
import com.mengzhidu.dream.nuff.remote.rpc.hook.RPCInvokeHook;
import com.mengzhidu.dream.nuff.remote.rpc.server.state.StatefulServer;
import com.mengzhidu.dream.nuff.remote.rpc.wrapper.RequestWrapper;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 抽象服务类
 * 它并不能进行真正意义上的初始化，它没有传输层对应的协议
 * 它主要做两个方面的工作:
 * 1.提供默认值,避免类似空指针的错误
 * 2.提供基础的实现，对基本概念进行了定义
 *
 * 它还可以接受一个延迟队列来处理解决不了的请求
 */
public abstract class AbstractServer extends StatefulServer {

    protected Map<Class<?>, Object> classObjectMap = new ConcurrentHashMap<>();

    protected Map<Class<?>, BlockingQueue<RequestWrapper>> classBlockingQueueMap = new ConcurrentHashMap<>();

    protected RPCInvokeHook rpcInvokeHook;

    protected ServerRequestHandler serverRequestHandler;

    protected ServerConfig serverConfig;

    public ServerConfig getServerConfig() {
        return serverConfig;
    }

    public void setServerConfig(ServerConfig serverConfig) {
        this.serverConfig = serverConfig;
    }

    @Override
    public void config(ServerConfig serverConfig) {
        this.serverConfig = ServerConfigHelper.fillServerConfig(serverConfig);
    }

    @Override
    public void start() {
        doStart();
    }

    abstract protected void doStart();

    @Override
    public void addInterfaceObject(Class<?> clazz, Object object) {
        classObjectMap.put(clazz, object);
        classBlockingQueueMap.put(clazz, new LinkedBlockingQueue<RequestWrapper>());
    }

    @Override
    public void putInterfaceObjectMap(Map<Class<?>, Object> classObjectMap) {
        this.classObjectMap = classObjectMap;
        for (Map.Entry entry: classObjectMap.entrySet()) {
            classBlockingQueueMap.put((Class<?>) entry.getKey(), new LinkedBlockingQueue<RequestWrapper>());
        }
    }

    @Override
    public Map<Class<?>, Object> getInterfaceObjectMap() {
        return classObjectMap;
    }

    @Override
    public Map<Class<?>, BlockingQueue<RequestWrapper>> getInterfaceBlockingQueueMap() {
        return classBlockingQueueMap;
    }
}
