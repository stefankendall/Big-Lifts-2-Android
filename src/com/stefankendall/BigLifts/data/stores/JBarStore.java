package com.stefankendall.BigLifts.data.stores;

import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.data.models.JBar;
import com.stefankendall.BigLifts.data.models.JModel;
import com.stefankendall.BigLifts.data.models.JSettings;

import java.math.BigDecimal;
import java.util.List;

public class JBarStore extends BLJStore {
    @Override
    public Class<? extends JModel> modelClass() {
        return JBar.class;
    }

    public static JBarStore instance() {
        return (JBarStore) BLJStore.instance(JBarStore.class);
    }

    @Override
    public void setupDefaults() {
        JBar bar = (JBar) this.create();
        bar.weight = new BigDecimal("45");
    }

    @Override
    protected List<Class> getAssociations() {
        return Lists.newArrayList();
    }

    public void adjustForKg() {
        JSettings settings = (JSettings) JSettingsStore.instance().first();
        JBar bar = (JBar) this.first();
        if (settings.units.equals("kg")) {
            bar.weight = new BigDecimal("20");
        } else {
            bar.weight = new BigDecimal("45");
        }
    }
}
