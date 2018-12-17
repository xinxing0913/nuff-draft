package com.mengzhidu.dream.nuff.remote.rpc.listener;

/**
 * Created by xinxing on 2018/12/16
 */
public interface RPCFutureListener {

    void onResult(Object result);

    void onException(Throwable throwable);
}
