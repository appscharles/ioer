package com.appscharles.libs.ioer.downloaders.content;

import com.appscharles.libs.ioer.exceptions.IoerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
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

    private Integer attempts;

    private long delayAttempt;

    /**
     * Instantiates a new Http content downloader.
     *
     * @param url the url
     */
    public HttpContentDownloader(URL url) {
        this.url = url;
        this.attempts = 1;
        this.delayAttempt = 5000;
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
                Thread.sleep(this.delayAttempt);
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
            httpURLConnection.setReadTimeout(60000);
            httpURLConnection.setConnectTimeout(60000);
            httpURLConnection.setRequestMethod("GET");
            final int responseCode = httpURLConnection.getResponseCode();
            final String responseMessage = httpURLConnection.getResponseMessage();
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
                throw new IoerException(responseCode + " " + responseMessage + ">>>RESPONSE_HTML>>>" + sB.toString());
            }
        } catch (Exception e) {
           throw new IoerException("Failed get content from url: " + this.url.toString(), e);
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

    /**
     * Sets delay attempt.
     *
     * @param delayAttempt the delay attempt
     * @return the delay attempt
     */
    public HttpContentDownloader setDelayAttempt(long delayAttempt) {
        this.delayAttempt = delayAttempt;
        return this;
    }
}
