package com.stefankendall.BigLifts.views.fto.settings;

import com.stefankendall.BigLifts.data.models.JSettings;
import com.stefankendall.BigLifts.data.stores.JSettingsStore;
import com.stefankendall.BigLifts.views.cells.SwitchCell;

public class KeepScreenOnCell extends SwitchCell {
    @Override
    protected String label() {
        return "Keep screen on?";
    }

    @Override
    protected boolean defaultState() {
        JSettings settings = (JSettings) JSettingsStore.instance().first();
        return settings.screenAlwaysOn;
    }

    @Override
    protected void valueChanged(boolean state) {
        JSettings settings = (JSettings) JSettingsStore.instance().first();
        settings.screenAlwaysOn = state;
    }
}
