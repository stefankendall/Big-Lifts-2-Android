package com.stefankendall.BigLiftsPro.views.fto.plan;

import com.stefankendall.BigLiftsPro.data.models.fto.JFTOSettings;
import com.stefankendall.BigLiftsPro.data.stores.fto.JFTOSettingsStore;
import com.stefankendall.BigLiftsPro.data.stores.fto.JFTOWorkoutStore;
import com.stefankendall.BigLiftsPro.views.cells.SwitchCell;

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
