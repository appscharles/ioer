package com.appscharles.libs.ioer.validators;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * The type Dirs contains dir validator.
 */
public class DirsContainsDirValidator {

    /**
     * Contains boolean.
     *
     * @param dir    the dir
     * @param inDirs the in dirs
     * @return the boolean
     * @throws IOException the io exception
     */
    public static Boolean contains(File dir, List<File> inDirs) throws IOException {
        for (File inDir : inDirs) {
            if (DirContainsDirValidator.contains(dir, inDir)){
                return true;
            }
        }
        return false;
    }
}
