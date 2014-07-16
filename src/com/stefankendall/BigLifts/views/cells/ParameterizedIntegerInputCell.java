package com.stefankendall.BigLifts.views.cells;

import java.math.BigDecimal;

public class ParameterizedIntegerInputCell extends IntegerInputCell {
    private String defaultValue;
    private String label;

    public ParameterizedIntegerInputCell(String label, String defaultValue) {
        this.label = label;
        this.defaultValue = defaultValue;
    }

    @Override
    protected void stringValueChanged(String s) {
    }

    @Override
    protected String label() {
        return this.label;
    }

    @Override
    protected String defaultValue() {
        return this.defaultValue;
    }
}
