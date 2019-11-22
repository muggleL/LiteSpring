package de.o0o0o0.v3.context.support;

import de.o0o0o0.v3.beans.factory.support.DefaultBeanFactory;
import de.o0o0o0.v3.beans.factory.xml.XmlBeanDefinitionReader;
import de.o0o0o0.v3.context.ApplicationContext;

public class ClassPathXmlApplicationContext implements ApplicationContext {
    private DefaultBeanFactory factory = null;

    public ClassPathXmlApplicationContext(String configFile) {
        factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.LoadBeanDefinitionReader(configFile);
    }
    @Override
    public Object getBean(String beanID) {
        return factory.getBean(beanID);
    }
}
