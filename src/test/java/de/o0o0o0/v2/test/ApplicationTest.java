package de.o0o0o0.v2.test;

import de.o0o0o0.v2.service.PetStoreService;
import de.o0o0o0.v7.context.ApplicationContext;
import de.o0o0o0.v7.context.support.ClassPathXmlApplicationContext;
import org.junit.Assert;
import org.junit.Test;

public class ApplicationTest {
    @Test
    public void testGetBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("petstore-v2.xml");
        PetStoreService storeService = (PetStoreService) context.getBean("petStore");

        Assert.assertNotNull(storeService);
        Assert.assertNotNull(storeService.getAccountDao());
        Assert.assertNotNull(storeService.getItemDao());
        Assert.assertEquals("alibaba", storeService.getOwner());
        Assert.assertEquals(7, storeService.getVersion());
    }
}
