package com.viewscenes.netsupervisor.beanProxyToSpring;

import com.viewscenes.netsupervisor.entity.Cat;

public class MyBeanFactory {

    public static Cat creatCat(){
        return new Cat();
    }
}
