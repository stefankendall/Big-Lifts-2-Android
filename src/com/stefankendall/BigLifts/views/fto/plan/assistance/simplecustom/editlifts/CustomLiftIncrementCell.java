package com.stefankendall.BigLifts.views.fto.plan.assistance.simplecustom.editlifts;

import com.stefankendall.BigLifts.data.models.fto.JFTOCustomAssistanceLift;
import com.stefankendall.BigLifts.data.numbers.BigDecimals;
import com.stefankendall.BigLifts.views.cells.ParameterizedDecimalInputCell;

public class CustomLiftIncrementCell extends ParameterizedDecimalInputCell {
    private final JFTOCustomAssistanceLift lift;

    public CustomLiftIncrementCell(JFTOCustomAssistanceLift lift) {
        super("Increment after cycle", "");
        this.lift = lift;
    }

    @Override
    protected String defaultValue() {
        return BigDecimals.print(this.lift.increment);
    }

    @Override
    protected void stringValueChanged(String s) {
        this.lift.increment = this.getValue();
    }
}
