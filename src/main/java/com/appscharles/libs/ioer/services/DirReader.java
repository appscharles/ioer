package com.appscharles.libs.ioer.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Dir reader.
 */
public class DirReader {

    /**
     * Gets regular files.
     *
     * @param dir the dir
     * @return the regular files
     * @throws IOException the io exception
     */
    public static List<File> getRegularFiles(File dir) throws IOException {
        return Files.walk(Paths.get(dir.getPath()))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());
    }

    public static List<File> getRegularDirectories(File dir) throws IOException {
        return Files.walk(Paths.get(dir.getPath()))
                .filter(Files::isDirectory)
                .map(Path::toFile)
                .collect(Collectors.toList());
    }

    /**
     * Gets files.
     *
     * @param dir the dir
     * @return the files
     * @throws IOException the io exception
     */
    public static List<File> getFiles(File dir) throws IOException {
        return Files.walk(Paths.get(dir.getPath()))
                .map(Path::toFile)
                .collect(Collectors.toList());
    }
}
