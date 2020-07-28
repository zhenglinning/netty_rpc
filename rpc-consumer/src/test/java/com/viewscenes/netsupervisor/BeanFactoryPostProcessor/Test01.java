package com.viewscenes.netsupervisor.BeanFactoryPostProcessor;

import com.viewscenes.netsupervisor.entity.Dog;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

public class Test01 {

    /**  https://juejin.im/post/5d99fcc7e51d457822796ea1
     * spring自动装配模型
     1、AUTOWIRE_NO = 0；（这个NO咋说呢，理解为不使用自动装配吧，spring默认的值）
     2、AUTOWIRE_BY_NAME = 1；（通过名字自动装配）
     3、AUTOWIRE_BY_TYPE = 2；（通过类型自动装配）
     4、 AUTOWIRE_CONSTRUCTOR= 3；（通过构造函数自动装配）
     5、AUTOWIRE_AUTODETECT = 4；（已经被标注过时，本文不再讨论）
     我们在开发中使用最常见的应该就是通过@Autowired注解来完成注入的。
     这里有个常见的误区，网上看到说使用@Autowired就是通过类型来装配的。在我理解看来，这种说法是不对的。原因有两点：
     1、@Autowired可以通过类型来找对应的类，如果通过类型找不到就通过名字来找，如果还是找不到就会排除异常。
     2、虽然使用了@Autowired注解，但装配模型依然还是AUTOWIRE_NO，并未看到有改变装配模型的源码。这也是spring默认的装配模型

     todo
     spring 中 bean 都有自己的bean定义  bean定义会有一个 赋值 模型 称之为 spring自动装配模型
     一般来说 默认的 是  0 AUTOWIRE_NO 只能使用  @Autowired 才能实现装配
     AUTOWIRE_BY_NAME  通过set方法，并且 set方法的名称需要和bean的name一致     byName
     AUTOWIRE_BY_TYPE 通过set方法,并且再根据bean的类型，注入属性，是通过类型配置  byType
     autowire_construcor: 通过构造器注入
     这种 创建bean 时候 会根据 对应的 by——name  by-type 进行装配  就不需要写 Autowired注解了


     *
     */
    @Test
    public void beanTest() {

        //todo 通过注解类创建spring容器
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MyConfig.class);
        Object dog = app.getBean("CalculateService");

        GenericBeanDefinition beanDefinition = (GenericBeanDefinition)app.getBeanDefinition("CalculateService");

        System.out.println(beanDefinition.getAutowireMode());


//        Object dog = app.getBean("dog");
        Object cat = app.getBean("person");

        //通过class 过去 对于应类型的 对象
//        Dog bean = app.getBean(Dog.class);
        GenericBeanDefinition catDefinition = (GenericBeanDefinition)app.getBeanDefinition("cat");
        System.out.println(catDefinition.getAutowireMode());

        System.out.println();
    }

    @Test
    public void testSimpleScan() {
        String BASE_PACKAGE = "com.viewscenes.netsupervisor.entity";
        GenericApplicationContext context = new GenericApplicationContext();
        MyClassPathDefinitonScanner myClassPathDefinitonScanner = new MyClassPathDefinitonScanner(context, Component.class);
        // 注册过滤器
        myClassPathDefinitonScanner.registerTypeFilter();
        int beanCount = myClassPathDefinitonScanner.scan(BASE_PACKAGE);
        context.refresh();
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        System.out.println(beanCount);
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }
}
