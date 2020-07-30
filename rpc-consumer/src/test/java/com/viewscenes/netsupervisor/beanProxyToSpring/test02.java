package com.viewscenes.netsupervisor.beanProxyToSpring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test02 {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("app.xml");
        Object cat = context.getBean("cat");
        Object cat1 = context.getBean("cat1");
        Object cat2 = context.getBean("cat2");
        System.out.println();
    }
}
