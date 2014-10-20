package com.stefankendall.BigLifts.views.fto.plan.assistance.simplecustom.editlifts;

import com.stefankendall.BigLifts.data.models.fto.JFTOCustomAssistanceLift;
import com.stefankendall.BigLifts.data.numbers.BigDecimals;
import com.stefankendall.BigLifts.views.cells.ParameterizedDecimalInputCell;

public class CustomLiftWeightCell extends ParameterizedDecimalInputCell {
    private final JFTOCustomAssistanceLift lift;

    public CustomLiftWeightCell(JFTOCustomAssistanceLift lift) {
        super("Weight", "");
        this.lift = lift;
    }

    @Override
    protected String defaultValue() {
        return BigDecimals.print(this.lift.weight);
    }

    @Override
    protected void stringValueChanged(String s) {
        this.lift.weight = this.getValue();
    }
}
