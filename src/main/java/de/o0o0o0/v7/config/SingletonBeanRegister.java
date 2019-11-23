package de.o0o0o0.v7.config;

public interface SingletonBeanRegister {
    void registerSingleton(String beanName, Object singletonObject);

    Object getSingleton(String beanName);
}
