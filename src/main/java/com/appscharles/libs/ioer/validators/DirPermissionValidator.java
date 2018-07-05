package com.appscharles.libs.ioer.validators;

import com.appscharles.libs.ioer.services.DirReader;

import java.io.File;
import java.io.IOException;

/**
 * The type Dir permission validator.
 */
public class DirPermissionValidator {

    /**
     * Is writable boolean.
     *
     * @param file the file
     * @return the boolean
     * @throws IOException the io exception
     */
    public static Boolean isWritable(File file) throws IOException {
        for (File foundFile : DirReader.getFiles(file)) {
            if (FilePermissionValidator.isWritable(foundFile) == false){
                return false;
            }
        }
        return true;
    }
}
