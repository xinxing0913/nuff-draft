package com.mengzhidu.dream.nuff.remote.rpc.config;

/**
 * Created by xinxing on 2019/3/15
 */
public class ClientConfig extends ChannelConfig {

    private int timeout;

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
