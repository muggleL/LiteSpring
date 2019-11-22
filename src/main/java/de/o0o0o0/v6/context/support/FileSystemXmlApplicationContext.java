package de.o0o0o0.v6.context.support;


import de.o0o0o0.v6.core.io.FileSystemResource;
import de.o0o0o0.v6.core.io.Resource;


public class FileSystemXmlApplicationContext extends AbstractApplicationContext {

    public FileSystemXmlApplicationContext(String path) {
        super(path);
    }

    @Override
    Resource getResourceByPath(String path) {
        return new FileSystemResource(path);
    }
}
