package com.mengzhidu.dream.nuff.demo.demo1;


import com.mengzhidu.dream.nuff.demo.common.HiService;
import com.mengzhidu.dream.nuff.demo.common.impl.HiServiceImpl;
import com.mengzhidu.dream.nuff.remote.rpc.netty4.server.DefaultServer;
import com.mengzhidu.dream.nuff.remote.rpc.server.Server;

/**s
 * Created by xinxing on 2018/12/16
 */
public class ServerDemo {
    public static void main(String[] args) {
        HiServiceImpl hiService = new HiServiceImpl();
        Server server = new DefaultServer(HiService.class, hiService, 3190, 4, null);
        server.init(null);
        System.out.println("server started ... ");
    }
}
