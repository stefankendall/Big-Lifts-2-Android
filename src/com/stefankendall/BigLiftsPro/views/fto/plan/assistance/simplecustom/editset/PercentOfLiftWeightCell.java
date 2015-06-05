package com.stefankendall.BigLiftsPro.views.fto.plan.assistance.simplecustom.editset;

import com.stefankendall.BigLiftsPro.data.models.JSet;
import com.stefankendall.BigLiftsPro.data.numbers.BigDecimals;
import com.stefankendall.BigLiftsPro.views.cells.ParameterizedDecimalInputCell;

public class PercentOfLiftWeightCell extends ParameterizedDecimalInputCell {
    private final JSet set;

    public PercentOfLiftWeightCell(JSet set) {
        super("% of lift weight", "", "100");
        this.set = set;
    }

    @Override
    protected String defaultValue() {
        return BigDecimals.print(this.set.percentage);
    }

    @Override
    protected void stringValueChanged(String s) {
        this.set.percentage = this.getValue();
    }
}
