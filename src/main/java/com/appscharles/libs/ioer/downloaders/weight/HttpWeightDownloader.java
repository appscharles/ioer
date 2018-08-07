package com.appscharles.libs.ioer.downloaders.weight;

import com.appscharles.libs.ioer.exceptions.IoerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 07.08.2018
 * Time: 12:33
 * Project name: ioer
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class HttpWeightDownloader implements IWeightDownloader {

    private static final Logger logger = LogManager.getLogger(HttpWeightDownloader.class);

    private URL url;

    private HttpURLConnection httpURLConnection;

    /**
     * Instantiates a new Http content downloader.
     *
     * @param url the url
     * @throws IOException the io exception
     */
    public HttpWeightDownloader(URL url) throws IOException {
        this.url = url;
        this.httpURLConnection = (HttpURLConnection) this.url.openConnection();
        this.httpURLConnection.setRequestMethod("GET");
    }

    @Override
    public Long getWeight() throws IoerException {
        try {
            this.httpURLConnection.getInputStream();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            logger.debug(e, e);
            return -1L;
        }
        return new Long(this.httpURLConnection.getContentLengthLong());
    }
}
