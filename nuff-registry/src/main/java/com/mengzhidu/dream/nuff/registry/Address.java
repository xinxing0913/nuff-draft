package com.mengzhidu.dream.nuff.registry;

/**
 * 可以访问的地址信息
 * 一般是IP加端口号的方式，也可以是域名加端口号的方式
 */
public class Address {

    private String ip;

    private String port;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
