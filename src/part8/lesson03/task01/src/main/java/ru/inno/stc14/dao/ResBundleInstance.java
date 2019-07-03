package ru.inno.stc14.dao;

import ru.inno.stc14.dao.jdbc.singleton.Decorator.ResourceBundleInterface;


/**
 *
 * ResBundleInstance
 *
 */
public interface ResBundleInstance {

    ResourceBundleInterface getResource();
}
