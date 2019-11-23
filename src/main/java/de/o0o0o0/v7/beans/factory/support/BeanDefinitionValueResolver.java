package de.o0o0o0.v7.beans.factory.support;

import de.o0o0o0.v7.config.RuntimeBeanReference;
import de.o0o0o0.v7.config.TypedStringValue;

public class BeanDefinitionValueResolver {
    private final DefaultBeanFactory factory;

    public BeanDefinitionValueResolver(DefaultBeanFactory factory) {
        this.factory = factory;
    }

    public Object resolveValueIfNecessary(Object value) {
        if (value instanceof RuntimeBeanReference) {
            String refName = ((RuntimeBeanReference) value).getBeanName();
            return factory.getBean(refName);
        } else if (value instanceof TypedStringValue) {
            return ((TypedStringValue) value).getValue();
        }
        // TODO
        throw new RuntimeException("the value " + value + " has not implemented");
    }
}
