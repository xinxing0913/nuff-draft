package com.mengzhidu.dream.nuff.remote.rpc.client;

import com.mengzhidu.dream.nuff.remote.rpc.config.ClientConfig;
import com.mengzhidu.dream.nuff.remote.rpc.config.Configurable;

import java.lang.reflect.InvocationHandler;

/**
 * Created by xinxing on 2019/3/15
 */
public interface Client  extends InvocationHandler,Configurable {

    void connect();

    void config(ClientConfig clientConfig);

    void init(ClientConfig clientConfig) throws Exception;

    void reConnect();
}
