package com.stefankendall.BigLiftsPro.allprograms.formulas.bar;

import com.stefankendall.BigLiftsPro.data.models.JPlate;

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
