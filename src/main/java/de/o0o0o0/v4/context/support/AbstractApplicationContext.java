package de.o0o0o0.v4.context.support;

import de.o0o0o0.v4.beans.factory.support.DefaultBeanFactory;
import de.o0o0o0.v4.beans.factory.xml.XmlBeanDefinitionReader;
import de.o0o0o0.v4.context.ApplicationContext;
import de.o0o0o0.v4.core.io.Resource;

public abstract class AbstractApplicationContext implements ApplicationContext {

    private DefaultBeanFactory factory;

    public AbstractApplicationContext(String path) {
        factory = new DefaultBeanFactory();
        Resource resource = this.getResourceByPath(path);
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.LoadBeanDefinitionReader(resource);
    }

    abstract Resource getResourceByPath(String path);

    @Override
    public Object getBean(String beanID) {
        return factory.getBean(beanID);
    }
}
