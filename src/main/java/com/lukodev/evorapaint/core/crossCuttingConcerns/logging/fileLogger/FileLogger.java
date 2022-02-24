package com.lukodev.evorapaint.core.crossCuttingConcerns.logging.fileLogger;

import java.io.IOException;

public interface FileLogger {

    void verbose(String message) throws IOException;
    void fatal(String message) throws IOException;
    void info(String message) throws IOException;
    void warn(String message) throws IOException;
    void debug(String message) throws IOException;
    void error(String message) throws IOException;
}
