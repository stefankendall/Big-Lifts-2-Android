package com.stefankendall.BigLiftsPro.views.cells;

import com.stefankendall.BigLiftsPro.R;

public class ParameterizedIntegerInputCell extends ParameterizedNumberInputCell {
    public ParameterizedIntegerInputCell(String label, String defaultValue) {
        super(label, defaultValue);
    }

    public ParameterizedIntegerInputCell(String label, String defaultValue, String hint) {
        super(label, defaultValue, hint);
    }

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
