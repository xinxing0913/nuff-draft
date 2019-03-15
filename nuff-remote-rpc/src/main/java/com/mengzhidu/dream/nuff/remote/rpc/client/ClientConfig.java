package com.mengzhidu.dream.nuff.remote.rpc.client;

/**
 * Created by xinxing on 2019/3/15
 */
public class ClientConfig {

    private String host;

    private int port;

    private int timeout;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    @Override
    public String toString() {
        return "ClientConfig{" +
                "host='" + host + '\'' +
                ", port=" + port +
                '}';
    }
}
