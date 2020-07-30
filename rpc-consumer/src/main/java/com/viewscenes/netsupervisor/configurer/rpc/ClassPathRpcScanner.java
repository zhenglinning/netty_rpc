package com.viewscenes.netsupervisor.configurer.rpc;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Set;

/**
 * Created by MACHENIKE on 2018-12-03.
 */
public class ClassPathRpcScanner extends ClassPathBeanDefinitionScanner{

    private RpcFactoryBean<?> rpcFactoryBean = new RpcFactoryBean<Object>();

    private Class<? extends Annotation> annotationClass;

    public void setAnnotationClass(Class<? extends Annotation> annotationClass) {
        this.annotationClass = annotationClass;
    }

    public ClassPathRpcScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }



    public Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);

        if (beanDefinitions.isEmpty()) {
            logger.warn("No RPC mapper was found in '"
                    + Arrays.toString(basePackages)
                    + "' package. Please check your configuration.");
        } else {
            processBeanDefinitions(beanDefinitions);
        }

        return beanDefinitions;
    }


    private void processBeanDefinitions(Set<BeanDefinitionHolder> beanDefinitions) {

        GenericBeanDefinition definition;

        for (BeanDefinitionHolder holder : beanDefinitions) {

            definition = (GenericBeanDefinition) holder.getBeanDefinition();

           //  在这里，我们可以给该对象的属性注入对应的实例。
           //  比如mybatis，就在这里注入了dataSource和sqlSessionFactory，
           //  注意，如果采用definition.getPropertyValues()方式的话，
           //  类似definition.getPropertyValues().add("interfaceType", beanClazz);
           //  则要求在FactoryBean（本应用中即ServiceFactory）提供setter方法，否则会注入失败
           //  如果采用definition.getConstructorArgumentValues()，
           //  则FactoryBean中需要提供包含该属性的构造方法，否则会注入失败
//            definition.getPropertyValues().add("interfaceType", definition.getBeanClassName());

//  addGenericArgumentValue 给泛型添加值
            definition.getConstructorArgumentValues().addGenericArgumentValue(definition.getBeanClassName());

//            definition.getConstructorArgumentValues().addGenericArgumentValue("com.viewscenes.netsupervisor.entity.InfoUser");



            //注意，这里的BeanClass是生成Bean实例的工厂，不是Bean本身。
            // FactoryBean是一种特殊的Bean，其返回的对象不是指定类的一个实例，
            // 其返回的是该工厂Bean的getObject方法所返回的对象。
            definition.setBeanClass(this.rpcFactoryBean.getClass());

            definition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
            System.out.println(holder);
        }
    }

    public void registerFilters() {
        boolean acceptAllInterfaces = true;

        if (this.annotationClass != null) {
            addIncludeFilter(new AnnotationTypeFilter(this.annotationClass));
            acceptAllInterfaces = false;
        }

        if (acceptAllInterfaces) {
            addIncludeFilter(new TypeFilter() {
                @Override
                public boolean match(MetadataReader metadataReader,
                                     MetadataReaderFactory metadataReaderFactory) {
                    return true;
                }
            });
        }

        // exclude package-info.java
        addExcludeFilter(new TypeFilter() {
            @Override
            public boolean match(MetadataReader metadataReader,
                                 MetadataReaderFactory metadataReaderFactory)
                    throws IOException {
                String className = metadataReader.getClassMetadata()
                        .getClassName();
                return className.endsWith("package-info");
            }
        });
    }


    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
    }
}
