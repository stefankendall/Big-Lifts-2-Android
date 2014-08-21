package com.stefankendall.BigLifts.data.stores.fto;

import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.data.models.JModel;
import com.stefankendall.BigLifts.data.models.fto.JFTOVariant;
import com.stefankendall.BigLifts.data.stores.BLJStore;

import java.util.List;

public class JFTOVariantStore extends BLJStore {
    @Override
    public Class<? extends JModel> modelClass() {
        return JFTOVariant.class;
    }

    public static JFTOVariantStore instance() {
        return (JFTOVariantStore) BLJStore.instance(JFTOVariantStore.class);
    }

    @Override
    public void setupDefaults() {
        JFTOVariant variant = (JFTOVariant) this.create();
        variant.name = JFTOVariant.STANDARD;
    }

    @Override
    protected List<Class> getAssociations() {
        return Lists.newArrayList();
    }

    public void changeTo(String variant) {
        JFTOVariant ftoVariant = (JFTOVariant) this.first();
        ftoVariant.name = variant;
        JFTOWorkoutStore.instance().switchTemplate();
    }
}
