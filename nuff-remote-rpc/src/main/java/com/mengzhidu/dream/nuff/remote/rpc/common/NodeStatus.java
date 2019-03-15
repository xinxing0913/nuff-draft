package com.mengzhidu.dream.nuff.remote.rpc.common;

/**
 * 节点状态
 * 每个节点都会有对应的节点状态
 */
public enum NodeStatus {
    UNINIT(0, "未启动"),
    INITING(1, "启动中"),
    INITED(2, "完成初始化"),
    CLOSING(3, "关闭中"),
    CLOSED(4, "已关闭");

    private int code;

    private String desc;

    NodeStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
