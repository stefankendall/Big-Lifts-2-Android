package com.stefankendall.BigLiftsPro.data;

import com.stefankendall.BigLiftsPro.data.stores.BLJStoreManager;
import com.stefankendall.BigLiftsPro.data.stores.migrations.Migrator;

public class DataLoader {
    public static boolean loaded = false;
    public static void load() {
        new Migrator().migrateStores();
        BLJStoreManager.instance().loadStores();
        loaded = true;
    }
}
