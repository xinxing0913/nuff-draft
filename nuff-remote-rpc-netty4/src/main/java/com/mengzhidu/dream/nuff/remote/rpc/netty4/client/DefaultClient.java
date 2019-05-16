package com.mengzhidu.dream.nuff.remote.rpc.netty4.client;

import com.mengzhidu.dream.nuff.remote.rpc.client.AbstractClient;
import com.mengzhidu.dream.nuff.remote.rpc.config.ChannelConfig;
import com.mengzhidu.dream.nuff.remote.rpc.netty4.handler.ClientChannelHandler;
import com.mengzhidu.dream.nuff.remote.rpc.request.RPCFuture;
import com.mengzhidu.dream.nuff.remote.rpc.request.RPCRequest;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * Created by xinxing on 2019/3/15
 */
public class DefaultClient extends AbstractClient {

    private EventLoopGroup eventLoopGroup;

    private Bootstrap bootstrap;

    private ChannelFuture channelFuture;


    public boolean doInit() throws Exception {
        if (eventLoopGroup == null) {
            eventLoopGroup = new NioEventLoopGroup();
        }

        if (bootstrap == null) {
            bootstrap = new Bootstrap();
        }

        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .remoteAddress(getRemoteAddress())
                .handler(ClientChannelHandler.getDefaultChannelHandler(responseHandler, inactiveHandler));

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

    private InetSocketAddress getRemoteAddress() {
        return new InetSocketAddress(clientConfig.getHost(), clientConfig.getPort());
    }

    @Override
    public void reConnect() {

    }

    @Override
    public void config(ChannelConfig channelConfig) {

    }
}
