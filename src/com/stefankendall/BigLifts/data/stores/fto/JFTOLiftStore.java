package com.stefankendall.BigLifts.data.stores.fto;

import com.stefankendall.BigLifts.data.models.JModel;
import com.stefankendall.BigLifts.data.models.JSettings;
import com.stefankendall.BigLifts.data.models.fto.JFTOLift;
import com.stefankendall.BigLifts.data.stores.BLJStore;
import com.stefankendall.BigLifts.data.stores.JLiftStore;
import com.stefankendall.BigLifts.data.stores.JSettingsStore;

import java.math.BigDecimal;

public class JFTOLiftStore extends JLiftStore {
    public static JFTOLiftStore instance() {
        return (JFTOLiftStore) BLJStore.instance(JFTOLiftStore.class);
    }

    @Override
    public Class<? extends JModel> modelClass() {
        return JFTOLift.class;
    }

    @Override
    public void setDefaultsForObject(JModel object) {
        super.setDefaultsForObject(object);
        JFTOLift lift = (JFTOLift) object;
        Integer maxOrder = (Integer) this.max("order");
        lift.order = maxOrder == null ? 0 : maxOrder.intValue() + 1;
    }

    @Override
    public void setupDefaults() {
        this.isSettingDefaults = true;

        this.create("Bench", new BigDecimal("5"), 0);
        this.create("Squat", new BigDecimal("10"), 1);
        this.create("Press", new BigDecimal("5"), 2);
        this.create("Deadlift", new BigDecimal("10"), 3);

        this.isSettingDefaults = false;
    }

    private void create(String name, BigDecimal increment, int order) {
        JFTOLift lift = (JFTOLift) this.create();
        lift.name = name;
        lift.increment = increment;
        lift.order = order;
        lift.usesBar = true;
    }

    public void adjustForKg() {
        JSettingsStore settingsStore = JSettingsStore.instance();
        JSettings settings = (JSettings) settingsStore.first();
        for (JModel model : this.findAll()) {
            JFTOLift lift = (JFTOLift) model;
            if (lift.increment == null) {
                continue;
            }

            if (settings.units.equals("kg")) {
                lift.increment = lift.increment.equals(settingsStore.defaultLbsIncrementForLift(lift.name))
                        ? settingsStore.defaultIncrementForLift(lift.name) : lift.increment;
            } else {
                lift.increment = lift.increment.equals(settingsStore.defaultKgIncrementForLift(lift.name))
                        ? settingsStore.defaultIncrementForLift(lift.name) : lift.increment;
            }
        }
    }

    @Override
    public void liftsChanged() {
        JFTOBoringButBigLiftStore.instance().adjustToMainLifts();
        JFTOWorkoutStore.instance().switchTemplate();
    }

    public void swapOrder(JFTOLift first, JFTOLift second) {
        int order = first.order;
        first.order = second.order;
        second.order = order;

        JFTOWorkoutStore.instance().switchTemplate();
    }
}
