package com.stefankendall.BigLiftsPro.views.fto.plan.assistance.boringbutbig;

import com.stefankendall.BigLiftsPro.data.models.fto.JFTOBoringButBig;
import com.stefankendall.BigLiftsPro.data.numbers.BigDecimals;
import com.stefankendall.BigLiftsPro.data.stores.fto.JFTOBoringButBigStore;
import com.stefankendall.BigLiftsPro.views.cells.ParameterizedDecimalInputCell;

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
