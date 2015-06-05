package com.stefankendall.BigLiftsPro.views.fto.settings;

import com.stefankendall.BigLiftsPro.data.models.JSettings;
import com.stefankendall.BigLiftsPro.data.stores.JSettingsStore;
import com.stefankendall.BigLiftsPro.views.cells.SwitchCell;

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
