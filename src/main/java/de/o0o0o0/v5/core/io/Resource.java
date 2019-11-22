package de.o0o0o0.v5.core.io;

import java.io.FileNotFoundException;
import java.io.InputStream;

public interface Resource {
    InputStream getInputStream() throws FileNotFoundException;
    String getDescription();
}
