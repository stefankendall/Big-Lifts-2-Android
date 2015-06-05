package com.stefankendall.BigLiftsPro.views.fto.lift.individual.change;

import com.stefankendall.BigLiftsPro.data.numbers.BigDecimals;
import com.stefankendall.BigLiftsPro.views.cells.ParameterizedDecimalInputCell;
import com.stefankendall.BigLiftsPro.views.fto.lift.individual.SetChange;

import java.math.BigDecimal;

public class WeightCell extends ParameterizedDecimalInputCell {
    public WeightCell(BigDecimal weight) {
        super("Weight", "", BigDecimals.print(weight));
    }

    @Override
    protected void stringValueChanged(String s) {
        if (s.trim().equals("")) {
            SetChange.instance().weight = null;
        } else {
            SetChange.instance().weight = BigDecimals.parse(s);
        }
    }
}
