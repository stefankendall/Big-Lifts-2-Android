package com.stefankendall.BigLifts.data.stores.fto;

import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.data.models.JModel;
import com.stefankendall.BigLifts.data.models.fto.JFTOBoringButBigLift;
import com.stefankendall.BigLifts.data.stores.BLJStore;

import java.util.List;

public class JFTOBoringButBigLiftStore extends BLJStore {
    @Override
    public Class<? extends JModel> modelClass() {
        return JFTOBoringButBigLift.class;
    }

    @Override
    protected List<Class> getAssociations() {
        return Lists.newArrayList();
    }

    public static JFTOBoringButBigLiftStore instance() {
        return (JFTOBoringButBigLiftStore) BLJStore.instance(JFTOBoringButBigLiftStore.class);
    }
}
