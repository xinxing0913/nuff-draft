package com.mengzhidu.dream.nuff.remote.rpc.common;

/**
 * 所有的RPC节点
 * 它包括客户端节点和服务端节点
 * 所有的节点都可以支持初始化、关闭等功能的实现
 */
public interface Node {

    void init();

    void close();
}
