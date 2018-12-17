package com.mengzhidu.dream.nuff.remote.rpc.proxy;

import com.mengzhidu.dream.nuff.remote.rpc.client.RPCClient;
import com.mengzhidu.dream.nuff.remote.rpc.request.RPCFuture;

/**
 * RPC客户端的代理
 */
public class RPCClientAsyncProxy {

    private RPCClient rpcClient;

    public RPCClientAsyncProxy(RPCClient rpcClient) {
        this.rpcClient = rpcClient;
    }

    public RPCClient getRpcClient() {
        return rpcClient;
    }

    public void setRpcClient(RPCClient rpcClient) {
        this.rpcClient = rpcClient;
    }

    public RPCFuture call(String methodName, Object ... args) {
        return (RPCFuture) rpcClient.call();
    }
}
