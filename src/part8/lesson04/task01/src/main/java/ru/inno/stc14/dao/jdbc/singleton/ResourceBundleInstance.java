package ru.inno.stc14.dao.jdbc.singleton;

import ru.inno.stc14.dao.ResBundleInstance;
import ru.inno.stc14.dao.jdbc.singleton.Decorator.ResourceBundleInterface;
import ru.inno.stc14.dao.jdbc.singleton.Decorator.UTFResourceBundle;

import java.util.ResourceBundle;
/**
 * singleton для ResourceBundleInterface
 * (для декораторов ResourceBundle)
 *
 *
 */
public class ResourceBundleInstance implements ResBundleInstance {
    private static volatile ResourceBundleInstance instance;
    private ResourceBundleInterface resource;

    private ResourceBundleInstance() {
        resource = new UTFResourceBundle(ResourceBundle.getBundle("demo"));
    }
    /**
     * метод получения экземпляра синглтона
     *
     */
    public static ResBundleInstance getInstance() {
        ResourceBundleInstance localInstance = instance;
        if (localInstance == null) {
            synchronized (ResourceBundleInstance.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ResourceBundleInstance();
                }
            }
        }
        return localInstance;
    }

    @Override
    public ResourceBundleInterface getResource() {
        return resource;
    }


}
