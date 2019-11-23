package de.o0o0o0.v2.test;

import de.o0o0o0.v7.beans.BeanDefinition;
import de.o0o0o0.v7.beans.factory.xml.XmlBeanDefinitionReader;
import de.o0o0o0.v7.beans.factory.support.DefaultBeanFactory;
import de.o0o0o0.v7.core.io.ClassPathResource;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class BeanDefinitionTest {
    @Test
    public void testGetBeanDefinition() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.LoadBeanDefinitionReader(new ClassPathResource("petstore-v2.xml"));

        BeanDefinition bd = factory.getBeanDefinition("petStore");
        List<PropertyValue> propertyValues = bd.getPropertyValues();
        Assert.assertEquals(2, propertyValues.size());
        {
            PropertyValue value = this.getPropertyValue("accountDao", propertyValues);
            Assert.assertNotNull(value);
            Assert.assertTrue(value.getValue() instanceof RuntimeBeanReference);
        }
        {
            PropertyValue value = this.getPropertyValue("itemDao", propertyValues);
            Assert.assertNotNull(value);
            Assert.assertTrue(value.getValue() instanceof RuntimeBeanReference);
        }
    }

    private PropertyValue getPropertyValue(String name, List<PropertyValue> propertyValues) {
        for (PropertyValue value : propertyValues) {
            if (value.getName().equals(name)) {
                return value;
            }
        }
        return null;
    }
}
