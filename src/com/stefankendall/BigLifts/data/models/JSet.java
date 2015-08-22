package com.stefankendall.BigLifts.data.models;

import com.stefankendall.BigLifts.allprograms.lift.WeightRounder;
import com.stefankendall.BigLifts.data.stores.JBarStore;

import java.math.BigDecimal;

public class JSet extends JModel {
    public Integer reps;
    public Integer maxReps;
    public BigDecimal percentage;
    public JLift lift;
    public boolean warmup;
    public boolean amrap;
    public boolean optional;
    public boolean assistance;

    public BigDecimal effectiveWeight() {
        if (this.percentage == null || this.lift == null) {
            return BigDecimal.ZERO;
        }

        BigDecimal effectiveWeight = this.lift.weight.multiply(this.percentage).divide(new BigDecimal("100"));

        if (this.lift.usesBar) {
            JBar bar = (JBar) JBarStore.instance().first();
            if (effectiveWeight.compareTo(bar.weight) < 0) {
                return bar.weight;
            }
        }

        return effectiveWeight;
    }

    public BigDecimal roundedEffectiveWeight(){
        return WeightRounder.round(this.effectiveWeight());
    }
}
