package com.stefankendall.BigLiftsPro.views.fto.plan;

import com.stefankendall.BigLiftsPro.data.models.fto.JFTOSettings;
import com.stefankendall.BigLiftsPro.data.stores.fto.JFTOSettingsStore;
import com.stefankendall.BigLiftsPro.views.cells.ParameterizedDecimalInputCell;

public class TrainingMaxCell extends ParameterizedDecimalInputCell {
    public TrainingMaxCell() {
        super("Training Max %", "");
    }

    @Override
    protected String defaultValue() {
        JFTOSettings settings = (JFTOSettings) JFTOSettingsStore.instance().first();
        return settings.trainingMax.toString();
    }

    @Override
    protected void stringValueChanged(String value) {
        JFTOSettings settings = (JFTOSettings) JFTOSettingsStore.instance().first();
        settings.trainingMax = this.getValue();
    }
}
