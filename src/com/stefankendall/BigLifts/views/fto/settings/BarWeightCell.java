package com.stefankendall.BigLifts.views.fto.settings;

import com.stefankendall.BigLifts.data.models.JBar;
import com.stefankendall.BigLifts.data.numbers.BigDecimals;
import com.stefankendall.BigLifts.data.stores.JBarStore;
import com.stefankendall.BigLifts.views.cells.ParameterizedDecimalInputCell;

import java.math.BigDecimal;

public class BarWeightCell extends ParameterizedDecimalInputCell {
    public BarWeightCell() {
        super("Bar Weight", "");
    }

    @Override
    protected void stringValueChanged(String s) {
        this.valueChanged(this.getValue());
    }

    @Override
    protected String defaultValue() {
        JBar bar = (JBar) JBarStore.instance().first();
        return BigDecimals.print(bar.weight);
    }

    protected void valueChanged(BigDecimal value) {
        JBar bar = (JBar) JBarStore.instance().first();
        bar.weight = value;
    }
}
