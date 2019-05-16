package com.mengzhidu.dream.nuff.remote.rpc.netty4.server;

import com.mengzhidu.dream.nuff.remote.rpc.config.ServerConfig;
import com.mengzhidu.dream.nuff.remote.rpc.handler.ServerRequestHandler;
import com.mengzhidu.dream.nuff.remote.rpc.hook.RPCInvokeHook;
import com.mengzhidu.dream.nuff.remote.rpc.netty4.handler.ServerChannelHandler;
import com.mengzhidu.dream.nuff.remote.rpc.server.AbstractServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;
import java.util.Objects;
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

    private ServerBootstrap serverBootstrap;

    public DefaultServer() {
    }

    public DefaultServer(ServerConfig config) {
        this.serverConfig = config;
    }

//    public DefaultServer(Class<?> interfaceClass, Object beanObject, int port, int threads,
//                     RPCInvokeHook rpcInvokeHook) {
//        this.interfaceClass = interfaceClass;
//        this.beanObject = beanObject;
//        this.port = port;
//        this.threads = threads;
//        this.rpcInvokeHook = rpcInvokeHook;
//    }

    private void checkBeforeInit() {
        //interface和object 必须设置

    }

    @Override
    protected void doStart() {
        checkBeforeInit();
        if (bossGroup == null) {
            bossGroup = new NioEventLoopGroup(serverConfig.getBossThreads());
        }

        if (workerGroup == null) {
            workerGroup = new NioEventLoopGroup(serverConfig.getWorkerThreads());
        }

        if (serverRequestHandler == null) {
            serverRequestHandler = new ServerRequestHandler(this, rpcInvokeHook, serverConfig.getWorkerThreads());
        }

        if (serverBootstrap == null) {
            serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(ServerChannelHandler.getDefaultChannelHandler(serverRequestHandler));

            serverBootstrap.childOption(ChannelOption.TCP_NODELAY, true);
            serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
            InetSocketAddress inetSocketAddress = new InetSocketAddress(serverConfig.getPort());

            ChannelFuture channelFuture = serverBootstrap.bind(inetSocketAddress);
            channelFuture.syncUninterruptibly();

            serverRequestHandler.start();
        }
    }
}
