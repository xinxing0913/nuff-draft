package com.mengzhidu.dream.nuff.remote.rpc.server;

import com.mengzhidu.dream.nuff.remote.rpc.config.ServerConfig;
import com.mengzhidu.dream.nuff.remote.rpc.wrapper.RequestWrapper;

import java.util.Map;
import java.util.concurrent.BlockingQueue;

/**
 * 提供了对服务器类的基本抽象
 *
 * 在最初的实现中，是没有config这个步骤的，直接在init中传入ServerConfig，但是配置和启动分离，职责更加明确
 */
public interface Server {

    void start();

    void addInterfaceObject(Class<?> clazz, Object object);

    void putInterfaceObjectMap(Map<Class<?>, Object> classObjectMap);

    Map<Class<?>, Object> getInterfaceObjectMap();

    Map<Class<?>, BlockingQueue<RequestWrapper>> getInterfaceBlockingQueueMap();

    void config(ServerConfig serverConfig);
}
