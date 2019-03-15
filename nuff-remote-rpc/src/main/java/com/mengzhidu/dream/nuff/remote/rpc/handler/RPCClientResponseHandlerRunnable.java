package com.mengzhidu.dream.nuff.remote.rpc.handler;

import com.mengzhidu.dream.nuff.remote.rpc.request.RPCFuture;
import com.mengzhidu.dream.nuff.remote.rpc.request.RPCResponse;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by xinxing on 2018/12/15
 */
public class RPCClientResponseHandlerRunnable implements Runnable {

    private ConcurrentMap<Integer, RPCFuture> invokeMap;

    private BlockingQueue<RPCResponse> messageBlockingQueue;

    public RPCClientResponseHandlerRunnable(ConcurrentMap<Integer, RPCFuture> invokeMap, BlockingQueue<RPCResponse> messageBlockingQueue) {
        this.invokeMap = invokeMap;
        this.messageBlockingQueue = messageBlockingQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                RPCResponse rpcResponse = messageBlockingQueue.take();
                int id = rpcResponse.getRequestId();
                RPCFuture rpcFuture = invokeMap.remove(id);
                if (rpcResponse.isSuccess()) {
                    rpcFuture.setResult(rpcResponse.getResult());
                } else {
                    rpcFuture.setThrowable(rpcResponse.getThrowable());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
