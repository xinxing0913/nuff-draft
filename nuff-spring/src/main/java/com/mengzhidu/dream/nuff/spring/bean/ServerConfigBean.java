package com.mengzhidu.dream.nuff.spring.bean;

/**
 * Created by xinxing on 2018/12/21
 */
public class ServerConfigBean {
    private Integer bossThreadNumber;

    private Integer workerThreadNumber;

    private Integer port;

    public ServerConfigBean() {
    }

    public Integer getBossThreadNumber() {
        return bossThreadNumber;
    }

    public void setBossThreadNumber(Integer bossThreadNumber) {
        this.bossThreadNumber = bossThreadNumber;
    }

    public Integer getWorkerThreadNumber() {
        return workerThreadNumber;
    }

    public void setWorkerThreadNumber(Integer workerThreadNumber) {
        this.workerThreadNumber = workerThreadNumber;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
