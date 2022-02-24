package com.lukodev.evorapaint.core.crossCuttingConcerns.logging.fileLogger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

@Component
public class FileLoggerImpl implements FileLogger{

    private String path;
    private Environment environment;

    @Autowired
    public FileLoggerImpl(Environment environment){
        this.environment = environment;
        path = this.environment.getProperty("file_logger_path");
    }


    @Override
    public void verbose(String message) throws IOException {
        this.log("[VERBOSE] " + message);
    }

    @Override
    public void fatal(String message) throws IOException {
        this.log("[FATAL] " + message);
    }

    @Override
    public void info(String message) throws IOException {
        this.log("[INFO] " + message);
    }

    @Override
    public void warn(String message) throws IOException {
        this.log("[WARN] " + message);
    }

    @Override
    public void debug(String message) throws IOException {
        this.log("[DEBUG] " + message);
    }

    @Override
    public void error(String message) throws IOException {
        this.log("[ERROR] " + message);
    }

    private void log(String message) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path + "/log.log",true));
        writer.write(message + "\n");
        writer.close();
    }
}
