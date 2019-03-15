package com.mengzhidu.dream.nuff.remote.rpc.handler;

import com.mengzhidu.dream.nuff.remote.rpc.wrapper.RequestWrapper;

/**
 * Created by xinxing on 2019/3/15
 */
public interface RequestHandler {

    void start();

    void addRequest(RequestWrapper rpcRequest);
}
