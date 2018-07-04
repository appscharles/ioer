package com.appscharles.libs.ioer.services;

import java.io.*;
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

    /**
     * Write file.
     *
     * @param file        the file
     * @param inputStream the input stream
     * @return the file
     * @throws IOException the io exception
     */
    public static File write(File file, InputStream inputStream) throws IOException {
        try (OutputStream outStream = new FileOutputStream(file)){
            byte[] buffer = new byte[1024];

            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, length);
            }
            return file;
        }
    }
}
