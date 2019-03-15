package com.mengzhidu.dream.nuff.remote.rpc.common;

/**
 * 节点监听器
 * 它在对应的节点做出对应的行为时的方法回调
 * 比如统计启动次数、重启次数、关闭次数等
 */
public interface NodeListener {

    void onInit();

    void onClose();
}
