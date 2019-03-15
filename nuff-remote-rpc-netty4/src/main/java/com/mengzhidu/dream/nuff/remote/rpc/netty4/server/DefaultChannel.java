package com.mengzhidu.dream.nuff.remote.rpc.netty4.server;

import com.mengzhidu.dream.nuff.remote.rpc.request.RPCResponse;
import com.mengzhidu.dream.nuff.remote.rpc.wrapper.AbstractChannel;
import io.netty.channel.Channel;

/**
 * Created by xinxing on 2019/3/15
 */
public class DefaultChannel extends AbstractChannel {

    private Channel channel;

    public DefaultChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public void writeAndFlush(RPCResponse rpcResponse) {
        channel.writeAndFlush(rpcResponse);
    }
}
