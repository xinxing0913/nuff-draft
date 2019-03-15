package com.mengzhidu.dream.nuff.remote.rpc.client;

import com.mengzhidu.dream.nuff.remote.rpc.common.Node;
import com.mengzhidu.dream.nuff.remote.rpc.request.RPCFuture;

import java.lang.reflect.InvocationHandler;

/**
 * 客户端节点
 * 它可以
 */
public interface ClientNode extends Node, InvocationHandler {

    void connect();

    void reconnect();

    RPCFuture call();

    int getRequestId();

}
