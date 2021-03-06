package com.appscharles.libs.ioer.downloaders.file;

import com.appscharles.libs.ioer.downloaders.weight.HttpWeightDownloader;
import com.appscharles.libs.ioer.downloaders.weight.IWeightDownloader;
import com.appscharles.libs.ioer.exceptions.IoerException;
import com.appscharles.libs.ioer.models.StatusProgress;
import com.appscharles.libs.ioer.setters.StatusProgressSetter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * The type Http file downloader.
 */
public class HttpFileDownloader implements IFileDownloader, IStatusProgressable {

    private static final Logger logger = LogManager.getLogger(HttpFileDownloader.class);

    private Integer attempts = 3;

    private URL url;

    private StatusProgress statusProgress;

    private Long fileWeight;

    /**
     * Instantiates a new Http file downloader.
     */
    public HttpFileDownloader() {

    }

    /**
     * Instantiates a new Http file downloader.
     *
     * @param url      the url
     * @param attempts the attempts
     */
    public HttpFileDownloader(URL url, Integer attempts) {
        this.url = url;
        this.attempts = attempts;
        this.fileWeight = -1L;
    }

    @Override
    public void download(File toFile) throws IoerException {
        try {
            IWeightDownloader downloader = new HttpWeightDownloader(this.url);
            this.fileWeight =  downloader.getWeight();
        } catch (IOException e) {
            throw new IoerException(e);
        }
        downloadWithAttempts(toFile);
    }

    private void downloadWithAttempts(File toFile) throws IoerException {
        Exception exception = null;
        for (Integer i = 0; i < this.attempts; i++) {
            try {
                fileDownload(toFile);
                return;
            } catch (Exception e) {
                exception = e;
                logger.debug(e, e);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new IoerException(e);
            }
        }
        throw new IoerException("Can not download file: " + this.url.toString(), exception);
    }

    private void fileDownload(File toFile) throws IOException {
        if (toFile.getParentFile().exists() == false){
            toFile.getParentFile().mkdirs();
        }
        URLConnection urlConnection = this.url.openConnection();
        urlConnection.setReadTimeout(10000);
        urlConnection.setConnectTimeout(60000);
        try (BufferedInputStream in = new BufferedInputStream(urlConnection.getInputStream());
             FileOutputStream fos = new FileOutputStream(toFile)) {
            int size = 1024;
            byte dataBuffer[] = new byte[size];
            int bytesRead;
            long downloadBytes = 0;
            while ((bytesRead = in.read(dataBuffer, 0, size)) != -1) {
                downloadBytes += size;
                fos.write(dataBuffer, 0, bytesRead);
                StatusProgressSetter.set(this.statusProgress, toFile.getAbsolutePath(), this.fileWeight, downloadBytes);
            }
        }
    }

    @Override
    public void setStatusProgress(StatusProgress statusProgress) {
        this.statusProgress = statusProgress;
    }
}
