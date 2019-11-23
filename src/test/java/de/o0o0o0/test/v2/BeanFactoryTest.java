package de.o0o0o0.test.v2;

import de.o0o0o0.v1.service.PetStoreService;
import de.o0o0o0.v2.beans.BeanDefinition;
import de.o0o0o0.v2.beans.factory.support.DefaultBeanFactory;
import de.o0o0o0.v2.beans.factory.xml.XmlBeanDefinitionReader;
import org.junit.Assert;
import org.junit.Test;

public class BeanFactoryTest {
    @Test
    public void testGetBean(){
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.LoadBeanDefinitionReader("petstore-v1.xml");

        BeanDefinition definition = factory.getBeanDefinition("petStore");
        Assert.assertEquals("de.o0o0o0.v1.service.PetStoreService", definition.getBeanClassName());

        PetStoreService service = (PetStoreService) factory.getBean("petStore");
        Assert.assertNotNull(service);
    }
}
