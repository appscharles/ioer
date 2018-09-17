package com.appscharles.libs.ioer.converters;

/**
 * The type Iso 88591 to utf 8 converter.
 */
public class Iso88591ToUtf8Converter {

    /**
     * Convert string.
     *
     * @param content the content
     * @return the string
     */
    public static String convert(String content){
        content = content.replace("\\u0104", "Ą");
        content = content.replace("\\u0106", "Ć");
        content = content.replace("\\u0118", "Ę");
        content = content.replace("\\u0141", "Ł");
        content = content.replace("\\u0143", "Ń");
        content = content.replace("\\u00d3", "Ó");
        content = content.replace("\\u015a", "Ś");
        content = content.replace("\\u0179", "Ź");
        content = content.replace("\\u017b", "Ż");
        content = content.replace("\\u0105", "ą");
        content = content.replace("\\u0107", "ć");
        content = content.replace("\\u0119", "ę");
        content = content.replace("\\u0142", "ł");
        content = content.replace("\\u0144", "ń");
        content = content.replace("\\u00f3", "ó");
        content = content.replace("\\u015b", "ś");
        content = content.replace("\\u017a", "ź");
        content = content.replace("\\u017c", "ż");
        return content;
    }
}
