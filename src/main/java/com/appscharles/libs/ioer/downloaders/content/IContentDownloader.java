package com.appscharles.libs.ioer.downloaders.content;

import com.appscharles.libs.ioer.exceptions.IoerException;

/**
 * The interface Content downloader.
 */
public interface IContentDownloader {

    /**
     * Gets content.
     *
     * @return the content
     * @throws IoerException the ioer exception
     */
    String getContent() throws IoerException;
}
