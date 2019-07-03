package ru.inno.stc14.dao.jdbc.singleton.Decorator;

import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

/**
 *
 * декоратор, перекодировка в UTF-8 значения по ключу
 *
 */
public class UTFResourceBundle extends Decorator {
    public UTFResourceBundle(ResourceBundle resource) {
        super(resource);
    }
    /**
     *
     * получить строку по ключу и перекодировать
     * @param key ключ
     */
    @Override
    public String getResourceBundleString(String key) {
        return new String(resource.getString(key).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

    }
}
