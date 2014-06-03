package com.stefankendall.BigLifts.data.stores;

import com.stefankendall.BigLifts.data.models.JBar;
import com.stefankendall.BigLifts.data.models.JModel;
import com.stefankendall.BigLifts.data.models.JSettings;

import java.math.BigDecimal;

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

    public void adjustForKg() {
        JSettings settings = (JSettings) JSettingsStore.instance().first();
        if (settings.units.equals("kg")) {
            JBar bar = (JBar) this.first();
            bar.weight = new BigDecimal("20");
        }
    }
}
