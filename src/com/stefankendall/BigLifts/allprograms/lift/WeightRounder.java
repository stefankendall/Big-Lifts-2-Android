package com.stefankendall.BigLifts.allprograms.lift;

import com.google.common.collect.ImmutableMap;
import com.stefankendall.BigLifts.data.models.JSettings;
import com.stefankendall.BigLifts.data.stores.JSettingsStore;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class WeightRounder {
    public static BigDecimal round(BigDecimal number) {
        if (number == null) {
            return BigDecimal.ZERO;
        }

        JSettings settings = (JSettings) JSettingsStore.instance().first();
        if (new BigDecimal(JSettings.NEAREST_5_ROUNDING).equals(settings.roundTo)) {
            return WeightRounder.roundToNearest5(number, settings.roundingType);
        } else if (new BigDecimal("5").equals(settings.roundTo)) {
            return WeightRounder.roundTo5(number, settings.roundingType);
        } else if (new BigDecimal("2.5").equals(settings.roundTo)) {
            return WeightRounder.roundTo2p5(number, settings.roundingType);
        } else if (new BigDecimal("2").equals(settings.roundTo)) {
            return WeightRounder.roundTo2(number, settings.roundingType);
        } else {
            return WeightRounder.roundTo1(number, settings.roundingType);
        }
    }

    protected static BigDecimal roundToNearest5(BigDecimal number, String roundingType) {
        return null;
    }

    protected static BigDecimal roundTo5(BigDecimal number, String roundingType) {
        int base5Round = number.divide(new BigDecimal("5")).intValue() * 5;
        if (roundingType.equals(JSettings.ROUNDING_TYPE_DOWN)) {
            return new BigDecimal(base5Round);
        } else if (roundingType.equals(JSettings.ROUNDING_TYPE_UP)) {
            BigDecimal base5 = new BigDecimal(base5Round);
            if (base5.equals(number)) {
                return base5;
            } else {
                return base5.add(new BigDecimal("5"));
            }
        }

        int lastDigitAndDecimalTimes10 = number.multiply(new BigDecimal("10")).intValue() % 100;
        if (lastDigitAndDecimalTimes10 >= 50) {
            lastDigitAndDecimalTimes10 -= 50;
        }

        if (lastDigitAndDecimalTimes10 == 0) {
            return WeightRounder.roundTo1(number, JSettings.ROUNDING_TYPE_NORMAL);
        } else if (lastDigitAndDecimalTimes10 < 25) {
            return new BigDecimal(base5Round);
        } else {
            return new BigDecimal(base5Round + 5);
        }
    }

    protected static BigDecimal roundTo2p5(BigDecimal number, String roundingType) {
        return null;
    }

    protected static BigDecimal roundTo2(BigDecimal number, String roundingType) {
        return null;
    }

    protected static BigDecimal roundTo1(BigDecimal number, String roundingType) {
        Map<String, RoundingMode> rounding = ImmutableMap.<String, RoundingMode>builder()
                .put(JSettings.ROUNDING_TYPE_DOWN, RoundingMode.DOWN)
                .put(JSettings.ROUNDING_TYPE_NORMAL, RoundingMode.HALF_UP)
                .put(JSettings.ROUNDING_TYPE_UP, RoundingMode.UP)
                .build();
        return number.setScale(0, rounding.get(roundingType));
    }
}
