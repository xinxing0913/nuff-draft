package com.mengzhidu.dream.nuff.remote.rpc.server.state;

/**
 * Created by xinxing on 2019/3/16
 *
 * 状态和事件，应该用哪一个
 * 用哪一个更加贴切
 * 需不需要时间戳，记录发生的时间有意义吗
 */
public interface StateListener {

    void handleServerState(ServerState serverState);
}
