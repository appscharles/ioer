package com.appscharles.libs.ioer.checkers;

import com.appscharles.libs.ioer.services.DirReader;
import com.appscharles.libs.ioer.validators.DirsContainsDirValidator;
import com.appscharles.libs.ioer.validators.DirsContainsFileValidator;
import com.appscharles.libs.ioer.validators.FilePermissionValidator;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * The type In dir permission checker.
 */
public class InDirPermissionChecker {

    /**
     * Check.
     *
     * @param inDir      the in dir
     * @param ignoreDirs the ignore dirs
     * @throws IOException the io exception
     */
    public static void check(File inDir, List<File> ignoreDirs) throws IOException {
        for (File file : DirReader.getFiles(inDir)) {
            Boolean isRootDir = file.getAbsolutePath().equals(inDir.getAbsolutePath());
            if (isRootDir){
                continue;
            }
            if (ignoreDirs != null) {
                if (file.isFile()){
                    if (DirsContainsFileValidator.contains(file, ignoreDirs)){
                        continue;
                    }
                } else {
                    if (DirsContainsDirValidator.contains(file, ignoreDirs)){
                        continue;
                    }
                }
            }
            if (FilePermissionValidator.isWritable(file) == false){
                throw new IOException("File is not writable: " + file.getPath());
            }
        }
    }
}
