package de.o0o0o0.v7.context.support;

import de.o0o0o0.utils.ClassUtils;
import de.o0o0o0.v7.beans.factory.support.DefaultBeanFactory;
import de.o0o0o0.v7.beans.factory.xml.XmlBeanDefinitionReader;
import de.o0o0o0.v7.context.ApplicationContext;
import de.o0o0o0.v7.core.io.Resource;

public abstract class AbstractApplicationContext implements ApplicationContext {

    private DefaultBeanFactory factory;
    private ClassLoader BeanClassLoader;

    public AbstractApplicationContext(String path) {
        factory = new DefaultBeanFactory();
        factory.setBeanClassLoader(this.getBeanCLassLoader());
        Resource resource = this.getResourceByPath(path);
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.LoadBeanDefinitionReader(resource);
    }

    abstract Resource getResourceByPath(String path);

    @Override
    public Object getBean(String beanID) {
        return factory.getBean(beanID);
    }

    @Override
    public void setBeanClassLoader(ClassLoader beanClassLoader) {
        this.BeanClassLoader = beanClassLoader;
    }

    @Override
    public ClassLoader getBeanCLassLoader() {
        return this.BeanClassLoader != null ? this.BeanClassLoader : ClassUtils.getDefaultClassLoader();
    }
}
