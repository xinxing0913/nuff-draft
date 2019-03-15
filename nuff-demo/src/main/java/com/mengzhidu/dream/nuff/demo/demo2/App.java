package com.mengzhidu.dream.nuff.demo.demo2;

import com.mengzhidu.dream.nuff.spring.bean.Application;
import com.mengzhidu.dream.nuff.spring.bean.ServiceBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by xinxing on 2018/12/20
 */
public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:demo2.xml");
        System.out.println("server start ...");
        ServiceBean serviceBean = (ServiceBean)context.getBean("remoteHiService");
        System.out.println("serviceBean:" + serviceBean);
    }

}
