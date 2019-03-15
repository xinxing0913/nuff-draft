package com.mengzhidu.dream.nuff.remote.rpc.netty4.handler;

import com.mengzhidu.dream.nuff.remote.rpc.handler.ServerRequestHandler;
import com.mengzhidu.dream.nuff.remote.rpc.netty4.server.DefaultChannel;
import com.mengzhidu.dream.nuff.remote.rpc.request.RPCRequest;
import com.mengzhidu.dream.nuff.remote.rpc.wrapper.RequestWrapper;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by xinxing on 2018/12/16
 */
public class ServerDispatchHandler extends ChannelInboundHandlerAdapter {

    private ServerRequestHandler defaultServerRequestHandler;

    public ServerDispatchHandler() {
    }

    public ServerDispatchHandler(ServerRequestHandler defaultServerRequestHandler) {
        this.defaultServerRequestHandler = defaultServerRequestHandler;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RPCRequest rpcRequest = (RPCRequest) msg;
        System.out.println("new request:" + rpcRequest);
        DefaultChannel defaultChannel = new DefaultChannel(ctx.channel());
        RequestWrapper requestWrapper = new RequestWrapper(rpcRequest, defaultChannel);

        defaultServerRequestHandler.addRequest(requestWrapper);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        super.exceptionCaught(ctx, cause);
    }
}
