package com.stefankendall.BigLifts.views.fto.lift.individual.change;

import com.stefankendall.BigLifts.views.cells.ParameterizedIntegerInputCell;
import com.stefankendall.BigLifts.views.fto.lift.individual.SetChange;

public class RepsCell extends ParameterizedIntegerInputCell {
    public RepsCell(Integer reps) {
        super("Reps", "", reps + "");
    }

    @Override
    protected void stringValueChanged(String s) {
        try {
            SetChange.instance().reps = Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            SetChange.instance().reps = null;
        }
    }
}
