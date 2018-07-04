package com.appscharles.libs.ioer.models;

import java.io.File;

/**
 * The type Relative file.
 */
public class RelativeFile extends File implements IRelativeable {


    private String relativePath;

    /**
     * Instantiates a new Relative file.
     *
     * @param rootPath     the root path
     * @param relativePath the relative path
     */
    public RelativeFile(String rootPath, String relativePath) {
        super(rootPath, relativePath);
        this.relativePath = relativePath;
    }

    @Override
    public String getRelativePath() {
        return this.relativePath;
    }

}
