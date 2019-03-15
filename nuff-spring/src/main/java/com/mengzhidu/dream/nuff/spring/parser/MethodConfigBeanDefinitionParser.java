package com.mengzhidu.dream.nuff.spring.parser;

import com.mengzhidu.dream.nuff.spring.bean.MethodConfig;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

/**
 * Created by xinxing on 2018/12/21
 */
public class MethodConfigBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class<?> getBeanClass(Element element) {
        return MethodConfig.class;
    }
}
