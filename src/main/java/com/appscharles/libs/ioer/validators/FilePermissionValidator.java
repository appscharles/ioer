package com.appscharles.libs.ioer.validators;

import java.io.File;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 05.07.2018
 * Time: 13:55
 * Project name: ioer
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class FilePermissionValidator {
    public static Boolean isWritable(File file){
        return file.renameTo(file);
    }
}