package com.stefankendall.BigLifts.data.stores.fto;

import com.stefankendall.BigLifts.data.models.fto.JFTOLift;
import com.stefankendall.BigLifts.data.stores.BLJStore;
import com.stefankendall.BigLifts.data.stores.JLiftStore;

public class JFTOLiftStore extends JLiftStore {
    public boolean isSettingDefaults;

    public static BLJStore instance() {
        return BLJStore.instance(JFTOLiftStore.class);
    }

    @Override
    public Class modelClass() {
        return JFTOLift.class;
    }

    public void adjustForKg() {

    }
}
