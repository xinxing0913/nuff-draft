package com.mengzhidu.dream.nuff.spring.parser;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

/**
 * 服务类的Bean配置解析器
 * 通常服务类的配置应该如下:
 *
 * <nuff:server port = "xxx" bossThreads = "xxx" worderThreads = "xxx" >
 *     <nuff:invoke type="before" class="" />
 *     <nuff:invoke type="after" class="" />
 * </nuff:server>
 */
public class ServerBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class<?> getBeanClass(Element element) {
        return super.getBeanClass(element);
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        super.doParse(element, builder);
    }
}
