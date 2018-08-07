package com.appscharles.libs.ioer.downloaders.file;

import com.appscharles.libs.ioer.exceptions.IoerException;

import java.io.File;

/**
 * The interface File downloader.
 */
public interface IFileDownloader {

    /**
     * Download.
     *
     * @param toFile the to file
     * @throws IoerException the ioer exception
     */
    void download(File toFile) throws IoerException;
}
