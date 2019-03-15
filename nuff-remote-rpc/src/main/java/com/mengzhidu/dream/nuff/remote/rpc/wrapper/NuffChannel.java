package com.mengzhidu.dream.nuff.remote.rpc.wrapper;

import com.mengzhidu.dream.nuff.remote.rpc.request.RPCResponse;

/**
 * Created by xinxing on 2019/3/15
 */
public interface NuffChannel {

    void writeAndFlush(RPCResponse rpcResponse);
}
