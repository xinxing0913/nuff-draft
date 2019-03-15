package com.mengzhidu.dream.nuff.spring.handler;

import com.mengzhidu.dream.nuff.spring.parser.ApplicationBeanDefinitionParser;
import com.mengzhidu.dream.nuff.spring.parser.InterfaceConfigBeanParser;
import com.mengzhidu.dream.nuff.spring.parser.NettyServerBeanDefinitionParser;
import com.mengzhidu.dream.nuff.spring.parser.ServerBeanDefinitionParser;
import com.mengzhidu.dream.nuff.spring.parser.ServiceBeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Nuff命名空间处理器
 * 我们这里支持配置的所有的命名空间有:
 * 1.application 表示一个应用
 * 2.server 表示一个服务端
 * 3.client 表示一个客户端
 * 4.connection 表示一个连接
 * 5.interface 表示一个接口
 * 6.method 表示一个方法
 * 7.common 表示通用配置
 */
public class NuffNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("application", new ApplicationBeanDefinitionParser());
        registerBeanDefinitionParser("interface", new InterfaceConfigBeanParser());
        registerBeanDefinitionParser("server", new ServerBeanDefinitionParser());
        registerBeanDefinitionParser("netty-server", new NettyServerBeanDefinitionParser());
        registerBeanDefinitionParser("service", new ServiceBeanDefinitionParser());
    }
}
