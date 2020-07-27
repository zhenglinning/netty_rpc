package com.viewscenes.netsupervisor.BeanFactoryPostProcessor;

import com.viewscenes.netsupervisor.entity.Dog;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test01 {

    @Test
    public void beanTest() {

        //todo 通过注解类创建spring容器
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MyConfig.class);
        Object dog = app.getBean("dog");
        Object cat = app.getBean("cat");

        //通过class 过去 对于应类型的 对象
        Dog bean = app.getBean(Dog.class);

        System.out.println();
    }
}
