package de.o0o0o0.v1.beans.factory;

import de.o0o0o0.v1.beans.BeansException;

public class BeanCreationException extends BeansException {
    private String beanName;

    public BeanCreationException(String msg) {
        super(msg);
    }
    public BeanCreationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public BeanCreationException(String beanName, String msg) {
        super("Error Creating Bean with name'" + beanName + "':" + msg);
        this.beanName = beanName;
    }
    public BeanCreationException(String beanName, String msg, Throwable cause) {
        this(beanName, msg);
        initCause(cause);
    }

    public String getBeanName() {
        return beanName;
    }
}
