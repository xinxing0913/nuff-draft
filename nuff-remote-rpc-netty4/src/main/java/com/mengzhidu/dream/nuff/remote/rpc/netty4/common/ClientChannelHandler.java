package com.mengzhidu.dream.nuff.remote.rpc.netty4.common;

import com.mengzhidu.dream.nuff.remote.rpc.netty4.client.RPCClientResponseHandler;
import com.mengzhidu.dream.nuff.remote.rpc.netty4.handler.ClientDispatchHandler;
import com.mengzhidu.dream.nuff.remote.rpc.netty4.handler.RPCClientChannelInactiveListener;
import com.mengzhidu.dream.nuff.serialize.kryo.NettyKryoDecoder;
import com.mengzhidu.dream.nuff.serialize.kryo.NettyKryoEncoder;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;


/**
 * Created by xinxing on 2018/12/14
 */
public class ClientChannelHandler {

    public static ChannelHandler getDefaultChannelHandler(final RPCClientResponseHandler rpcClientResponseHandler, final RPCClientChannelInactiveListener rpcClientChannelInactiveListener) {
        return new ChannelInitializer<SocketChannel>() {

            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                //pipeline.addLast("decoder", new Netty)
                pipeline.addLast("decoder", new NettyKryoDecoder());
                pipeline.addLast("encoder", new NettyKryoEncoder());
                pipeline.addLast("request", new ClientDispatchHandler(rpcClientResponseHandler, rpcClientChannelInactiveListener));
            }
        };
    }

}
