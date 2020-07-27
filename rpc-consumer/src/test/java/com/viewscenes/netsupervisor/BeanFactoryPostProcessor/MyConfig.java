package com.viewscenes.netsupervisor.BeanFactoryPostProcessor;

import com.viewscenes.netsupervisor.entity.Cat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    @Bean
    public Cat cat() {
        return new Cat();
    }

    @Bean
    public MyBeanDefinitionRegistryPostProcessor myBeanDefinitionRegistryPostProcessor() {
        return new MyBeanDefinitionRegistryPostProcessor();
    }
}
