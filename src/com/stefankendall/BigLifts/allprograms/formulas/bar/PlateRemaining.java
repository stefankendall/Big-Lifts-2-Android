package com.stefankendall.BigLifts.allprograms.formulas.bar;

import com.stefankendall.BigLifts.data.models.JPlate;

import java.math.BigDecimal;

public class PlateRemaining {
    public BigDecimal weight;
    public int count;

    public PlateRemaining(BigDecimal weight, int count) {
        this.weight = weight;
        this.count = count;
    }

    public PlateRemaining(JPlate p) {
        this(p.weight, p.count);
    }
}
