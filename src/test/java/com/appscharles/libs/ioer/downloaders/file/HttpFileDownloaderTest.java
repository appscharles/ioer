package com.appscharles.libs.ioer.downloaders.file;

import com.appscharles.libs.ioer.TestCase;
import com.appscharles.libs.ioer.exceptions.IoerException;
import com.appscharles.libs.ioer.models.StatusProgress;
import com.appscharles.libs.ioer.services.FileReader;
import com.appscharles.libs.ioer.services.FileWriter;
import com.appscharles.libs.logger.configurators.Log4j2Console;
import com.appscharles.libs.logger.configurators.Log4jConsole;
import com.appscharles.libs.logger.services.LoggerConfigurator;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.apache.logging.log4j.Level;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 07.08.2018
 * Time: 12:44
 * Project name: ioer
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class HttpFileDownloaderTest extends TestCase {

    /**
     * Sets logger.
     */
    @Before
    public void setLogger() {
        LoggerConfigurator.config(new Log4j2Console(Level.DEBUG));
        LoggerConfigurator.config(new Log4jConsole(org.apache.log4j.Level.WARN));
    }


    @Test
    public void shouldDownloadFile() throws IOException, IoerException {
        LoggerConfigurator.config(new Log4j2Console(Level.DEBUG));
        File file = this.temp.newFile("file");
        StatusProgress statusProgress = new StatusProgress();
        IFileDownloader downloader = new HttpFileDownloader(new URL("https://github.com/appscharles/libs_browser/releases/download/Chrome.portable.68.0/chrome.zip"), 3);
       ((IStatusProgressable) downloader).setStatusProgress(statusProgress);
        statusProgress.progressProperty().addListener((args, oldVal, newVal) ->{
            System.out.println(newVal);
        });
       // downloader.download(file);
        System.out.println("OK");
    }

    @Test
    public void shouldDownloadFile2() throws IOException, IoerException {
        File httpDirectory = this.temp.newFolder();
        File httpFile = new File(httpDirectory, "__files/content.txt");
        String content = "ąćśśśśśśśśććććććć";
        FileWriter.write(httpFile, content);
        File downloadedFile = this.temp.newFile();
        WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort()
                .usingFilesUnderDirectory(httpDirectory.getPath()));
        wireMockRule.stubFor(get(urlEqualTo("/content.txt"))
                .willReturn(aResponse()
                        .withBodyFile("content.txt")));
        wireMockRule.start();
        new HttpFileDownloader(new URL("http://localhost:"+wireMockRule.port() + "/content.txt"),3).download( downloadedFile);
        String downloadedContent = FileReader.read(downloadedFile);
        Assert.assertEquals(content, downloadedContent);
        wireMockRule.stop();
    }
}