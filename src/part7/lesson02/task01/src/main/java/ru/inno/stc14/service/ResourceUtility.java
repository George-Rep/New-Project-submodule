package ru.inno.stc14.service;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

public  class ResourceUtility {
    static public String resourceGetString (ResourceBundle resource,String key) throws UnsupportedEncodingException {
        return new String(resource.getString(key).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
    }
}
