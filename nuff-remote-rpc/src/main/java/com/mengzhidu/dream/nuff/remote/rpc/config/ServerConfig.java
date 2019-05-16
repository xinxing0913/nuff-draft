package com.mengzhidu.dream.nuff.remote.rpc.config;

/**
 * Created by xinxing on 2018/12/22
 */
public class ServerConfig extends ChannelConfig {

    private Integer bossThreads = 1;

    private Integer workerThreads = 4;

    private Integer hanlderThreads = 4;

    public Integer getBossThreads() {
        return bossThreads;
    }

    public void setBossThreads(Integer bossThreads) {
        this.bossThreads = bossThreads;
    }

    public Integer getWorkerThreads() {
        return workerThreads;
    }

    public void setWorkerThreads(Integer workerThreads) {
        this.workerThreads = workerThreads;
    }

    public Integer getHanlderThreads() {
        return hanlderThreads;
    }

    public void setHanlderThreads(Integer hanlderThreads) {
        this.hanlderThreads = hanlderThreads;
    }

    @Override
    public String toString() {
        return "ServerConfig{" +
                "bossThreads=" + bossThreads +
                ", workerThreads=" + workerThreads +
                ", hanlderThreads=" + hanlderThreads +
                '}';
    }
}
