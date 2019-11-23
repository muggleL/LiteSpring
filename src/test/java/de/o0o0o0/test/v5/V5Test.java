package de.o0o0o0.test.v5;

import de.o0o0o0.v1.service.PetStoreService;
import de.o0o0o0.v5.context.support.ClassPathXmlApplicationContext;
import org.junit.Assert;
import org.junit.Test;

public class V5Test {
    @Test
    public void testGetBean() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("petstore-v1.xml");
        PetStoreService service = (PetStoreService) ctx.getBean("petStore");
        Assert.assertNotNull(service);
    }
}
