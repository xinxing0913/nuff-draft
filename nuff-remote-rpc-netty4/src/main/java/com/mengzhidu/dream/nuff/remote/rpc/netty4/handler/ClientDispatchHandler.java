package com.mengzhidu.dream.nuff.remote.rpc.netty4.handler;

import com.mengzhidu.dream.nuff.remote.rpc.netty4.client.RPCClientResponseHandler;
import com.mengzhidu.dream.nuff.remote.rpc.request.RPCResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by xinxing on 2018/12/16
 */
public class ClientDispatchHandler extends ChannelInboundHandlerAdapter {

    private RPCClientResponseHandler rpcClientResponseHandler;

    private RPCClientChannelInactiveListener rpcClientChannelInactiveListener;

    public ClientDispatchHandler() {
    }

    public ClientDispatchHandler(RPCClientResponseHandler rpcClientResponseHandler, RPCClientChannelInactiveListener rpcClientChannelInactiveListener) {
        this.rpcClientResponseHandler = rpcClientResponseHandler;
        this.rpcClientChannelInactiveListener = rpcClientChannelInactiveListener;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RPCResponse rpcResponse = (RPCResponse) msg;
        rpcClientResponseHandler.addResponse(rpcResponse);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        super.exceptionCaught(ctx, cause);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        if (rpcClientChannelInactiveListener != null) {
            rpcClientChannelInactiveListener.onInactive();
        }
        super.channelInactive(ctx);
    }
}
