package com.stefankendall.BigLiftsPro.views.fto.settings;

import com.google.common.collect.Lists;
import com.stefankendall.BigLiftsPro.data.models.JSettings;
import com.stefankendall.BigLiftsPro.data.stores.JSettingsStore;

import java.util.List;

public class LbsKgSwitchCell extends RadioButtonCell {
    static List<String> units = Lists.newArrayList("lbs", "kg");

    public LbsKgSwitchCell() {
        super(units);
    }

    @Override
    protected void valueChanged(int selection) {
        JSettings settings = (JSettings) JSettingsStore.instance().first();
        settings.setUnits(units.get(selection));
    }

    @Override
    protected int defaultSelectedIndex() {
        JSettings settings = (JSettings) JSettingsStore.instance().first();
        return units.indexOf(settings.units);
    }
}
