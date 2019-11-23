package de.o0o0o0.v7.core.io;

import de.o0o0o0.utils.ClassUtils;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ClassPathResource implements Resource {
    private String path;
    private ClassLoader cl;

    public ClassPathResource(String path) {
        this(path, null);
    }

    public ClassPathResource(String path, ClassLoader cl) {
        this.path = path;
        this.cl = (cl != null ? cl : ClassUtils.getDefaultClassLoader());
    }

    @Override
    public InputStream getInputStream() throws FileNotFoundException {
        InputStream is = this.cl.getResourceAsStream(this.path);
        if (is == null) {
            throw new FileNotFoundException(path + "cannot be opened");
        }
        return is;
    }

    @Override
    public String getDescription() {
        return this.path;
    }
}
