package com.appscharles.libs.ioer.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * The type Dir deleter.
 */
public class DirDeleter {

    /**
     * Delete.
     *
     * @param dir the dir
     * @throws IOException the io exception
     */
    public static void delete(File dir) throws IOException {
        Files.walk(dir.toPath())
                .map(Path::toFile)
                .sorted((o1, o2) -> -o1.compareTo(o2))
                .forEach(File::delete);
    }
}
