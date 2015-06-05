package com.stefankendall.BigLiftsPro.data.stores.migrations;

import com.stefankendall.BigLiftsPro.data.stores.JVersionStore;

public class Migrator {
    public void migrateStores(){
        JVersionStore.instance().load();
    }
}
