package com.mengzhidu.dream.nuff.spring.parser;

import com.mengzhidu.dream.nuff.spring.bean.Application;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

/**
 * Created by xinxing on 2018/12/20
 */
public class ApplicationBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class<?> getBeanClass(Element element) {
        return Application.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        String id = element.getAttribute("id");
        String name = element.getAttribute("name");
        builder.addPropertyValue("id", id);
        builder.addPropertyValue("name", name);
        super.doParse(element, builder);
    }
}
