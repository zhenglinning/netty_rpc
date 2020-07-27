package com.viewscenes.netsupervisor.BeanFactoryPostProcessor;

import com.viewscenes.netsupervisor.entity.Dog;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;

public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    /**
     * 所有Bean定义信息将要被加载，Bean实例还未创建的时候执行，优先postProcessBeanFactory执行。
     * @param beanDefinitionRegistry
     * @throws BeansException
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        System.out.println("---->postProcessBeanDefinitionRegistry容器中BeanDefinition的数量为："
                + beanDefinitionRegistry.getBeanDefinitionCount());
        RootBeanDefinition beanDefinition = new RootBeanDefinition(Dog.class);
        beanDefinitionRegistry.registerBeanDefinition("dog", beanDefinition);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println("====>postProcessBeanFactory容器中BeanDefinition的数量为："
                + configurableListableBeanFactory.getBeanDefinitionCount());

    }
}
