package com.mengzhidu.dream.nuff.remote.rpc.config;

/**
 * 核心配置，这里主要是针对客户端和服务端的核心配置
 */
public class ChannelConfig {

    protected String host;

    protected Integer port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
