package com.appscharles.libs.ioer.services;

import com.appscharles.libs.ioer.exceptions.IoerException;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.io.File;

/**
 * The type Zip unziper.
 */
public class ZipUnziper {

    /**
     * Unzip.
     *
     * @param sourceFile     the source file
     * @param destinationDir the destination dir
     * @throws IoerException the ioer exception
     */
    public static void unzip(File sourceFile, File destinationDir) throws IoerException {
        try {
            ZipFile zipFile = new ZipFile(sourceFile);
            zipFile.extractAll(destinationDir.getAbsolutePath());
        } catch (ZipException e) {
            throw new IoerException(e);
        }
    }
}
