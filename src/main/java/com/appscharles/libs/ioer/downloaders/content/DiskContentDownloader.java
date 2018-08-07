package com.appscharles.libs.ioer.downloaders.content;

import com.appscharles.libs.ioer.exceptions.IoerException;
import com.appscharles.libs.ioer.services.FileReader;

import java.io.File;
import java.io.IOException;

/**
 * The type Disk content downloader.
 */
public class DiskContentDownloader implements IContentDownloader {

    private File file;

    /**
     * Instantiates a new Disk content downloader.
     *
     * @param file the file
     */
    public DiskContentDownloader(File file) {
        this.file = file;
    }

    @Override
    public String getContent() throws IoerException {
        try {
            return FileReader.read(this.file);
        } catch (IOException e) {
        throw new IoerException(e);
        }
    }
}
