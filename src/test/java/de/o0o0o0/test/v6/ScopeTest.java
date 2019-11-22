package de.o0o0o0.test.v6;

import de.o0o0o0.service.v1.PetStoreService;
import de.o0o0o0.v6.beans.BeanDefinition;
import de.o0o0o0.v6.beans.factory.support.DefaultBeanFactory;
import de.o0o0o0.v6.beans.factory.xml.XmlBeanDefinitionReader;
import de.o0o0o0.v6.core.io.ClassPathResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static de.o0o0o0.v6.beans.BeanDefinition.SCOPE_DEFAULT;

public class ScopeTest {
    private DefaultBeanFactory factory;
    private XmlBeanDefinitionReader reader;
    @Before
    public void init(){
        factory = new DefaultBeanFactory();
        reader = new XmlBeanDefinitionReader(factory);
    }
    @Test
    public void testGetBean(){
        reader.LoadBeanDefinitionReader(new ClassPathResource("petstore-v1.xml"));
        BeanDefinition bd = factory.getBeanDefinition("petStore");
        Assert.assertTrue(bd.isSingleton());
        Assert.assertFalse(bd.isPrototype());
        Assert.assertEquals(SCOPE_DEFAULT, bd.getScope());
        Assert.assertEquals("de.o0o0o0.service.v1.PetStoreService", bd.getBeanClassName());
        PetStoreService service = (PetStoreService) factory.getBean("petStore");
        Assert.assertNotNull(service);
        PetStoreService service1 = (PetStoreService) factory.getBean("petStore");
        Assert.assertEquals(service, service1);

    }
}
