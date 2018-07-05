package com.appscharles.libs.ioer.validators;

import com.appscharles.libs.ioer.services.FileWriter;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

/**
 * The type Dir contains file validator test.
 */
public class DirContainsFileValidatorTest {

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
        File fileInDir1 = new File(inDir, "file1.txt");
        File fileInDir2 = new File(inDir, "sub_folder/file2.txt");
        File fileInDir3 = new File(this.temp.newFolder("folder2"), "sub_folder/file2.txt");
        FileWriter.write(fileInDir1, "content");
        FileWriter.write(fileInDir1, "fileInDir2");
        FileWriter.write(fileInDir1, "fileInDir3");
        Assert.assertTrue(DirContainsFileValidator.contains(fileInDir1, inDir));
        Assert.assertTrue(DirContainsFileValidator.contains(fileInDir2, inDir));
        Assert.assertFalse(DirContainsFileValidator.contains(fileInDir3, inDir));
    }

}