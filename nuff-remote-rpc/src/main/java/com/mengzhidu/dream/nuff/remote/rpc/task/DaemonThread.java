package com.mengzhidu.dream.nuff.remote.rpc.task;


import com.mengzhidu.dream.nuff.remote.rpc.request.RPCFuture;

/**
 * 相当于是RPC的一个线程，它从服务端返回
 */
public class DaemonThread extends Thread {

    private Class clazz;

    private String methodName;

    private Object[] args;

    RPCFuture rpcFuture;

    public DaemonThread(RPCFuture rpcFuture, Class clazz, String methodName, Object[] args) {
        this.clazz = clazz;
        this.rpcFuture = rpcFuture;
        this.methodName = methodName;
        this.args = args;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(20);
//            int parmeterCount = args.length;
//            Method method;
//            if (parmeterCount > 0) {
//                Class<?>[] parameterTypes = new Class[args.length];
//                for (int i = 0; i < parmeterCount; i++) {
//                    parameterTypes[i] = args[i].getClass();
//                }
//                method = clazz.getMethod(methodName, parameterTypes);
//            } else {
//                method = clazz.getMethod(methodName);
//            }

            rpcFuture.setResult("nuff返回的结果");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
