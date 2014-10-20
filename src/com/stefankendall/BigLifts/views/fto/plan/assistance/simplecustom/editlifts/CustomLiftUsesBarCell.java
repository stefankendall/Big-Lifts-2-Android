package com.stefankendall.BigLifts.views.fto.plan.assistance.simplecustom.editlifts;

import com.stefankendall.BigLifts.data.models.fto.JFTOCustomAssistanceLift;
import com.stefankendall.BigLifts.views.cells.SwitchCell;

public class CustomLiftUsesBarCell extends SwitchCell {
    private final JFTOCustomAssistanceLift lift;

    public CustomLiftUsesBarCell(JFTOCustomAssistanceLift lift) {
        this.lift = lift;
    }

    @Override
    protected String label() {
        return "Uses bar?";
    }

    @Override
    protected boolean defaultState() {
        return lift.usesBar;
    }

    @Override
    protected void valueChanged(boolean state) {
        lift.usesBar = state;
    }
}
