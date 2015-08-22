package com.stefankendall.BigLifts.views.fto.plan.assistance.boringbutbig;

import com.stefankendall.BigLifts.data.models.fto.JFTOBoringButBig;
import com.stefankendall.BigLifts.data.numbers.BigDecimals;
import com.stefankendall.BigLifts.data.stores.fto.JFTOBoringButBigStore;
import com.stefankendall.BigLifts.views.cells.ParameterizedDecimalInputCell;

public class BoringButBigPercentageCell extends ParameterizedDecimalInputCell {
    public BoringButBigPercentageCell() {
        super("Lift % of max", "");
    }

    @Override
    protected String defaultValue() {
        JFTOBoringButBig boringButBig = (JFTOBoringButBig) JFTOBoringButBigStore.instance().first();
        return BigDecimals.print(boringButBig.percentage);
    }

    @Override
    protected void stringValueChanged(String s) {
        JFTOBoringButBig boringButBig = (JFTOBoringButBig) JFTOBoringButBigStore.instance().first();
        boringButBig.percentage = this.getValue();
    }
}
