package com.mengzhidu.dream.nuff.remote.rpc.request;

/**
 * Created by xinxing on 2018/12/15
 */
public class RPCFutureListener {

    public void onResult(Object result)
    {
        System.out.println("RpcFutureListener result = " + result.toString());
    }

    public void onException(Throwable throwable)
    {
        System.out.println("RpcFutureListener onException");
    }
}
