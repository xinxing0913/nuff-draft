package com.mengzhidu.dream.nuff.demo.demo1;

import com.mengzhidu.dream.nuff.demo.common.HiService;
import com.mengzhidu.dream.nuff.remote.rpc.builder.ClientProxyBuilder;
import com.mengzhidu.dream.nuff.remote.rpc.client.Client;
import com.mengzhidu.dream.nuff.remote.rpc.client.ClientConfig;
import com.mengzhidu.dream.nuff.remote.rpc.netty4.client.DefaultClient;

/**
 * Created by xinxing on 2018/12/16
 */
public class ClientDemo {

    public static void main(String[] args) throws Exception {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setHost("127.0.0.1");
        clientConfig.setPort(3190);
        clientConfig.setTimeout(0);


        Client client = new DefaultClient();
        client.init(clientConfig);


        HiService hiService = ClientProxyBuilder.create(HiService.class)
                .timeout(0)
                .client(client)
                .build();

        for (int i = 0; i < 10; i++) {
            System.out.println("调用结果:" + hiService.hello("nuff" + i));
        }
    }
}
