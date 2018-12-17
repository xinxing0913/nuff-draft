package com.mengzhidu.dream.nuff.remote.rpc.netty4.client;


import com.mengzhidu.dream.nuff.remote.rpc.client.RPCClient;
import com.mengzhidu.dream.nuff.remote.rpc.netty4.common.ClientChannelHandler;
import com.mengzhidu.dream.nuff.remote.rpc.netty4.handler.RPCClientChannelInactiveListener;
import com.mengzhidu.dream.nuff.remote.rpc.request.RPCFuture;
import com.mengzhidu.dream.nuff.remote.rpc.netty4.message.CodecUtil;
import com.mengzhidu.dream.nuff.remote.rpc.request.RPCRequest;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * Created by xinxing on 2018/12/14
 */
public class Client extends RPCClient {

    private EventLoopGroup eventLoopGroup;

    private Bootstrap bootstrap;

    private ChannelFuture channelFuture;

    private RPCClientResponseHandler responseHandler = new RPCClientResponseHandler(1);

    private RPCClientChannelInactiveListener inactiveListener;

    public boolean doInit() throws Exception {

        if (eventLoopGroup == null) {
            eventLoopGroup = new NioEventLoopGroup();
        }

        if (bootstrap == null) {
            bootstrap = new Bootstrap();
        }

        if (inactiveListener == null) {
            inactiveListener = new RPCClientChannelInactiveListener();
            inactiveListener.setRpcClient(this);
        }

        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .remoteAddress(getRemoteAddress())
        .handler(ClientChannelHandler.getDefaultChannelHandler(responseHandler, inactiveListener));

        channelFuture = bootstrap.connect().sync();
        System.out.println("client connect...");
        return true;
    }

    /**
     * 获取连接，有进一步优化的空间
     *
     * @return
     */
    public Channel tryConnect() {
        try {
            if (channelFuture.isSuccess()) {
                return channelFuture.channel();
            } else {
                Thread.sleep(1000);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 调用
     */
    public RPCFuture call(Class clazz, String methodName, Object ... args) {
        RPCFuture rpcFuture = new RPCFuture();
        int id = atomicInteger.addAndGet(1);
        responseHandler.register(id, rpcFuture);

        RPCRequest rpcRequest = new RPCRequest();
        rpcRequest.setId(id);
        rpcRequest.setClazz(clazz);
        rpcRequest.setMethodName(methodName);
        rpcRequest.setArgs(args);

        if (channelFuture != null) {
            channelFuture.channel().writeAndFlush(rpcRequest);
        } else {
            // todo
            return null;
        }

        return rpcFuture;
    }


    public RPCFuture call() {
        RPCFuture rpcFuture = new RPCFuture();
        responseHandler.register(1, rpcFuture);
        int timeout = 20;
        Channel ch = channelFuture.channel();
        byte[] data = CodecUtil.encode("hahaha");
        ChannelFuture channelFuture = ch.writeAndFlush(data);

        boolean result = channelFuture.awaitUninterruptibly(timeout, TimeUnit.MILLISECONDS);

        if (result && channelFuture.isSuccess()) {
            return rpcFuture;
        } else {
            System.out.println("失败....");
        }
        return rpcFuture;
    }

    public InetSocketAddress getRemoteAddress() {
        return new InetSocketAddress("127.0.0.1", 3190);
    }
}
