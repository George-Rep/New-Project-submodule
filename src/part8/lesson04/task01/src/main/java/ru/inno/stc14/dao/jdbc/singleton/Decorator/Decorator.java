package ru.inno.stc14.dao.jdbc.singleton.Decorator;



import java.util.ResourceBundle;
/**
 *
 * декоратор для ResourceBundle
 *
 */
public class Decorator implements ResourceBundleInterface {
    protected ResourceBundle resource;

    Decorator(ResourceBundle resource) {
        this.resource = resource;
    }
    /**
     *
     * получить строку по ключу и перекодировать
     * @param key ключ
     */
    @Override
    public String getResourceBundleString(String key) {
        return resource.getString(key);
    }

}
