package com.mengzhidu.dream.nuff.remote.rpc.netty4.handler;

import com.mengzhidu.dream.nuff.remote.rpc.netty4.client.Client;

/**
 * Created by xinxing on 2018/12/16
 */
public class RPCClientChannelInactiveListener {

    private Client rpcClient;

    public RPCClientChannelInactiveListener() {
    }

    public RPCClientChannelInactiveListener(Client rpcClient) {
        this.rpcClient = rpcClient;
    }

    /**
     * 在失去连接时进行重试，这里的接口应该定义在RPC层
     * 但是具体的连接操作定义在Netty的Client层
     */
    public void onInactive() {
        System.out.println("connection with server is closed...");

        rpcClient.tryConnect();
    }

    public Client getRpcClient() {
        return rpcClient;
    }

    public void setRpcClient(Client rpcClient) {
        this.rpcClient = rpcClient;
    }
}
