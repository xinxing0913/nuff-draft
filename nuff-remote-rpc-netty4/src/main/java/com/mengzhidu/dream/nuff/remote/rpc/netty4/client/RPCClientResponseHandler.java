package com.mengzhidu.dream.nuff.remote.rpc.netty4.client;

import com.mengzhidu.dream.nuff.remote.rpc.request.RPCFuture;
import com.mengzhidu.dream.nuff.remote.rpc.request.RPCResponse;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by xinxing on 2018/12/15
 */
public class RPCClientResponseHandler {

    private ConcurrentHashMap<Integer, RPCFuture> invokeMap = new ConcurrentHashMap<Integer, RPCFuture>();

    private ExecutorService executorService;

    private BlockingQueue<RPCResponse>  messageBlockingQueue = new LinkedBlockingDeque<RPCResponse>();

    public RPCClientResponseHandler(int threads) {
        executorService = Executors.newFixedThreadPool(threads);
        for (int i = 0; i < threads; i++) {
            executorService.execute(new RPCClientResponseHandlerRunnable(invokeMap, messageBlockingQueue));
        }
    }

    public void register(int id, RPCFuture rpcFuture) {
        invokeMap.put(id, rpcFuture);
    }

    public RPCFuture getRegister(int id) {
        return invokeMap.get(id);
    }

    public void addResponse(RPCResponse message) {
        messageBlockingQueue.add(message);
    }
}
