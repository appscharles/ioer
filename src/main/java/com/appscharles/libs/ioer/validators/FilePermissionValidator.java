package com.appscharles.libs.ioer.validators;

import java.io.File;

/**
 * The type File permission validator.
 */
public class FilePermissionValidator {
    /**
     * Is writable boolean.
     *
     * @param file the file
     * @return the boolean
     */
    public static Boolean isWritable(File file){
        return file.renameTo(file);
    }
}