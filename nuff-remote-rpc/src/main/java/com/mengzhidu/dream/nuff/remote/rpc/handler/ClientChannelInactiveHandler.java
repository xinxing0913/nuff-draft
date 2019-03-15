package com.mengzhidu.dream.nuff.remote.rpc.handler;

import com.mengzhidu.dream.nuff.remote.rpc.client.Client;

/**
 * Created by xinxing on 2019/3/16
 */
public class ClientChannelInactiveHandler {

    private Client rpcClient;

    public ClientChannelInactiveHandler(Client rpcClient) {
        this.rpcClient = rpcClient;
    }

    /**
     * 在失去连接时进行重试，这里的接口应该定义在RPC层
     * 但是具体的连接操作定义在Netty的Client层
     */
    public void onInactive() {
        System.out.println("connection with server is closed...");

        rpcClient.reConnect();
    }

    public Client getRpcClient() {
        return rpcClient;
    }

    public void setRpcClient(Client rpcClient) {
        this.rpcClient = rpcClient;
    }
}
