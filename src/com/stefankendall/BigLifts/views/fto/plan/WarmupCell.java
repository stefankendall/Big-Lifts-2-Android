package com.stefankendall.BigLifts.views.fto.plan;

import com.stefankendall.BigLifts.data.models.fto.JFTOSettings;
import com.stefankendall.BigLifts.data.stores.fto.JFTOSettingsStore;
import com.stefankendall.BigLifts.data.stores.fto.JFTOWorkoutStore;
import com.stefankendall.BigLifts.views.cells.SwitchCell;

public class WarmupCell extends SwitchCell {
    @Override
    protected String label() {
        return "Warm-up?";
    }

    @Override
    protected boolean defaultState() {
        JFTOSettings jftoSettings = (JFTOSettings) JFTOSettingsStore.instance().first();
        return jftoSettings.warmupEnabled;
    }

    @Override
    protected void valueChanged(boolean state) {
        JFTOSettings jftoSettings = (JFTOSettings) JFTOSettingsStore.instance().first();
        jftoSettings.warmupEnabled = state;
        JFTOWorkoutStore.instance().switchTemplate();
    }
}
