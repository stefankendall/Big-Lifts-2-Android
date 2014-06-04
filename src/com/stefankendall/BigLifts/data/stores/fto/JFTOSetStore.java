package com.stefankendall.BigLifts.data.stores.fto;

import com.stefankendall.BigLifts.data.models.JModel;
import com.stefankendall.BigLifts.data.models.fto.JFTOSet;
import com.stefankendall.BigLifts.data.stores.BLJStore;
import com.stefankendall.BigLifts.data.stores.JSetStore;

import java.math.BigDecimal;

public class JFTOSetStore extends JSetStore {
    @Override
    public Class<? extends JModel> modelClass() {
        return JFTOSet.class;
    }

    public static JFTOSetStore instance() {
        return (JFTOSetStore) BLJStore.instance(JFTOSetStore.class);
    }

    @Override
    public void setDefaultsForObject(JModel object) {
        JFTOSet set = (JFTOSet) object;
        set.percentage = BigDecimal.ZERO;
        set.reps = 0;
    }
}
