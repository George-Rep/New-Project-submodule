package ru.inno.stc14.dao.jdbc.singleton;

import ru.inno.stc14.dao.ResBundleInstance;

import java.util.ResourceBundle;
/**
 * singleton для ResourceBundleInterface
 * (для декораторов ResourceBundle)
 *
 *
 */
public class ResourceBundleInstance implements ResBundleInstance {
    private static volatile ResourceBundleInstance instance;
    private ResourceBundle resource;

    private ResourceBundleInstance() {
        resource = ResourceBundle.getBundle("demo");
    }
    /**
     * метод получения экземпляра синглтона
     *
     */
    public static ResourceBundleInstance getInstance() {
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
    public ResourceBundle getResource() {
        return resource;
    }

}
