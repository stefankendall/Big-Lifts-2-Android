package com.stefankendall.BigLifts.views.fto.settings;

import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.data.models.JSettings;
import com.stefankendall.BigLifts.data.numbers.BigDecimals;
import com.stefankendall.BigLifts.data.stores.JSettingsStore;
import com.stefankendall.BigLifts.views.cells.SpinnerCell;

import java.util.List;

public class RoundToCell extends SpinnerCell {

    private static List<String> options = Lists.newArrayList("0.5", "1", "2", "2.5", "5", "Nearest 5");

    @Override
    protected List<String> options() {
        return options;
    }

    @Override
    protected String label() {
        return "Round To";
    }

    @Override
    protected int defaultSelection() {
        JSettings settings = (JSettings) JSettingsStore.instance().first();
        String roundTo = BigDecimals.print(settings.roundTo);
        if (roundTo.equals(JSettings.NEAREST_5_ROUNDING)) {
            return options.size() - 1;
        }

        return options.indexOf(roundTo);
    }

    @Override
    protected void valueChanged(int position) {
        JSettings settings = (JSettings) JSettingsStore.instance().first();
        settings.roundTo = BigDecimals.parse(options.get(position).toString());
    }
}
