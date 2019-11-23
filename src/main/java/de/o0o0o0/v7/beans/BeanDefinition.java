package de.o0o0o0.v7.beans;

import java.util.List;

public interface BeanDefinition {
    String SCOPE_SINGLETON = "singleton";
    String SCOPE_DEFAULT = "";
    String SCOPE_PROTOTYPE = "prototype";

    String getBeanClassName();

    boolean isSingleton();

    boolean isPrototype();

    void setScope(String scope);

    String getScope();

    List<PropertyValue> getPropertyValues();
}
