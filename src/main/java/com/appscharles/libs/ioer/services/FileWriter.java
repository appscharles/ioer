package com.appscharles.libs.ioer.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

/**
 * The type File writer.
 */
public class FileWriter {

    /**
     * Write.
     *
     * @param file    the file
     * @param content the content
     * @throws IOException the io exception
     */
    public static void write(File file, String content) throws IOException {
        if (file.getParentFile().exists() == false){
            file.getParentFile().mkdirs();
        }
        try (OutputStreamWriter writer =
                     new OutputStreamWriter(new FileOutputStream(file) , StandardCharsets.UTF_8)) {
            writer.write(content);
        }
    }
}
