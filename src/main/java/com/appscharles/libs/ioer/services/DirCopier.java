package com.appscharles.libs.ioer.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * The type Dir copier.
 */
public class DirCopier {

    /**
     * Copy.
     *
     * @param sourceDir          the source dir
     * @param destDir            the dest dir
     * @param standardCopyOption the standard copy option
     * @throws IOException the io exception
     */
    public  static void copy(File sourceDir, File destDir, StandardCopyOption standardCopyOption) throws IOException {
        if (sourceDir.isDirectory()) {
            if (!destDir.exists()) {
                destDir.mkdir();
            }
            String files[] = sourceDir.list();
            for (String file : files) {
                File srcFile = new File(sourceDir, file);
                File destFile = new File(destDir, file);
                copy(srcFile, destFile, standardCopyOption);
            }
        } else {
            Files.copy(sourceDir.toPath(), destDir.toPath(), standardCopyOption);
        }
    }
}
