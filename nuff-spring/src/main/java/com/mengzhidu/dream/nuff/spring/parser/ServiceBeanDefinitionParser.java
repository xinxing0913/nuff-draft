package com.mengzhidu.dream.nuff.spring.parser;

import com.mengzhidu.dream.nuff.spring.bean.ServiceBean;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.w3c.dom.Element;

import javax.annotation.Resource;

/**
 * ServiceBean定义解析器
 */
public class ServiceBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class<?> getBeanClass(Element element) {
        return ServiceBean.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        String id = element.getAttribute("id");
        String interfaceName = element.getAttribute("interface");
        Object ref = element.getAttribute("ref");
        Integer port = Integer.valueOf(element.getAttribute("port"));

        builder.addPropertyValue("id", id);
        Class clazz = null;
        try {
            clazz = Class.forName(interfaceName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("ref是:" + ref);

        builder.addPropertyValue("interfaceClazz", clazz);
        builder.addPropertyValue("beanId", ref);
        builder.addPropertyValue("port", port);


        super.doParse(element, builder);
    }

}
