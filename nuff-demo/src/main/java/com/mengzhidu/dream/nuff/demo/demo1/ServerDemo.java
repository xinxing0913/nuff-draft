package com.mengzhidu.dream.nuff.demo.demo3;


import com.mengzhidu.dream.nuff.demo.common.HiService;
import com.mengzhidu.dream.nuff.demo.common.impl.HiServiceImpl;
import com.mengzhidu.dream.nuff.remote.rpc.netty4.server.Server;

/**
 * Created by xinxing on 2018/12/16
 */
public class ServerDemo {
    public static void main(String[] args) {
        HiServiceImpl hiService = new HiServiceImpl();
        Server server = new Server(HiService.class, hiService, 3190, 4, null);
        server.doInit();
    }
}
