package de.o0o0o0.v2.beans.factory;

import de.o0o0o0.v2.beans.BeanDefinition;

public interface BeanFactory {
    Object getBean(String beanID);
}
