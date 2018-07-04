package com.appscharles.libs.ioer.converters;

import com.appscharles.libs.ioer.models.RelativeFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * The type Relative file converter.
 */
public class RelativeFileConverter {

    /**
     * Convert list.
     *
     * @param rootPath the root path
     * @param files    the files
     * @return the list
     */
    public static List<RelativeFile> convert(File rootPath, List<File> files) {
        List<RelativeFile> relativeFiles = new ArrayList<>();
        for (File file : files) {
            String filePath = file.getPath();
            String relativePath = filePath.replaceFirst(Pattern.quote(rootPath.getPath()), "");
            RelativeFile relativeFile = new RelativeFile(rootPath.getPath(), relativePath);
            relativeFiles.add(relativeFile);
        }
        return relativeFiles;
    }
}
