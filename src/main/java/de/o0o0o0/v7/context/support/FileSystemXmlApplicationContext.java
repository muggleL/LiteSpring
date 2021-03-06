package de.o0o0o0.v7.context.support;


import de.o0o0o0.v7.core.io.FileSystemResource;
import de.o0o0o0.v7.core.io.Resource;


public class FileSystemXmlApplicationContext extends AbstractApplicationContext {

    public FileSystemXmlApplicationContext(String path) {
        super(path);
    }

    @Override
    Resource getResourceByPath(String path) {
        return new FileSystemResource(path);
    }
}
