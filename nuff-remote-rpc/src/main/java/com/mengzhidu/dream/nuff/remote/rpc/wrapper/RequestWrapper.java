package com.mengzhidu.dream.nuff.remote.rpc.wrapper;

import com.mengzhidu.dream.nuff.remote.rpc.request.RPCRequest;


public class RequestWrapper {
    private final RPCRequest rpcRequest;
    private final Channel channel;

    public RequestWrapper(RPCRequest rpcRequest, Channel channel) {
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

    public RPCRequest getRpcRequest() {
        return rpcRequest;
    }

    @Override
    public String toString() {
        return "RPCRequestWrapper{" +
                "rpcRequest=" + rpcRequest +
                ", channel=" + channel +
                '}';
    }
}
