package com.mengzhidu.dream.nuff.spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * Created by xinxing on 2018/12/22
 */
public class ServiceBean implements BeanFactoryAware, BeanPostProcessor, InitializingBean, DisposableBean,
        ApplicationListener<ContextRefreshedEvent> {

    private BeanFactory beanFactory;

    private String id;

    private Class<?> interfaceClazz;

    private Object object;

    private Integer port;

    private String beanId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        System.out.println("set id --------");
        this.id = id;
    }

    public Class<?> getInterfaceClazz() {
        return interfaceClazz;
    }

    public void setInterfaceClazz(Class<?> interfaceClazz) {
        System.out.println("set interface ....");
        this.interfaceClazz = interfaceClazz;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        System.out.println("set object ...");
        this.object = object;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        System.out.println("set port ....");
        this.port = port;
    }

    public String getBeanId() {
        return beanId;
    }

    public void setBeanId(String beanId) {
        this.beanId = beanId;
    }

    @Override
    public void destroy() throws Exception {
        // 取消被暴露的服务
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.object = beanFactory.getBean(this.beanId);

    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("被初始化");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
