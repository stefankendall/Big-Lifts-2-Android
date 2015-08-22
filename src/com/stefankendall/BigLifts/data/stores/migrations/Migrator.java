package com.stefankendall.BigLifts.data.stores.migrations;

import com.stefankendall.BigLifts.data.stores.JVersionStore;

public class Migrator {
    public void migrateStores(){
        JVersionStore.instance().load();
    }
}
