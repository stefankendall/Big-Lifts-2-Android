package com.stefankendall.BigLifts.data.stores;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.data.models.JModel;
import com.stefankendall.BigLifts.data.models.JSettings;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class JSettingsStore extends BLJStore {
    @Override
    public Class<? extends JModel> modelClass() {
        return JSettings.class;
    }

    public static JSettingsStore instance() {
        return (JSettingsStore) BLJStore.instance(JSettingsStore.class);
    }

    @Override
    public void setupDefaults() {
        JSettings settings = (JSettings) this.create();
        settings.units = "lbs";
        settings.roundTo = new BigDecimal("5");
        settings.screenAlwaysOn = false;
        settings.roundingFormula = JSettings.ROUNDING_FORMULA_EPLEY;
        settings.roundingType = JSettings.ROUNDING_TYPE_NORMAL;
        settings.isMale = true;
        settings.barLoadingEnabled = true;
    }

    @Override
    protected List<Class> getAssociations() {
        return Lists.newArrayList();
    }

    public void adjustForKg() {
        JSettings settings = (JSettings) this.first();
        if (settings.units.equals("kg")) {
            settings.roundTo = new BigDecimal("1");
        } else {
            settings.roundTo = new BigDecimal("5");
        }
    }

    public BigDecimal defaultIncrementForLift(String lift) {
        JSettings settings = (JSettings) this.first();
        if (settings.units.equals("kg")) {
            return this.defaultKgIncrementForLift(lift);
        } else {
            return this.defaultLbsIncrementForLift(lift);
        }
    }

    public BigDecimal defaultLbsIncrementForLift(String lift) {
        Map<String, BigDecimal> defaultIncrements = ImmutableMap.<String, BigDecimal>builder()
                .put("Press", new BigDecimal("5"))
                .put("Bench", new BigDecimal("5"))
                .put("Power Clean", new BigDecimal("5"))
                .put("Deadlift", new BigDecimal("10"))
                .put("Squat", new BigDecimal("10"))
                .put("Back Extension", new BigDecimal("0"))
                .build();
        return defaultIncrements.get(lift);
    }

    public BigDecimal defaultKgIncrementForLift(String lift) {
        Map<String, BigDecimal> defaultIncrements = ImmutableMap.<String, BigDecimal>builder()
                .put("Press", new BigDecimal("2"))
                .put("Bench", new BigDecimal("2"))
                .put("Power Clean", new BigDecimal("2"))
                .put("Deadlift", new BigDecimal("5"))
                .put("Squat", new BigDecimal("5"))
                .put("Back Extension", new BigDecimal("0"))
                .build();
        return defaultIncrements.get(lift);
    }
}
