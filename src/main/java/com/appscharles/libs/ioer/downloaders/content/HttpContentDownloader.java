package com.appscharles.libs.ioer.downloaders.content;

import com.appscharles.libs.ioer.exceptions.IoerException;

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

    private URL url;

    private HttpURLConnection httpURLConnection;

    /**
     * Instantiates a new Http content downloader.
     *
     * @param url the url
     * @throws IOException the io exception
     */
    public HttpContentDownloader(URL url) throws IOException {
        this.url = url;
        this.httpURLConnection = (HttpURLConnection) this.url.openConnection();
        this.httpURLConnection.setRequestMethod("GET");
    }

    @Override
    public String getContent() throws IoerException {
        StringBuilder sB = new StringBuilder();
        try {
            int responseCode = this.httpURLConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try(InputStream is = this.httpURLConnection.getInputStream();
                        BufferedReader bR = new BufferedReader(new InputStreamReader(is))){
                    String line;
                    while ((line = bR.readLine()) != null) {
                        sB.append(line);
                    }
                }
            } else {
                throw new IoerException("Could not get content from url: "+ this.url.toString());
            }
        } catch (IOException e) {
           throw new IoerException(e);
        }
        return sB.toString();
    }

    /**
     * Gets http url connection.
     *
     * @return the http url connection
     */
    public HttpURLConnection getHttpURLConnection() {
        return httpURLConnection;
    }
}
