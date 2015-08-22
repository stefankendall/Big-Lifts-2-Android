package com.stefankendall.BigLifts.views.cells;

import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.data.numbers.BigDecimals;

import java.math.BigDecimal;

public class ParameterizedDecimalInputCell extends ParameterizedNumberInputCell {
    public ParameterizedDecimalInputCell(String label, String defaultValue) {
        super(label, defaultValue);
    }

    public ParameterizedDecimalInputCell(String label, String defaultValue, String hint) {
        super(label, defaultValue, hint);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.decimal_input_cell;
    }

    public BigDecimal getValue() {
        return BigDecimals.parse(this.input.getText().toString());
    }
}
