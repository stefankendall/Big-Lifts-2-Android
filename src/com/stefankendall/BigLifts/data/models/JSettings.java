package com.stefankendall.BigLifts.data.models;

import com.stefankendall.BigLifts.data.stores.JBarStore;
import com.stefankendall.BigLifts.data.stores.JPlateStore;
import com.stefankendall.BigLifts.data.stores.JSettingsStore;
import com.stefankendall.BigLifts.data.stores.fto.JFTOLiftStore;

import java.math.BigDecimal;

public class JSettings extends JModel {
    public static String ROUNDING_FORMULA_EPLEY = "Epley";
    public static String ROUNDING_FORMULA_BRZYCKI = "Brzycki";
    public static String ROUNDING_TYPE_NORMAL = "Normal";
    public static String ROUNDING_TYPE_UP = "Up";
    public static String ROUNDING_TYPE_DOWN = "Down";
    public static String NEAREST_5_ROUNDING = "5.5";

    public String units;
    public BigDecimal roundTo;
    public String roundingType;
    public String roundingFormula;
    public boolean screenAlwaysOn;
    public boolean isMale;
    public BigDecimal bodyweight;

    public void setUnits(String units) {
        this.units = units;
        JSettingsStore.instance().adjustForKg();
        JFTOLiftStore.instance().adjustForKg();
        JBarStore.instance().adjustForKg();
        JPlateStore.instance().adjustForKg();
    }

    public void setRoundTo(BigDecimal roundTo) {
        this.roundTo = roundTo;
    }

    public void setRoundingType(String roundingType) {
        this.roundingType = roundingType;
    }

    public void setRoundingFormula(String roundingFormula) {
        this.roundingFormula = roundingFormula;
    }

    public void setScreenAlwaysOn(boolean screenAlwaysOn) {
        this.screenAlwaysOn = screenAlwaysOn;
    }

    public void setMale(boolean isMale) {
        this.isMale = isMale;
    }

    public void setBodyweight(BigDecimal bodyweight) {
        this.bodyweight = bodyweight;
    }
}
