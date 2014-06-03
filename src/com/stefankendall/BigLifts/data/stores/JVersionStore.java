package com.stefankendall.BigLifts.data.stores;

import com.stefankendall.BigLifts.data.models.JModel;
import com.stefankendall.BigLifts.data.models.JVersion;

public class JVersionStore extends BLJStore {
    @Override
    public Class<? extends JModel> modelClass() {
        return JVersion.class;
    }

    public static JVersionStore instance() {
        return (JVersionStore) BLJStore.instance(JVersionStore.class);
    }

    @Override
    public void setupDefaults() {
        JVersion version = (JVersion) this.create();
        version.version = 14;
    }
}
