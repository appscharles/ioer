package com.appscharles.libs.ioer.downloaders.weight;

import com.appscharles.libs.ioer.exceptions.IoerException;

/**
 * The interface Content downloader.
 */
public interface IWeightDownloader {

    /**
     * Gets content.
     *
     * @return the content
     * @throws IoerException the ioer exception
     */
    Long getWeight() throws IoerException;
}
