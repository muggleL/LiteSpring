package de.o0o0o0.test.v1;

import de.o0o0o0.v1.beans.BeanDefinition;
import de.o0o0o0.v1.beans.factory.BeanCreationException;
import de.o0o0o0.v1.beans.factory.BeanDefinitionStoreException;
import de.o0o0o0.v1.beans.factory.BeanFactory;
import de.o0o0o0.v1.beans.factory.support.DefaultBeanFactory;
import de.o0o0o0.v1.service.PetStoreService;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class BeanFactoryTest {
    // 通过配置文件 获取 配置定义
    @Test
    public void testGetBean(){
        // beanFactory
        BeanFactory factory = new DefaultBeanFactory("petstore-v1.xml");
        // 获取 bean
        BeanDefinition bd = factory.getBeanDefinition("petStore");
        // 判断 bean 名称
        assertEquals("de.o0o0o0.v1.service.PetStoreService", bd.getBeanClassName());
        // 返回 service 实例
        PetStoreService service = (PetStoreService) factory.getBean("petStore");
        // 判断 service 不为空
        System.out.println(service.getClass());
        assertNotNull(service);

    }

    @Test
    public void testInvalidBean(){
        BeanFactory factory = new DefaultBeanFactory("petstore-.xml");
        try {
            factory.getBean("invalidBean");
        } catch (BeanCreationException e) {
            return;
        }
        Assert.fail("expect BeanCreationException");

    }

    @Test
    public void testInvalidXML(){
        BeanFactory factory = new DefaultBeanFactory("petstore-v1.xml");
        try {
            factory.getBean("aBean");
        } catch (BeanDefinitionStoreException e) {
            return;
        }
        Assert.fail("expect BeanDefinitionStoreException");

    }

    public main Test() {
        System.out.println("Hello World")
    }
}
