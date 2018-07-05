package com.appscharles.libs.ioer.checkers;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 05.07.2018
 * Time: 16:39
 * Project name: ioer
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class InDirPermissionCheckerTest {

    @Rule
    public TemporaryFolder temp = new TemporaryFolder();

    @Test
    public void shouldCheckPermissionInDir() throws IOException {
        File inDir = this.temp.newFolder("inDir");
        File subDir = Files.createDirectories(new File(inDir, "subDir").toPath()).toFile();
        InDirPermissionChecker.check(inDir, new ArrayList<>(Arrays.asList(subDir)));
    }
}