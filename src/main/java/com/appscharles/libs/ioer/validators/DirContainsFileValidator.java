package com.appscharles.libs.ioer.validators;

import java.io.File;
import java.io.IOException;

/**
 * The type Dir contains file validator.
 */
public class DirContainsFileValidator {

    /**
     * Contains boolean.
     *
     * @param file  the file
     * @param inDir the in dir
     * @return the boolean
     * @throws IOException the io exception
     */
    public static Boolean contains(File file, File inDir) throws IOException {
        if (file.getAbsolutePath().equals(inDir.getAbsolutePath())){
            throw new IOException("In dir path is the same as file path: " + file.getAbsolutePath());
        }
        if (inDir.isDirectory()== false){
            throw new IOException("Is not directory: " + inDir.getAbsolutePath());
        }
        if (file.isDirectory()){
            throw new IOException("Is not directory: " + file.getAbsolutePath());
        }
        String pathFile = file.getAbsolutePath();
        String pathInDirWithSeparator = inDir.getAbsolutePath() + File.separator;
        return pathFile.contains(pathInDirWithSeparator);
    }
}
