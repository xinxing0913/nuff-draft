package com.mengzhidu.dream.nuff.remote.rpc.handler;

import com.mengzhidu.dream.nuff.remote.rpc.hook.RPCInvokeHook;
import com.mengzhidu.dream.nuff.remote.rpc.request.RPCRequest;
import com.mengzhidu.dream.nuff.remote.rpc.server.Server;
import com.mengzhidu.dream.nuff.remote.rpc.task.ServerRequestTask;
import com.mengzhidu.dream.nuff.remote.rpc.wrapper.RequestWrapper;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.logging.Logger;

/**
 * Created by xinxing on 2018/12/16
 *
 * 这里可以根据具体的接口指定线程池的方式来投入任务
 */
public class ServerRequestHandler {

    private Server server;

    private RPCInvokeHook rpcInvokeHook;

    private int threads;

    private ExecutorService executorService;

    private BlockingQueue<RequestWrapper> blockingQueue = new LinkedBlockingDeque<>();

    public ServerRequestHandler(Server server, RPCInvokeHook rpcInvokeHook, int threads) {
        this.server = server;
        this.rpcInvokeHook = rpcInvokeHook;
        this.threads = threads;
    }

    public void start() {
        executorService = Executors.newFixedThreadPool(threads);
        Map<Class<?>, Object> classObjectMap = server.getInterfaceObjectMap();
        // todo，确定线程池大小
        // todo, 这里的queue也得做区分
        for (Map.Entry entry: classObjectMap.entrySet()) {
            Class clazz = (Class<?>) entry.getKey();
            System.out.println("处理的类：" + entry.getKey());
            executorService.execute(new ServerRequestTask(clazz, entry.getValue(),
                    rpcInvokeHook, this.server.getInterfaceBlockingQueueMap().get(clazz)));
        }
        System.out.println("server handler 的所有task已启动");
    }

    public void addRequest(RequestWrapper requestWrapper) {
        Class clazz = requestWrapper.getRpcRequest().getClazz();
        RPCRequest request = requestWrapper.getRpcRequest();
        System.out.println("request:" + request);
        try {
            System.out.println("基本信息:\n" + clazz +
                    "\nserver:" + this.server +
                    "\nmap:" + this.server.getInterfaceBlockingQueueMap() +
            "\n queue:" + this.server.getInterfaceBlockingQueueMap().get(clazz));
            this.server.getInterfaceBlockingQueueMap().get(clazz).put(requestWrapper);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("server handler任务已提交");
    }

}
