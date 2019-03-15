package com.mengzhidu.dream.nuff.remote.rpc.netty4.handler;

import com.mengzhidu.dream.nuff.remote.rpc.handler.ClientChannelInactiveHandler;
import com.mengzhidu.dream.nuff.remote.rpc.handler.ClientResponseHandler;
import com.mengzhidu.dream.nuff.remote.rpc.request.RPCResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by xinxing on 2018/12/16
 */
public class ClientDispatchHandler extends ChannelInboundHandlerAdapter {

    private ClientResponseHandler clientResponseHandler;

    private ClientChannelInactiveHandler inactiveHandler;

    public ClientDispatchHandler(ClientResponseHandler rpcClientResponseHandler,
                                 ClientChannelInactiveHandler inactiveHandler) {
        this.clientResponseHandler = rpcClientResponseHandler;
        this.inactiveHandler = inactiveHandler;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RPCResponse rpcResponse = (RPCResponse) msg;
        clientResponseHandler.addResponse(rpcResponse);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        super.exceptionCaught(ctx, cause);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        if (inactiveHandler != null) {
            inactiveHandler.onInactive();
        }
        super.channelInactive(ctx);
    }
}
