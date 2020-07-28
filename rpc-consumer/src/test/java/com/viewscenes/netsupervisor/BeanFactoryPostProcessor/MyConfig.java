package com.viewscenes.netsupervisor.BeanFactoryPostProcessor;

import com.viewscenes.netsupervisor.beanProxyToSpring.ServiceBeanDefinitionRegistry;
import com.viewscenes.netsupervisor.entity.Cat;
import com.viewscenes.netsupervisor.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan("com.viewscenes.netsupervisor.entity")
@Import(MyImportBeanDefinitionRegistrar.class)
public class MyConfig {

//    @Bean
//    public Cat cat() {
//        return new Cat();
//    }
//
//    @Bean
//    public Person person() {
//        return new Person();
//    }


//    @Bean
//    public MyBeanDefinitionRegistryPostProcessor myBeanDefinitionRegistryPostProcessor() {
//        return new MyBeanDefinitionRegistryPostProcessor();
//    }

    @Bean
    public ServiceBeanDefinitionRegistry ServiceBeanDefinitionRegistry() {
        return new ServiceBeanDefinitionRegistry();
    }
}
