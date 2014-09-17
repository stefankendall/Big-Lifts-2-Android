package com.stefankendall.BigLifts.views.fto.lift.individual;

import com.stefankendall.BigLifts.data.models.JSet;

import java.math.BigDecimal;

public class SetChange {
    public Integer reps;
    public BigDecimal weight;
    public JSet modifyingSet;

    private static SetChange instance;

    public static SetChange instance() {
        if (instance == null) {
            instance = new SetChange();
        }

        return instance;
    }

    public static void reset() {
        SetChange.instance().reps = null;
        SetChange.instance().weight = null;
        SetChange.instance().modifyingSet = null;
    }
}
