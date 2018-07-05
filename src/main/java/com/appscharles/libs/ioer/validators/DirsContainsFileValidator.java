package com.appscharles.libs.ioer.validators;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * The type Dirs contains file validator.
 */
public class DirsContainsFileValidator {

    /**
     * Contains boolean.
     *
     * @param file   the file
     * @param inDirs the in dirs
     * @return the boolean
     * @throws IOException the io exception
     */
    public static Boolean contains(File file, List<File> inDirs) throws IOException {
        for (File inDir : inDirs) {
            if (DirContainsFileValidator.contains(file, inDir)){
                return true;
            }
        }
        return false;
    }
}
