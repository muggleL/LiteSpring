package de.o0o0o0.v6.beans.factory.support;

import de.o0o0o0.v6.beans.BeanDefinition;

public interface BeanDefinitionRegister {
    // 获取
    BeanDefinition getBeanDefinition(String beanID);
    // 注册
    void registerBeanDefinition(String id, BeanDefinition bd);
}