package com.mengzhidu.dream.nuff.remote.rpc.server.state;

/**
 * Created by xinxing on 2019/3/16
 *
 * 节点状态
 * 节点有可能在启动中，有可能是未启动，有可能是故障
 * 从单个应用的角度来看，定义成枚举是最好的，但是从扩展性来看，不使用Map可以更强的定义
 */
public class ServerState {

    private int code;

    private String message;


}
