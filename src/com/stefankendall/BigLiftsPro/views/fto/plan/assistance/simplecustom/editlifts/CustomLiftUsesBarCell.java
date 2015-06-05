package com.stefankendall.BigLiftsPro.views.fto.plan.assistance.simplecustom.editlifts;

import com.stefankendall.BigLiftsPro.data.models.fto.JFTOCustomAssistanceLift;
import com.stefankendall.BigLiftsPro.views.cells.SwitchCell;

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
