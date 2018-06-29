package com.appscharles.libs.ioer.services;

import java.io.*;

/**
 * The type File reader.
 */
public class FileReader {

    /**
     * Read string.
     *
     * @param file the file
     * @return the string
     * @throws IOException the io exception
     */
    public static String read(File file) throws IOException {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), "UTF-8"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
                if (line != null){
                    sb.append(System.lineSeparator());
                }
            }
            return sb.toString();
        }
    }
}
