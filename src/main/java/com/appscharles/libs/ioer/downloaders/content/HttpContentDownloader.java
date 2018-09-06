package com.appscharles.libs.ioer.downloaders.content;

import com.appscharles.libs.ioer.exceptions.IoerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * The type Http content downloader.
 */
public class HttpContentDownloader implements IContentDownloader {

    private static final Logger logger = LogManager.getLogger(HttpContentDownloader.class);

    private URL url;

    private Integer attempts = 1;

    /**
     * Instantiates a new Http content downloader.
     *
     * @param url the url
     * @throws IOException the io exception
     */
    public HttpContentDownloader(URL url) throws IOException {
        this.url = url;
    }

    @Override
    public String getContent() throws IoerException {
        return getContentWithAttempts();
    }

    private String getContentWithAttempts() throws IoerException {
        IoerException exception = null;
        for (Integer i = 0; i < this.attempts; i++) {
            try {
                return content();
            } catch (IoerException e) {
                exception = e;
                logger.debug(e, e);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new IoerException(e);
            }
        }
        throw exception;
    }


    private String content() throws IoerException {
        StringBuilder sB = new StringBuilder();
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) this.url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode >= 200 && responseCode <  400) {
                try(InputStream is = httpURLConnection.getInputStream();
                    BufferedReader bR = new BufferedReader(new InputStreamReader(is, "UTF-8"))){
                    int BUFFER_SIZE=1024;
                    char[] buffer = new char[BUFFER_SIZE];
                    int charsRead = 0;
                    while ( (charsRead  = bR.read(buffer, 0, BUFFER_SIZE)) != -1) {
                        sB.append(buffer, 0, charsRead);
                    }
                }
            } else {
                try(InputStream is = httpURLConnection.getErrorStream();
                    BufferedReader bR = new BufferedReader(new InputStreamReader(is, "UTF-8"))){
                    int BUFFER_SIZE=1024;
                    char[] buffer = new char[BUFFER_SIZE];
                    int charsRead = 0;
                    while ( (charsRead  = bR.read(buffer, 0, BUFFER_SIZE)) != -1) {
                        sB.append(buffer, 0, charsRead);
                    }
                }
                throw new IoerException(httpURLConnection.getResponseCode() + " " + httpURLConnection.getResponseMessage() + ">>>RESPONSE_HTML>>>" + sB.toString());
            }
        } catch (IOException e) {
           throw new IoerException(e);
        }
        return sB.toString();
    }

    /**
     * Sets attempts.
     *
     * @param attempts the attempts
     * @return the attempts
     */
    public HttpContentDownloader setAttempts(Integer attempts) {
        this.attempts = attempts;
        return this;
    }
}
