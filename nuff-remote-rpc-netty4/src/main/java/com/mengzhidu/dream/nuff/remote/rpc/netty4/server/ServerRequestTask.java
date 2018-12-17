package com.mengzhidu.dream.nuff.remote.rpc.netty4.server;

import com.esotericsoftware.reflectasm.MethodAccess;
import com.mengzhidu.dream.nuff.remote.rpc.hook.RPCInvokeHook;
import com.mengzhidu.dream.nuff.remote.rpc.netty4.common.RPCRequestWrapper;
import com.mengzhidu.dream.nuff.remote.rpc.request.RPCResponse;
import io.netty.channel.Channel;

import java.util.concurrent.BlockingQueue;

/**
 * Created by xinxing on 2018/12/16
 */
public class ServerRequestTask implements Runnable {

    private Class<?> interfaceClass;

    private Object beanObject;

    private RPCInvokeHook rpcInvokeHook;

    private BlockingQueue<RPCRequestWrapper> requestQueue;

    private RPCRequestWrapper requestWrapper;

    private MethodAccess methodAccess;

    private String lastMethodName = "";

    private int lastMethodIndex;


    public ServerRequestTask(Class<?> interfaceClass, Object beanObject, RPCInvokeHook rpcInvokeHook, BlockingQueue<RPCRequestWrapper> requestQueue) {
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

                Channel channel = requestWrapper.getChannel();
                RPCResponse rpcResponse = new RPCResponse();
                rpcResponse.setRequestId(requestWrapper.getId());
                rpcResponse.setResult(result);
                rpcResponse.setSuccess(true);
                channel.writeAndFlush(rpcResponse);

                if (rpcInvokeHook != null) {
                    rpcInvokeHook.afterInvoke(interfaceClass, methodName, args, result);
                }

            } catch (Exception e) {
                Channel channel = requestWrapper.getChannel();

                RPCResponse rpcResponse = new RPCResponse();
                rpcResponse.setRequestId(requestWrapper.getId());
                rpcResponse.setThrowable(e);
                rpcResponse.setSuccess(false);
                channel.writeAndFlush(rpcResponse);
            }
        }
    }


    //    @Override
//    public void run() {
//        while (true) {
//            try {
//                RPCRequestWrapper rpcRequest = requestQueue.take();
//                String methodName = rpcRequest.getMethodName();
//                Object[] args = rpcRequest.getArgs();
//
//                int parameterCount = args.length;
//                Method method = null;
//                if (parameterCount > 0) {
//                    Class<?>[] parameterTypes = new Class[args.length];
//                    for (int i = 0; i < parameterCount; i++) {
//                        parameterTypes[i] = args[i].getClass();
//                    }
//                    method = interfaceClass.getMethod(methodName, parameterTypes);
//                } else {
//                    method = interfaceClass.getMethod(methodName);
//                }
//
//                if (rpcInvokeHook != null) {
//                    rpcInvokeHook.beforeInvoke(interfaceClass, methodName, args);
//                }
//
//                Object result = method.invoke(beanObject, args);
//                System.out.println("执行得到的结果为:" + result);
//
//                if (rpcInvokeHook != null) {
//                    rpcInvokeHook.afterInvoke(interfaceClass, methodName, args, result);
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
//            } catch (SecurityException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
