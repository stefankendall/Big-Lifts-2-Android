package com.stefankendall.BigLifts.views.fto.onerep;

import com.stefankendall.BigLifts.data.models.JSettings;
import com.stefankendall.BigLifts.data.numbers.BigDecimals;
import com.stefankendall.BigLifts.data.stores.JSettingsStore;
import com.stefankendall.BigLifts.views.cells.ParameterizedDecimalInputCell;

public class BodyweightCell extends ParameterizedDecimalInputCell {
    public BodyweightCell(String label, String defaultValue) {
        super(label, defaultValue);
    }

    @Override
    protected String defaultValue() {
        JSettings settings = (JSettings) JSettingsStore.instance().first();
        return BigDecimals.print(settings.bodyweight);
    }

    @Override
    protected void stringValueChanged(String s) {
        JSettings settings = (JSettings) JSettingsStore.instance().first();
        settings.bodyweight = BigDecimals.parse(s);
    }

    @Override
    protected String hint() {
        return "170";
    }
}
