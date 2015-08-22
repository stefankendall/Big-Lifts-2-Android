package com.stefankendall.BigLifts.views.fto.plan.assistance.simplecustom.editset;

import com.stefankendall.BigLifts.data.models.JSet;
import com.stefankendall.BigLifts.views.cells.ParameterizedIntegerInputCell;

public class CustomRepsCell extends ParameterizedIntegerInputCell {
    private final JSet set;

    public CustomRepsCell(JSet set) {
        super("Reps", "");
        this.set = set;
    }

    @Override
    protected String defaultValue() {
        return this.set.reps == null ? "" : this.set.reps + "";
    }

    @Override
    protected void stringValueChanged(String s) {
        this.set.reps = this.getValue();
    }
}
