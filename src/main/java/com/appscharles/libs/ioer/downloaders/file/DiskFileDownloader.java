package com.appscharles.libs.ioer.downloaders.file;

import com.appscharles.libs.ioer.exceptions.IoerException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * The type Disk file downloader.
 */
public class DiskFileDownloader implements IFileDownloader {

    private File fromFile;

    /**
     * Instantiates a new Disk file downloader.
     *
     * @param fromFile the from file
     */
    public DiskFileDownloader(File fromFile) {
        this.fromFile = fromFile;
    }

    @Override
    public void download(File toFile) throws IoerException {
        try {
            if (toFile.getParentFile().exists() == false) {
                toFile.getParentFile().mkdirs();
            }
            Files.copy(this.fromFile.toPath(), toFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IoerException(e);
        }
    }
}
