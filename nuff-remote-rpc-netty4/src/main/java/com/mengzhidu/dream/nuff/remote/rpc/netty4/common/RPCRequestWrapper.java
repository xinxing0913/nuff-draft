package com.mengzhidu.dream.nuff.remote.rpc.netty4.common;

import com.mengzhidu.dream.nuff.remote.rpc.request.RPCRequest;
import io.netty.channel.Channel;


public class RPCRequestWrapper {
    private final RPCRequest rpcRequest;
    private final Channel channel;

    public RPCRequestWrapper(RPCRequest rpcRequest, Channel channel) {
        this.rpcRequest = rpcRequest;
        this.channel = channel;
    }

    public int getId() {
        return rpcRequest.getId();
    }

    public String getMethodName() {
        return rpcRequest.getMethodName();
    }

    public Object[] getArgs() {
        return rpcRequest.getArgs();
    }

    public Channel getChannel() {
        return channel;
    }

    @Override
    public String toString() {
        return "RPCRequestWrapper{" +
                "rpcRequest=" + rpcRequest +
                ", channel=" + channel +
                '}';
    }
}
