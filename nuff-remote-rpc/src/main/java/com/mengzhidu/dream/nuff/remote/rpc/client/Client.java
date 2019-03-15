package com.mengzhidu.dream.nuff.remote.rpc.client;

import java.lang.reflect.InvocationHandler;

/**
 * Created by xinxing on 2019/3/15
 */
public interface Client  extends InvocationHandler {

    void connect();

    void init(ClientConfig clientConfig) throws Exception;

    void reConnect();
}
