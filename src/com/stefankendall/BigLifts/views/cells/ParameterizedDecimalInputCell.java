package com.stefankendall.BigLifts.views.cells;

import com.google.common.base.Function;

import java.math.BigDecimal;

public class ParameterizedDecimalInputCell extends DecimalInputCell {

    public String label;
    public String defaultValue;

    public ParameterizedDecimalInputCell(String label, String defaultValue) {
        this.label = label;
        this.defaultValue = defaultValue;
    }

    @Override
    protected String label() {
        return this.label;
    }

    @Override
    protected String defaultValue() {
        return this.defaultValue;
    }

    @Override
    protected void valueChanged(BigDecimal value) {
    }
}
