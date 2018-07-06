package com.appscharles.libs.ioer.services;

import com.appscharles.libs.ioer.converters.RelativeFileConverter;
import com.appscharles.libs.ioer.models.RelativeFile;
import com.appscharles.libs.ioer.models.StatusProgress;
import com.appscharles.libs.ioer.setters.StatusProgressSetter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

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
     * @param statusProgress     the status progress
     * @throws IOException the io exception
     */
    public static void copy(File sourceDir, File destDir, StandardCopyOption standardCopyOption) throws IOException {
        copy(sourceDir, destDir, standardCopyOption, null);
    }

    /**
     * Copy.
     *
     * @param sourceDir          the source dir
     * @param destDir            the dest dir
     * @param standardCopyOption the standard copy option
     * @param statusProgress     the status progress
     * @throws IOException the io exception
     */
    public static void copy(File sourceDir, File destDir, StandardCopyOption standardCopyOption, StatusProgress statusProgress) throws IOException {

        List<RelativeFile> relativeFiles = RelativeFileConverter.convert(sourceDir, DirReader.getFiles(sourceDir));
        for (int i = 0; i < relativeFiles.size(); i++) {
            if (relativeFiles.get(i).isDirectory()) {
                StatusProgressSetter.set(statusProgress, relativeFiles, i);
                File destFile = new File(destDir, relativeFiles.get(i).getRelativePath());
                if (destFile.exists() == false) {
                    destFile.mkdirs();
                }
            } else {
                StatusProgressSetter.set(statusProgress, relativeFiles, i);
                File destFile = new File(destDir, relativeFiles.get(i).getRelativePath());
                Files.copy(relativeFiles.get(i).toPath(), destFile.toPath(), standardCopyOption);
            }
        }
    }


}
