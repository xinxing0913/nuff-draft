package com.mengzhidu.dream.nuff.remote.rpc.server;

/**
 * 提供了对服务器类的基本抽象
 */
public interface Server {

    void init(ServerConfig serverConfig);

    void putInterfaceObject(Class<?> clazz, Object object);
}
