package de.o0o0o0.v7.beans.factory.support;

import de.o0o0o0.utils.Assert;
import de.o0o0o0.v7.config.SingletonBeanRegister;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultSingletonBeanRegister implements SingletonBeanRegister {
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        Assert.notNull(beanName, "'beanName' must be not null");
        Object oldObject = this.singletonObjects.get(beanName);
        if (oldObject != null) {
            throw new IllegalStateException("Could not register object [" + singletonObject + "] under" +
                    " bean name '" + beanName + "': there is already object [" + oldObject + "]");
        }
        this.singletonObjects.put(beanName, singletonObject);
    }

    @Override
    public Object getSingleton(String beanName) {
        return this.singletonObjects.get(beanName);
    }
}
