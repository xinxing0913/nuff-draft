package com.mengzhidu.dream.nuff.remote.rpc.wrapper;

import com.mengzhidu.dream.nuff.remote.rpc.request.RPCRequest;


public class RequestWrapper {
    private final RPCRequest rpcRequest;
    private final NuffChannel nuffChannel;

    public RequestWrapper(RPCRequest rpcRequest, NuffChannel nuffChannel) {
        this.rpcRequest = rpcRequest;
        this.nuffChannel = nuffChannel;
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

    public NuffChannel getNuffChannel() {
        return nuffChannel;
    }

    @Override
    public String toString() {
        return "RPCRequestWrapper{" +
                "rpcRequest=" + rpcRequest +
                ", nuffChannel=" + nuffChannel +
                '}';
    }
}
