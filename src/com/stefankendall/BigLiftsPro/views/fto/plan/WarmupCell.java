package com.stefankendall.BigLiftsPro.views.fto.plan;

import com.stefankendall.BigLiftsPro.data.models.fto.JFTOSettings;
import com.stefankendall.BigLiftsPro.data.stores.fto.JFTOSettingsStore;
import com.stefankendall.BigLiftsPro.data.stores.fto.JFTOWorkoutStore;
import com.stefankendall.BigLiftsPro.views.cells.SwitchCell;

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
