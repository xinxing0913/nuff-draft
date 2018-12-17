package com.mengzhidu.dream.nuff.remote.rpc.netty4.handler;

import com.mengzhidu.dream.nuff.remote.rpc.netty4.common.RPCRequestWrapper;
import com.mengzhidu.dream.nuff.remote.rpc.request.RPCRequest;
import com.mengzhidu.dream.nuff.remote.rpc.netty4.server.ServerRequestHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by xinxing on 2018/12/16
 */
public class ServerDispatchHandler extends ChannelInboundHandlerAdapter {

    private ServerRequestHandler serverRequestHandler;

    public ServerDispatchHandler() {
    }

    public ServerDispatchHandler(ServerRequestHandler serverRequestHandler) {
        this.serverRequestHandler = serverRequestHandler;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RPCRequest rpcRequest = (RPCRequest) msg;
        System.out.println("new request:" + rpcRequest);
        RPCRequestWrapper wrapper = new RPCRequestWrapper(rpcRequest, ctx.channel());
        serverRequestHandler.addRequest(wrapper);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        super.exceptionCaught(ctx, cause);
    }
}
