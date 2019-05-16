package com.mengzhidu.dream.nuff.demo.demo1;


import com.mengzhidu.dream.nuff.demo.common.CourseService;
import com.mengzhidu.dream.nuff.demo.common.HiService;
import com.mengzhidu.dream.nuff.demo.common.impl.CourseServiceImpl;
import com.mengzhidu.dream.nuff.demo.common.impl.HiServiceImpl;
import com.mengzhidu.dream.nuff.remote.rpc.netty4.server.DefaultServer;
import com.mengzhidu.dream.nuff.remote.rpc.server.Server;
import com.mengzhidu.dream.nuff.remote.rpc.config.ServerConfig;

/**s
 * Created by xinxing on 2018/12/16
 */
public class ServerDemo {
    public static void main(String[] args) {
        HiServiceImpl hiService = new HiServiceImpl();
        CourseServiceImpl courseService = new CourseServiceImpl();

        ServerConfig serverConfig = new ServerConfig();
        serverConfig.setHost("127.0.0.1");
        serverConfig.setPort(3190);
        serverConfig.setBossThreads(1);
        serverConfig.setWorkerThreads(15);



//        Server server = new DefaultServer(HiService.class, hiService, 3190, 4, null);
        Server server = new DefaultServer(serverConfig);
        server.addInterfaceObject(HiService.class, hiService);
        server.addInterfaceObject(CourseService.class, courseService);
        server.start();

        System.out.println("server started ... ");
    }
}
