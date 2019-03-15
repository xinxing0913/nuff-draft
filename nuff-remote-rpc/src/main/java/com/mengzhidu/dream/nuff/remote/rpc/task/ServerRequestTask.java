package com.mengzhidu.dream.nuff.remote.rpc.task;

import com.esotericsoftware.reflectasm.MethodAccess;
import com.mengzhidu.dream.nuff.remote.rpc.hook.RPCInvokeHook;
import com.mengzhidu.dream.nuff.remote.rpc.request.RPCResponse;
import com.mengzhidu.dream.nuff.remote.rpc.wrapper.NuffChannel;
import com.mengzhidu.dream.nuff.remote.rpc.wrapper.RequestWrapper;

import java.util.concurrent.BlockingQueue;

/**
 * Created by xinxing on 2018/12/16
 */
public class ServerRequestTask implements Runnable {

    private Class<?> interfaceClass;

    private Object beanObject;

    private RPCInvokeHook rpcInvokeHook;

    private BlockingQueue<RequestWrapper> requestQueue;

    private RequestWrapper requestWrapper;

    private MethodAccess methodAccess;

    private String lastMethodName = "";

    private int lastMethodIndex;

    public ServerRequestTask(Class<?> interfaceClass, Object beanObject, RPCInvokeHook rpcInvokeHook, BlockingQueue<RequestWrapper> requestQueue) {
        this.interfaceClass = interfaceClass;
        this.beanObject = beanObject;
        this.rpcInvokeHook = rpcInvokeHook;
        this.requestQueue = requestQueue;
        methodAccess = MethodAccess.get(interfaceClass);
    }

    @Override
    public void run() {
        while (true) {
            try {
                requestWrapper = requestQueue.take();
                String methodName = requestWrapper.getMethodName();
                Object[] args = requestWrapper.getArgs();

                if (rpcInvokeHook != null) {
                    rpcInvokeHook.beforeInvoke(interfaceClass, methodName, args);
                }

                Object result;

                if (!methodName.equals(lastMethodName)) {
                    lastMethodIndex = methodAccess.getIndex(methodName);
                    lastMethodName = methodName;
                }

                result = methodAccess.invoke(beanObject, lastMethodIndex, args);

                NuffChannel nuffChannel = requestWrapper.getNuffChannel();
                RPCResponse rpcResponse = new RPCResponse();
                rpcResponse.setRequestId(requestWrapper.getId());
                rpcResponse.setResult(result);
                rpcResponse.setSuccess(true);
                nuffChannel.writeAndFlush(rpcResponse);

                if (rpcInvokeHook != null) {
                    rpcInvokeHook.afterInvoke(interfaceClass, methodName, args, result);
                }

            } catch (Exception e) {
                NuffChannel nuffChannel = requestWrapper.getNuffChannel();
                RPCResponse rpcResponse = new RPCResponse();
                rpcResponse.setRequestId(requestWrapper.getId());
                rpcResponse.setThrowable(e);
                rpcResponse.setSuccess(false);
                nuffChannel.writeAndFlush(rpcResponse);
            }
        }
    }

}
