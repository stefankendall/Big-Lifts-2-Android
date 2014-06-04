package com.stefankendall.BigLifts.data.stores;

import com.stefankendall.BigLifts.data.models.JLift;
import com.stefankendall.BigLifts.data.models.JSet;
import com.stefankendall.BigLifts.data.models.fto.JFTOSet;
import com.stefankendall.BigLifts.data.stores.fto.JFTOSetStore;

import java.math.BigDecimal;

public class JSetData {
    public int reps;
    public int maxReps;
    public BigDecimal percentage;
    public JLift lift;
    public boolean amrap;
    public boolean warmup;
    public boolean optional;

    public static JSetData builder() {
        return new JSetData();
    }

    public JSetData withReps(int reps) {
        this.reps = reps;
        return this;
    }

    public JSetData withMaxReps(int maxReps) {
        this.maxReps = maxReps;
        return this;
    }

    public JSetData withPercentage(BigDecimal percentage) {
        this.percentage = percentage;
        return this;
    }

    public JSetData withLift(JLift lift) {
        this.lift = lift;
        return this;
    }

    public JSetData withAmrap(boolean amrap) {
        this.amrap = amrap;
        return this;
    }

    public JSetData withWarmup(boolean warmup) {
        this.warmup = warmup;
        return this;
    }

    public JSet createSet() {
        JFTOSet set = (JFTOSet) JFTOSetStore.instance().create();
        set.reps = this.reps;
        if (this.maxReps > 0) {
            set.maxReps = this.maxReps;
        }
        set.percentage = this.percentage;
        set.lift = this.lift;
        set.amrap = this.amrap;
        set.warmup = this.warmup;
        set.optional = this.optional;
        return set;
    }
}
