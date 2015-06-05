package com.stefankendall.BigLiftsPro.views.fto.onerep;

import com.stefankendall.BigLiftsPro.allprograms.formulas.WilksCoefficientCalculator;
import com.stefankendall.BigLiftsPro.data.models.JSettings;
import com.stefankendall.BigLiftsPro.data.numbers.BigDecimals;
import com.stefankendall.BigLiftsPro.data.stores.JSettingsStore;
import com.stefankendall.BigLiftsPro.views.cells.ParameterizedDecimalInputCell;

import java.math.BigDecimal;

public class WilksCell extends ParameterizedDecimalInputCell {
    public WilksCell() {
        super("", "");
    }

    @Override
    protected String label() {
        return "Wilk's coefficient";
    }

    public void update(BigDecimal weight, BigDecimal bodyweight) {
        JSettings settings = (JSettings) JSettingsStore.instance().first();
        this.setValue(BigDecimals.print(WilksCoefficientCalculator.calculate(weight, bodyweight, settings.isMale, settings.units)));
    }

    @Override
    protected boolean enabled() {
        return false;
    }
}
