package com.appscharles.libs.ioer.validators;

import java.io.File;
import java.io.IOException;

/**
 * The type Dir contains dir validator.
 */
public class DirContainsDirValidator {

    /**
     * Contains boolean.
     *
     * @param dir   the dir
     * @param inDir the in dir
     * @return the boolean
     * @throws IOException the io exception
     */
    public static Boolean contains(File dir, File inDir) throws IOException {
        if (dir.getAbsolutePath().equals(inDir.getAbsolutePath())){
            throw new IOException("In dir path is the same as dir path: " + dir.getAbsolutePath());
        }
        if (dir.isDirectory()== false){
            throw new IOException("Is not directory: " + dir.getAbsolutePath());
        }
        if (inDir.isDirectory()== false){
            throw new IOException("Is not directory: " + inDir.getAbsolutePath());
        }
        String pathDir = dir.getAbsolutePath();
        String pathInDirWithSeparator = inDir.getAbsolutePath() + File.separator;
        return pathDir.contains(pathInDirWithSeparator);
    }
}
