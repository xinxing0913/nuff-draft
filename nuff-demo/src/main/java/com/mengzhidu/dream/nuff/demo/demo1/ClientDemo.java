package com.mengzhidu.dream.nuff.demo.demo3;

import com.mengzhidu.dream.nuff.demo.common.HiService;
import com.mengzhidu.dream.nuff.remote.rpc.netty4.client.RPCClientProxyBuilder;

/**
 * Created by xinxing on 2018/12/16
 */
public class ClientDemo {

    public static void main(String[] args) {
        HiService hiService = RPCClientProxyBuilder.create(HiService.class)
                .timeout(0)
                .connect("127.0.0.1", 3190)
                .build();

        for (int i = 0; i < 10; i++) {
            System.out.println("调用结果:" + hiService.hello("nuff" + i));
        }
    }
}
