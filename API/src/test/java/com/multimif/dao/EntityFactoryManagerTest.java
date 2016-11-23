package com.multimif.dao;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 * @author p1317074
 * @version 1.0
 * @since 1.0 20/10/16.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EntityFactoryManagerTest {
    public void createEntityManager() {
        EntityFactoryManager.persistance();
    }

    public void removeEntityManager(){
        EntityFactoryManager.close();
    }

}