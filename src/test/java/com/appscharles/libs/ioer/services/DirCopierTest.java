package com.appscharles.libs.ioer.services;

import com.appscharles.libs.generator.exceptions.GeneratorException;
import com.appscharles.libs.generator.services.IGenerator;
import com.appscharles.libs.generator.services.TreeFilesGenerator;
import com.appscharles.libs.ioer.models.StatusProgress;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.StandardCopyOption;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 06.07.2018
 * Time: 12:09
 * Project name: ioer
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class DirCopierTest {

    /**
     * The Temp.
     */
    @Rule
    public TemporaryFolder temp = new TemporaryFolder();

    @Test
    public void shouldCopyAllFiles() throws IOException, GeneratorException {
        File fromDir = this.temp.newFolder("from");
        File toDir = this.temp.newFolder("to");
        IGenerator generator = new TreeFilesGenerator(fromDir.getPath(), 12, 12, 3, 3);
        generator.random();
        DirCopier.copy(fromDir, toDir, StandardCopyOption.REPLACE_EXISTING);
        Assert.assertEquals(DirReader.getFiles(fromDir).size(), DirReader.getFiles(toDir).size());
    }

    @Test
    public void shouldCopyAllFilesWithStatusProgress() throws IOException, GeneratorException {
        File fromDir = this.temp.newFolder("from");
        File toDir = this.temp.newFolder("to");
        IGenerator generator = new TreeFilesGenerator(fromDir.getPath(), 12, 12, 3, 3);
        generator.random();
        StatusProgress statusProgress = new StatusProgress();
        DirCopier.copy(fromDir, toDir, StandardCopyOption.REPLACE_EXISTING, statusProgress);
        Assert.assertEquals(DirReader.getFiles(fromDir).size(), DirReader.getFiles(toDir).size());
        Assert.assertTrue("Status progress: " + statusProgress.getProgress(),statusProgress.getProgress().equals(1.0));
        Assert.assertTrue(statusProgress.getStatus().contains(File.separator));
    }
}