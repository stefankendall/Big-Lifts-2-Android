package com.stefankendall.BigLifts.views.cells;

import com.stefankendall.BigLifts.R;

public abstract class IntegerInputCell extends NumberInputCell {
    public int getValue() {
        try {
            return Integer.parseInt(this.input.getText().toString());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.integer_input_cell;
    }
}
