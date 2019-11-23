package de.o0o0o0.v2.test;

import de.o0o0o0.v2.dao.AccountDao;
import de.o0o0o0.v7.beans.factory.support.BeanDefinitionValueResolver;
import de.o0o0o0.v7.beans.factory.support.DefaultBeanFactory;
import de.o0o0o0.v7.beans.factory.xml.XmlBeanDefinitionReader;
import de.o0o0o0.v7.config.RuntimeBeanReference;
import de.o0o0o0.v7.config.TypedStringValue;
import de.o0o0o0.v7.core.io.ClassPathResource;
import org.junit.Assert;
import org.junit.Test;

public class BeanDefinitionValueResolverTest {
    @Test
    public void testResolveRuntimeBeanReference() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.LoadBeanDefinitionReader(new ClassPathResource("petstore-v2.xml"));

        BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(factory);
        RuntimeBeanReference reference = new RuntimeBeanReference("accountDao");
        Object value = resolver.resolveValueIfNecessary(reference);

        Assert.assertNotNull(value);
        Assert.assertTrue(value instanceof AccountDao);
    }
    @Test
    public void testResolveTypedStringValue() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.LoadBeanDefinitionReader(new ClassPathResource("petstore-v2.xml"));

        BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(factory);

        TypedStringValue value = new TypedStringValue("test");
        Object val = resolver.resolveValueIfNecessary(value);
        Assert.assertNotNull(val);
        Assert.assertEquals(val, "test");
    }
}
