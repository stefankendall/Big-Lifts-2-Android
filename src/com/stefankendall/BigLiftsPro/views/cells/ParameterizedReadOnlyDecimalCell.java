package com.stefankendall.BigLiftsPro.views.cells;

import com.stefankendall.BigLiftsPro.R;

public class ParameterizedReadOnlyDecimalCell extends ReadOnlyDecimalCell {
    private final String label;

    public ParameterizedReadOnlyDecimalCell(String label) {
        this.label = label;
    }

    protected int getLayoutResource() {
        return R.layout.read_only_decimal_cell;
    }

    protected String label() {
        return this.label;
    }
}
