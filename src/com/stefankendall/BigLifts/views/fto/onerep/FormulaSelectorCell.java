package com.stefankendall.BigLifts.views.fto.onerep;

import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.data.models.JSettings;
import com.stefankendall.BigLifts.data.stores.JSettingsStore;
import com.stefankendall.BigLifts.views.cells.SpinnerCell;
import com.stefankendall.BigLifts.views.fto.barloading.FieldWatcher;

import java.util.List;

public class FormulaSelectorCell extends SpinnerCell {
    public FormulaSelectorCell(FieldWatcher fieldWatcher) {
        super(fieldWatcher);
    }

    @Override
    protected List<String> options() {
        return Lists.newArrayList(JSettings.ROUNDING_FORMULA_EPLEY, JSettings.ROUNDING_FORMULA_BRZYCKI);
    }

    @Override
    protected String label() {
        return "Formula";
    }

    @Override
    protected int defaultSelection() {
        JSettings settings = (JSettings) JSettingsStore.instance().first();
        return this.options().indexOf(settings.roundingFormula);
    }

    @Override
    protected void valueChanged(int position) {
        JSettings settings = (JSettings) JSettingsStore.instance().first();
        settings.roundingFormula = this.options().get(position);
    }
}
