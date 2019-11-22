package de.o0o0o0.v1.beans.factory;

import de.o0o0o0.v1.beans.BeansException;

public class BeanDefinitionStoreException extends BeansException {
    public BeanDefinitionStoreException(String msg, Throwable cause){
        super(msg, cause);
    }
}
