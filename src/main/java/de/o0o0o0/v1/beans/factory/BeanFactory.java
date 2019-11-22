package de.o0o0o0.v1.beans.factory;

import de.o0o0o0.v1.beans.BeanDefinition;

public interface BeanFactory {
    BeanDefinition getBeanDefinition(String beanID);

    Object getBean(String beanID);
}
