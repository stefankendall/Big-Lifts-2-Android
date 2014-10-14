package com.stefankendall.BigLifts.views.fto.plan.assistance.simplecustom.editset;

import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.data.models.JSet;
import com.stefankendall.BigLifts.views.cells.SpinnerCell;

import java.util.List;

public class FTOAssistanceLiftSelector extends SpinnerCell {
    private final JSet set;

    public FTOAssistanceLiftSelector(JSet set) {
        this.set = set;
    }

    @Override
    protected List<String> options() {
        return Lists.newArrayList();
    }

    @Override
    protected String label() {
        return "Lift";
    }

    @Override
    protected int defaultSelection() {
        return 0;
    }

    @Override
    protected void valueChanged(int position) {

    }
}
