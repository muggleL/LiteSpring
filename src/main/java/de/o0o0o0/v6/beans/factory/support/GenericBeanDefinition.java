package de.o0o0o0.v6.beans.factory.support;

import de.o0o0o0.v6.beans.BeanDefinition;

public class GenericBeanDefinition implements BeanDefinition {

    private String id;
    private String beanClassName;
    private boolean singleton = true;
    private boolean prototype = false;
    private String scope = SCOPE_DEFAULT;

    public GenericBeanDefinition(String id, String beanClassName) {
        this.id = id;
        this.beanClassName = beanClassName;
    }

    public String getBeanClassName() {
        return this.beanClassName;
    }

    @Override
    public boolean isSingleton() {
        return this.singleton;
    }

    @Override
    public boolean isPrototype() {
        return this.prototype;
    }

    @Override
    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = scope.equals(SCOPE_SINGLETON) || scope.equals(SCOPE_DEFAULT); // 空也要判断
        this.prototype = scope.equals(SCOPE_PROTOTYPE);
    }

    @Override
    public String getScope() {
        return this.scope;
    }
}
