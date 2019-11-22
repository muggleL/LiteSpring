package de.o0o0o0.v5.beans.factory.support;

import de.o0o0o0.utils.ClassUtils;
import de.o0o0o0.v1.beans.factory.BeanCreationException;
import de.o0o0o0.v5.beans.BeanDefinition;
import de.o0o0o0.v5.config.ConfigurableBeanFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultBeanFactory implements ConfigurableBeanFactory, BeanDefinitionRegister {

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    private ClassLoader beanClassLoader;

    public BeanDefinition getBeanDefinition(String beanID) {
        return this.beanDefinitionMap.get(beanID);
    }

    @Override
    public void registerBeanDefinition(String id, BeanDefinition bd) {
        this.beanDefinitionMap.put(id, bd);
    }


    public Object getBean(String beanID) {
        BeanDefinition bd = this.getBeanDefinition(beanID);
        if (bd == null) {
            throw new BeanCreationException("Bean Definition Does Not Exist");
        }
        ClassLoader cl = this.getBeanCLassLoader();
        String beanClassName = bd.getBeanClassName();
        try {
            Class<?> clz = cl.loadClass(beanClassName);
            return clz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new BeanCreationException("Create Bean For Class '" + beanClassName + "' failed");
        }
    }

    @Override
    public void setBeanClassLoader(ClassLoader beanClassLoader) {
        this.beanClassLoader = beanClassLoader;
    }

    @Override
    public ClassLoader getBeanCLassLoader() {
        return this.beanClassLoader != null ? this.beanClassLoader : ClassUtils.getDefaultClassLoader();
    }
}
