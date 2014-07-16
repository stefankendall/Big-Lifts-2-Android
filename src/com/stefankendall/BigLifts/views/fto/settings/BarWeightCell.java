package com.stefankendall.BigLifts.views.fto.settings;

import com.stefankendall.BigLifts.data.models.JBar;
import com.stefankendall.BigLifts.data.numbers.BigDecimals;
import com.stefankendall.BigLifts.data.stores.JBarStore;
import com.stefankendall.BigLifts.views.cells.DecimalInputCell;

import java.math.BigDecimal;

public class BarWeightCell extends DecimalInputCell {
    @Override
    protected String label() {
        return "Bar Weight";
    }

    @Override
    protected String defaultValue() {
        JBar bar = (JBar) JBarStore.instance().first();
        return BigDecimals.print(bar.weight);
    }

    @Override
    protected void valueChanged(BigDecimal value) {
        JBar bar = (JBar) JBarStore.instance().first();
        bar.weight = value;
    }
}
