package com.viewscenes.netsupervisor.configurer.rpc;

import com.viewscenes.netsupervisor.entity.InfoUser;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Proxy;

/**
 * Created by MACHENIKE on 2018-12-03.
 */
public class RpcFactoryBean<T> implements FactoryBean<T> {

    private Class<T> rpcInterface;

    @Autowired
    private RpcFactory<T> factory;

    public RpcFactoryBean() {
    }

    public RpcFactoryBean(Class<T> rpcInterface) {
        System.out.println();
        this.rpcInterface = rpcInterface;
    }

    public T getObject() throws Exception {
        System.out.println("1111111111111111111-------RpcFactoryBean getObject ");
        return getRpc();
    }

    public Class<?> getObjectType() {
        System.out.println("1111111111111111111-------RpcFactoryBean getObjectType ");
        return this.rpcInterface;
    }

    public boolean isSingleton() {
        System.out.println("1111111111111111111-------RpcFactoryBean isSingleton ");

        return true;
    }

    public <T> T getRpc() {
        return (T) Proxy.newProxyInstance(rpcInterface.getClassLoader(), new Class[]{rpcInterface}, factory);
    }
}
