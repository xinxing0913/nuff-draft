package com.mengzhidu.dream.nuff.remote.rpc.server.state;

import com.mengzhidu.dream.nuff.remote.rpc.server.Server;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by xinxing on 2019/3/16
 *
 * 服务器是有状态的，它对状态的操作有三个:
 * (1).持有状态
 * (2).发送状态
 * (3).接收状态
 */
public abstract class StatefulServer implements Server {

    private ServerState serverState;

    private List<StateListener> stateListenerList = new CopyOnWriteArrayList<>();

    public void setServerState(ServerState serverState) {
        this.serverState = serverState;
    }

    public ServerState getServerState() {
        return serverState;
    }

    protected void sendServerState() {
        for (StateListener stateListener: stateListenerList) {
            stateListener.handleServerState(this.serverState);
        }
    }

    public void addStateListener(StateListener stateListener) {
        this.stateListenerList.add(stateListener);
    }

    public void removeStateListener(StateListener stateListener) {
        this.stateListenerList.remove(stateListener);
    }

    public void clearStateListener() {
        this.stateListenerList = new CopyOnWriteArrayList<>();
    }
}
