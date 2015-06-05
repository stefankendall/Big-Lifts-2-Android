package com.stefankendall.BigLiftsPro.views.fto.plan.assistance.simplecustom.editlifts;

import com.stefankendall.BigLiftsPro.data.models.fto.JFTOCustomAssistanceLift;
import com.stefankendall.BigLiftsPro.data.numbers.BigDecimals;
import com.stefankendall.BigLiftsPro.views.cells.ParameterizedDecimalInputCell;

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
