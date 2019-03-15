package com.mengzhidu.dream.nuff.remote.rpc.netty4.server;

import com.mengzhidu.dream.nuff.remote.rpc.handler.ServerRequestHandler;
import com.mengzhidu.dream.nuff.remote.rpc.hook.RPCInvokeHook;
import com.mengzhidu.dream.nuff.remote.rpc.netty4.common.ServerChannelHandler;
import com.mengzhidu.dream.nuff.remote.rpc.server.AbstractServer;
import com.mengzhidu.dream.nuff.remote.rpc.server.ServerConfig;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 服务端类
 * 这里是基于Netty实现的服务器类
 * 这里是nuff给出的一个默认实现
 */
public class DefaultServer extends AbstractServer {

    private AtomicBoolean inited = new AtomicBoolean(false);

    private EventLoopGroup bossGroup;

    private EventLoopGroup workerGroup;

    private ServerRequestHandler defaultServerRequestHandler;

    private ServerBootstrap serverBootstrap;

    public DefaultServer(Class<?> interfaceClass, Object beanObject, int port, int threads,
                     RPCInvokeHook rpcInvokeHook) {
        this.interfaceClass = interfaceClass;
        this.beanObject = beanObject;
        this.port = port;
        this.threads = threads;
        this.rpcInvokeHook = rpcInvokeHook;
    }

    @Override
    protected void doInit(ServerConfig serverConfig) {
        if (bossGroup == null) {
            bossGroup = new NioEventLoopGroup(serverConfig.getBossThreads());
        }

        if (workerGroup == null) {
            workerGroup = new NioEventLoopGroup(serverConfig.getWorkerThreads());
        }

        if (defaultServerRequestHandler == null) {
            defaultServerRequestHandler = new ServerRequestHandler(interfaceClass, beanObject, rpcInvokeHook, threads);
        }

        if (serverBootstrap == null) {
            serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(ServerChannelHandler.getDefaultChannelHandler(defaultServerRequestHandler));

            serverBootstrap.childOption(ChannelOption.TCP_NODELAY, true);
            serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
            InetSocketAddress inetSocketAddress = new InetSocketAddress(serverConfig.getPort());

            ChannelFuture channelFuture = serverBootstrap.bind(inetSocketAddress);
            channelFuture.syncUninterruptibly();

            defaultServerRequestHandler.start();
        }
    }

    @Override
    protected void doPutInterfaceObject(Class<?> clazz, Object object) {

    }
}
