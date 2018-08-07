package com.appscharles.libs.ioer.downloaders.file;

import com.appscharles.libs.ioer.TestCase;
import com.appscharles.libs.ioer.exceptions.IoerException;
import com.appscharles.libs.ioer.models.StatusProgress;
import com.appscharles.libs.logger.configurators.Log4j2Console;
import com.appscharles.libs.logger.services.LoggerConfigurator;
import org.apache.logging.log4j.Level;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

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
}