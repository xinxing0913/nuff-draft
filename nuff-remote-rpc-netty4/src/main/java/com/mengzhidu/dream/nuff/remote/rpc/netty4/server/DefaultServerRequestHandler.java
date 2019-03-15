package com.mengzhidu.dream.nuff.remote.rpc.netty4.server;

import com.mengzhidu.dream.nuff.remote.rpc.hook.RPCInvokeHook;
import com.mengzhidu.dream.nuff.remote.rpc.netty4.common.RPCRequestWrapper;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by xinxing on 2018/12/16
 */
public class DefaultServerRequestHandler {
    private Class<?> interfaceClass;

    private Object serviceProvider;

    private RPCInvokeHook rpcInvokeHook;

    private int threads;

    private ExecutorService executorService;

    private BlockingQueue<RPCRequestWrapper> blockingQueue = new LinkedBlockingDeque<>();

    public DefaultServerRequestHandler(Class<?> interfaceClass, Object serviceProvider, RPCInvokeHook rpcInvokeHook, int threads) {
        this.interfaceClass = interfaceClass;
        this.serviceProvider = serviceProvider;
        this.rpcInvokeHook = rpcInvokeHook;
        this.threads = threads;
    }

    public void start() {
        executorService = Executors.newFixedThreadPool(threads);
        for (int i = 0; i < threads; i++) {
            executorService.execute(new ServerRequestTask(interfaceClass, serviceProvider,
                     rpcInvokeHook, blockingQueue));
        }
        System.out.println("server handler 的所有task已启动");
    }

    public void addRequest(RPCRequestWrapper rpcRequest) {
        try {
            blockingQueue.put(rpcRequest);
            System.out.println("server handler任务已提交");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
