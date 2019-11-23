package de.o0o0o0.test.v4;

import de.o0o0o0.v1.service.PetStoreService;
import de.o0o0o0.v4.context.ApplicationContext;
import de.o0o0o0.v4.context.support.ClassPathXmlApplicationContext;
import de.o0o0o0.v4.context.support.FileSystemXmlApplicationContext;
import org.junit.Assert;
import org.junit.Test;

public class ResourceTest {
    @Test
    public void testClassPathResource() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("petstore-v1.xml");
        PetStoreService storeService = (PetStoreService) ctx.getBean("petStore");
        Assert.assertNotNull(storeService);
    }
    @Test
    public void testFileSystemResource() {
        ApplicationContext ctx = new FileSystemXmlApplicationContext("/Users/d.glua/IdeaProjects/liteSpring/src/test/resources/petstore-v1.xml");
        PetStoreService storeService = (PetStoreService) ctx.getBean("petStore");
        Assert.assertNotNull(storeService);
    }
}
