package com.mengzhidu.dream.nuff.remote.rpc.server;

/**
 * Created by xinxing on 2018/12/22
 */
public class ServerConfig {

    private Integer port;

    private Integer bossThreads;

    private Integer workerThreads;

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

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
}
