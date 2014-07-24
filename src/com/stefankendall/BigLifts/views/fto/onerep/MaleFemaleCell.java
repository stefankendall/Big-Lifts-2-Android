package com.stefankendall.BigLifts.views.fto.onerep;

import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.data.models.JSettings;
import com.stefankendall.BigLifts.data.stores.JSettingsStore;
import com.stefankendall.BigLifts.views.cells.SpinnerCell;
import com.stefankendall.BigLifts.views.fto.barloading.FieldWatcher;

import java.util.List;

public class MaleFemaleCell extends SpinnerCell {

    public MaleFemaleCell(FieldWatcher fieldWatcher) {
        super(fieldWatcher);
    }

    @Override
    protected List<String> options() {
        return Lists.newArrayList("Male", "Female");
    }

    @Override
    protected String label() {
        return "Sex";
    }

    @Override
    protected int defaultSelection() {
        JSettings settings = (JSettings) JSettingsStore.instance().first();
        return settings.isMale ? 0 : 1;
    }

    @Override
    protected void valueChanged(int position) {
        JSettings settings = (JSettings) JSettingsStore.instance().first();
        settings.isMale = position == 0;
    }
}
