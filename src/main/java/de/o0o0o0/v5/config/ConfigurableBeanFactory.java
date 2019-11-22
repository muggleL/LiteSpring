package de.o0o0o0.v5.config;

import de.o0o0o0.v4.beans.factory.BeanFactory;

public interface ConfigurableBeanFactory extends BeanFactory {
    void setBeanClassLoader(ClassLoader beanClassLoader);
    ClassLoader getBeanCLassLoader();
}
