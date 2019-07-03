package ru.inno.stc14.service.util;


import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

/**
 *
 * перекодировка ResourceBundle.getString в UTF-8
 *
 */
public  class ResourceUtility {
    /**
     *
     * перекодировка ResourceBundle.getString в UTF-8
     *
     */
    static public String resourceGetString (ResourceBundle resource,String key) {
        return new String(resource.getString(key).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
    }
}
