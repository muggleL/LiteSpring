package de.o0o0o0.v6.context.support;

import de.o0o0o0.v6.core.io.ClassPathResource;
import de.o0o0o0.v6.core.io.Resource;


public class ClassPathXmlApplicationContext extends AbstractApplicationContext {
    public ClassPathXmlApplicationContext(String path) {
        super(path);
    }

    @Override
    Resource getResourceByPath(String path) {
        return new ClassPathResource(path, this.getBeanCLassLoader());
    }
}
