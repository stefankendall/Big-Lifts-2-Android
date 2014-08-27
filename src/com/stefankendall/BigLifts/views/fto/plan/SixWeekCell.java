package com.stefankendall.BigLifts.views.fto.plan;

import com.stefankendall.BigLifts.data.models.fto.JFTOSettings;
import com.stefankendall.BigLifts.data.stores.fto.JFTOSettingsStore;
import com.stefankendall.BigLifts.data.stores.fto.JFTOWorkoutStore;
import com.stefankendall.BigLifts.views.cells.SwitchCell;

public class SixWeekCell extends SwitchCell {
    @Override
    protected String label() {
        return "Six-week plan?";
    }

    @Override
    protected boolean defaultState() {
        JFTOSettings jftoSettings = (JFTOSettings) JFTOSettingsStore.instance().first();
        return jftoSettings.sixWeekEnabled;
    }

    @Override
    protected void valueChanged(boolean state) {
        JFTOSettings jftoSettings = (JFTOSettings) JFTOSettingsStore.instance().first();
        jftoSettings.sixWeekEnabled = state;
        JFTOWorkoutStore.instance().switchTemplate();
    }
}
