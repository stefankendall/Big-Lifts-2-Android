package com.stefankendall.BigLiftsPro.views.fto.settings;

import com.stefankendall.BigLiftsPro.data.models.JBar;
import com.stefankendall.BigLiftsPro.data.numbers.BigDecimals;
import com.stefankendall.BigLiftsPro.data.stores.JBarStore;
import com.stefankendall.BigLiftsPro.views.cells.ParameterizedDecimalInputCell;

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
