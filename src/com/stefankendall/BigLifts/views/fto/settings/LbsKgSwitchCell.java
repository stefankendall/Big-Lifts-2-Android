package com.stefankendall.BigLifts.views.fto.settings;

import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.data.models.JSettings;
import com.stefankendall.BigLifts.data.stores.JSettingsStore;

import java.util.List;

public class LbsKgSwitchCell extends RadioButtonCell {
    static List<String> units = Lists.newArrayList("lbs", "kg");

    public LbsKgSwitchCell() {
        super(units);
    }

    @Override
    protected void valueChanged(int selection) {
        JSettings settings = (JSettings) JSettingsStore.instance().first();
        settings.units = units.get(selection);
    }

    @Override
    protected int defaultSelectedIndex() {
        JSettings settings = (JSettings) JSettingsStore.instance().first();
        return units.indexOf(settings.units);
    }
}
