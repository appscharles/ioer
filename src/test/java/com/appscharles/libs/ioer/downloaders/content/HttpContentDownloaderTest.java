package com.appscharles.libs.ioer.downloaders.content;

import com.appscharles.libs.ioer.exceptions.IoerException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 06.09.2018
 * Time: 08:11
 * Project name: ioer
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class HttpContentDownloaderTest {

    @Test(expected = IoerException.class)
    public void shouldGetContent() throws IOException, IoerException {
        URL url = new URL("http://hurtowniagalanter.pl/index.php?controller=product&id_product=235#/");
        String html = new HttpContentDownloader(url).setAttempts(3).getContent();
        Assert.assertTrue(html.contains("</body>"));
    }
}