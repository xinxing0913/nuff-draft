package com.mengzhidu.dream.nuff.remote.rpc.netty4.server;

import com.mengzhidu.dream.nuff.remote.rpc.hook.RPCInvokeHook;
import com.mengzhidu.dream.nuff.remote.rpc.netty4.common.ServerChannelHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * Created by xinxing on 2018/12/14
 */
public class Server extends RPCServer {

    private EventLoopGroup bossGroup;

    private EventLoopGroup workerGroup;

    private ServerRequestHandler serverRequestHandler;

    public Server(Class<?> interfaceClass, Object beanObject, int port, int threads, RPCInvokeHook rpcInvokeHook) {
        super(interfaceClass, beanObject, port, threads, rpcInvokeHook);
    }

    public boolean doInit() {

        if (bossGroup == null) {
            bossGroup = new NioEventLoopGroup(1);
        }

        if (workerGroup == null) {
            workerGroup = new NioEventLoopGroup();
        }

        if (serverRequestHandler == null) {
            serverRequestHandler = new ServerRequestHandler(interfaceClass, beanObject, rpcInvokeHook, threads);
        }

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
        .childHandler(ServerChannelHandler.getDefaultChannelHandler(serverRequestHandler));

        serverBootstrap.childOption(ChannelOption.TCP_NODELAY, true);
        serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
        InetSocketAddress inetSocketAddress = new InetSocketAddress(3190);

        ChannelFuture channelFuture = serverBootstrap.bind(inetSocketAddress);
        channelFuture.syncUninterruptibly();
        System.out.println("Server start ....");

        serverRequestHandler.start();
        return true;
    }
}
