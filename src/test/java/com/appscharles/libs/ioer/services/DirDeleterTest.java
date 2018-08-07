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
 * Date: 10.07.2018
 * Time: 12:03
 * Project name: ioer
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class DirDeleterTest {

    @Rule
    public TemporaryFolder temp = new TemporaryFolder();

    @Test
    public void shouldDeleteDirectory() throws IOException, GeneratorException {
        File dir = this.temp.newFolder("dir");
        IGenerator generator = new TreeFilesGenerator(dir.getPath(), 12, 12, 3, 3);
        generator.random();
        DirDeleter.delete(dir);
        Assert.assertFalse(dir.exists());
    }
}