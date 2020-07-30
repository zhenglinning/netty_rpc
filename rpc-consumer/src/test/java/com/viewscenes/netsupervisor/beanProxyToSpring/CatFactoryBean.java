package com.viewscenes.netsupervisor.beanProxyToSpring;

import com.viewscenes.netsupervisor.entity.Cat;
import org.springframework.beans.factory.FactoryBean;

public class CatFactoryBean implements FactoryBean<Cat> {

    public CatFactoryBean() {

        System.out.println("CatFactoryBean 构造器");
    }

    @Override
    public Cat getObject()  {
        System.out.println(" getObject ");
        Cat cat = new Cat();
        return cat;
    }

    @Override
    public Class<?> getObjectType() {
        System.out.println("getObjectType");
        return Cat.class;
    }

    /**
     * 设置是否是 单例
     * @return
     */
    @Override
    public boolean isSingleton() {
        System.out.println("isSingleton");
        return true;
    }

}