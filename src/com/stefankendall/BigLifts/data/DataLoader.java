package com.stefankendall.BigLifts.data;

import com.stefankendall.BigLifts.data.stores.BLJStoreManager;
import com.stefankendall.BigLifts.data.stores.migrations.Migrator;

public class DataLoader {
    public void load() {
        new Migrator().migrateStores();
        BLJStoreManager.instance().loadStores();
    }
}
