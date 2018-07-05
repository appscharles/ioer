package com.appscharles.libs.ioer.validators;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * The type Dir contains dir validator test.
 */
public class DirContainsDirValidatorTest {

    /**
     * The Temp.
     */
    @Rule
    public TemporaryFolder temp = new TemporaryFolder();


    /**
     * Should dit contains file.
     *
     * @throws IOException the io exception
     */
    @Test
    public void shouldDitContainsFile() throws IOException {
        File inDir = this.temp.newFolder("folder1");
        File dir1 = Files.createDirectories(new File(inDir, "dir1").toPath()).toFile();
        File dir2 = Files.createDirectories(new File(inDir, "sub_folder/dir2").toPath()).toFile();
        File dir3 = Files.createDirectories(new File(this.temp.newFolder("folder2"), "dir3").toPath()).toFile();
        Assert.assertTrue(DirContainsDirValidator.contains(dir1, inDir));
        Assert.assertTrue(DirContainsDirValidator.contains(dir2, inDir));
        Assert.assertFalse(DirContainsDirValidator.contains(dir3, inDir));
    }

}