package de.o0o0o0.v7.beans.factory.support;

import de.o0o0o0.utils.ClassUtils;
import de.o0o0o0.v7.beans.BeanDefinition;
import de.o0o0o0.v7.beans.PropertyValue;
import de.o0o0o0.v7.config.ConfigurableBeanFactory;
import de.o0o0o0.v1.beans.factory.BeanCreationException;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultBeanFactory extends DefaultSingletonBeanRegister
        implements ConfigurableBeanFactory, BeanDefinitionRegister {

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
            return null;
        }
        if (bd.isSingleton()) {
            Object bean = this.getSingleton(beanID);
            if (bean == null) {
                bean = createBean(bd);
                this.registerSingleton(beanID, bean);
            }
            return bean;
        }
        return createBean(bd);
    }

    private Object createBean(BeanDefinition bd) {
        // 构建实例
        Object bean = instantiateBean(bd);
        // 设置属性
        populateBean(bd, bean);
        return bean;
    }

    private void populateBean(BeanDefinition bd, Object bean) {
        List<PropertyValue> values = bd.getPropertyValues();

        if (values == null || values.isEmpty()) {
            return;
        }
        BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(this);
        try {
            for (PropertyValue value : values) {
                String propertyName = value.getName();
                Object originalValue = value.getValue();
                Object resolverValue = resolver.resolveValueIfNecessary(originalValue);
                // 调用bean 的set 方法注入参数
                BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
                PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
                for (PropertyDescriptor descriptor : pds) {
                    if (descriptor.getName().equals(propertyName)) {
                        descriptor.getWriteMethod().invoke(bean, resolverValue);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            throw new BeanCreationException("failed to obtain BeanInfo from class [" + bd.getBeanClassName() + "]");

        }
    }

    private Object instantiateBean(BeanDefinition bd) {
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
