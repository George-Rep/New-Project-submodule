package ru.inno.stc14.dao.jdbc.singleton.Decorator;
/**
 *
 * ResourceBundleInterface
 *
 */
public interface ResourceBundleInterface {
    /**
     *
     * получить значение по ключу
     * @param key ключ
     */
    String getResourceBundleString(String key);
}
