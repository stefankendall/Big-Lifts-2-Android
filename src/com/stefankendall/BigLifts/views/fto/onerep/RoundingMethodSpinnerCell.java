package com.stefankendall.BigLifts.views.fto.onerep;

import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.views.cells.SpinnerCell;

import java.util.List;

public class RoundingMethodSpinnerCell extends SpinnerCell {
    @Override
    protected List<String> options() {
        return Lists.newArrayList("Epley", "Brzycki");
    }

    @Override
    protected String label() {
        return "Formula";
    }

    @Override
    protected int defaultSelection() {
        return 0;
    }

    @Override
    protected void valueChanged(int position) {

    }
}
