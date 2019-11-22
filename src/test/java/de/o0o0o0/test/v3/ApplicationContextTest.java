package de.o0o0o0.test.v3;

import de.o0o0o0.service.v1.PetStoreService;
import de.o0o0o0.v3.context.ApplicationContext;
import de.o0o0o0.v3.context.support.ClassPathXmlApplicationContext;
import org.junit.Assert;
import org.junit.Test;

public class ApplicationContextTest {
    @Test
    public void testGetBean(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("petstore-v1.xml");
        PetStoreService storeService = (PetStoreService) ctx.getBean("petStore");
        Assert.assertNotNull(storeService);
    }
}
