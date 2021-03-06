package com.stefankendall.BigLiftsPro.data.stores;

import com.google.common.collect.Lists;
import com.stefankendall.BigLiftsPro.data.models.JLift;
import com.stefankendall.BigLiftsPro.data.models.JModel;
import com.stefankendall.BigLiftsPro.data.stores.fto.JFTOSetStore;

import java.math.BigDecimal;
import java.util.List;

public class JLiftStore extends BLJStore {
    protected boolean isSettingDefaults = false;

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

    @Override
    protected List<Class> getAssociations() {
        return Lists.newArrayList();
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
            if (lift.weight != null && lift.increment != null) {
                lift.weight = lift.weight.add(lift.increment);
            }
        }
    }

    @Override
    public void remove(JModel model) {
        super.remove(model);
        this.liftsChanged();
    }

    public void liftsChanged() {
        JSetStore.instance().adjustToLifts();
        JFTOSetStore.instance().adjustToLifts();
        JWorkoutStore.instance().adjustToLifts();
    }

    @Override
    public Object create() {
        JLift lift = (JLift) super.create();
        if (!this.isSettingDefaults) {
            this.liftsChanged();
        }
        return lift;
    }
}
