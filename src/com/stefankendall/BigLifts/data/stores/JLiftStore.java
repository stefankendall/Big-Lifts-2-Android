package com.stefankendall.BigLifts.data.stores;

import com.stefankendall.BigLifts.data.models.JLift;
import com.stefankendall.BigLifts.data.models.JModel;

import java.math.BigDecimal;

public class JLiftStore extends BLJStore {
    public static JLiftStore instance() {
        return (JLiftStore) BLJStore.instance(JLiftStore.class);
    }

    @Override
    public Class<? extends JModel> modelClass() {
        return JLift.class;
    }

    @Override
    public void setDefaultsForObject(JModel object) {
        JLift lift = (JLift) object;
        lift.weight = new BigDecimal("0");
    }

    public void copyInto(JLift source, JLift dest) {
        dest.name = source.name;
        dest.weight = source.weight;
        dest.increment = source.increment;
        dest.order = source.order;
        dest.usesBar = source.usesBar;
    }

    public void incrementLifts() {
        for (Object object : this.findAll()) {
            JLift lift = (JLift) object;
            lift.weight = lift.weight.add(lift.increment);
        }
    }
}
