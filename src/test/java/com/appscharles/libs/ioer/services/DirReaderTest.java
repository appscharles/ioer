package com.appscharles.libs.ioer.services;

import com.appscharles.libs.generator.exceptions.GeneratorException;
import com.appscharles.libs.generator.services.IGenerator;
import com.appscharles.libs.generator.services.TreeFilesGenerator;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 06.07.2018
 * Time: 15:13
 * Project name: ioer
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class DirReaderTest {

    @Rule
    public TemporaryFolder temp = new TemporaryFolder();


    @Test
    public void shouldGetFilesWithoutRootDirectory() throws IOException, GeneratorException {
        File dir = this.temp.newFolder("dir");
        IGenerator generator = new TreeFilesGenerator(dir.getPath(), 12, 12, 3, 3);
        generator.random();
        Assert.assertEquals(DirReader.getFiles(dir).size(), DirReader.getFilesWithoutRoot(dir).size() + 1);
    }
}