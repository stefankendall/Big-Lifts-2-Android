package com.stefankendall.BigLifts.data.stores.fto;

import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.data.models.JModel;
import com.stefankendall.BigLifts.data.models.fto.JFTOBoringButBig;
import com.stefankendall.BigLifts.data.stores.BLJStore;

import java.math.BigDecimal;
import java.util.List;

public class JFTOBoringButBigStore extends BLJStore {
    @Override
    public void setupDefaults() {
        JFTOBoringButBig bbbPercentage = (JFTOBoringButBig) this.create();
        bbbPercentage.percentage = new BigDecimal(50);
        bbbPercentage.threeMonthChallenge = false;
    }

    @Override
    public Class<? extends JModel> modelClass() {
        return JFTOBoringButBig.class;
    }

    @Override
    protected List<Class> getAssociations() {
        return Lists.newArrayList();
    }

    public static JFTOBoringButBigStore instance() {
        return (JFTOBoringButBigStore) BLJStore.instance(JFTOBoringButBigStore.class);
    }
}
