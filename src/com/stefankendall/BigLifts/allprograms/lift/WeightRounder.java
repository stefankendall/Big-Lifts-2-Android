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
        BigDecimal lastPart = WeightRounder.getLastPartsOf(number);
        if (lastPart.equals(new BigDecimal("5"))) {
            return number;
        }

        if (roundingType.equals(JSettings.ROUNDING_TYPE_UP)) {
            if (lastPart.compareTo(new BigDecimal("5")) < 0) {
                return number.subtract(lastPart).add(new BigDecimal("5"));
            } else {
                return number.subtract(lastPart).add(new BigDecimal("15"));
            }
        } else if (roundingType.equals(JSettings.ROUNDING_TYPE_DOWN)) {
            if (lastPart.compareTo(new BigDecimal("5")) < 0) {
                return number.subtract(lastPart).subtract(new BigDecimal("5"));
            } else {
                return number.subtract(lastPart).add(new BigDecimal("5"));
            }
        }

        BigDecimal roundedTo5 = WeightRounder.roundTo5(number, JSettings.ROUNDING_TYPE_NORMAL);
        if (roundedTo5.intValue() % 10 == 0) {
            BigDecimal up = roundedTo5.add(new BigDecimal("3"));
            BigDecimal down = roundedTo5.subtract(new BigDecimal("3"));
            BigDecimal up5 = WeightRounder.roundTo5(up, JSettings.ROUNDING_TYPE_NORMAL);
            BigDecimal down5 = WeightRounder.roundTo5(down, JSettings.ROUNDING_TYPE_NORMAL);

            BigDecimal upDistance = up5.subtract(number);
            BigDecimal downDistance = number.subtract(down5);

            int upToDown = upDistance.compareTo(downDistance);
            return upToDown <= 0 ? up5 : down5;
        } else {
            return roundedTo5;
        }
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
        BigDecimal lastDigitAndDecimals = WeightRounder.getLastPartsOf(number);

        if (lastDigitAndDecimals.equals(new BigDecimal("2.5"))
                || lastDigitAndDecimals.equals(new BigDecimal("5"))
                || lastDigitAndDecimals.equals(new BigDecimal("7.5"))
                || lastDigitAndDecimals.equals(new BigDecimal("0"))) {
            return number;
        }

        BigDecimal numberWithoutLastDigits = number.subtract(lastDigitAndDecimals);
        BigDecimal roundedNumber = null;

        if (lastDigitAndDecimals.compareTo(new BigDecimal("1.25")) < 0) {
            roundedNumber = numberWithoutLastDigits;
        } else if (lastDigitAndDecimals.compareTo(new BigDecimal("3.75")) < 0) {
            roundedNumber = numberWithoutLastDigits.add(new BigDecimal("2.5"));
        } else if (lastDigitAndDecimals.compareTo(new BigDecimal("6.25")) < 0) {
            roundedNumber = numberWithoutLastDigits.add(new BigDecimal("5"));
        } else if (lastDigitAndDecimals.compareTo(new BigDecimal("8.75")) < 0) {
            roundedNumber = numberWithoutLastDigits.add(new BigDecimal("7.5"));
        } else {
            roundedNumber = numberWithoutLastDigits.add(BigDecimal.TEN);
        }

        if (roundingType.equals(JSettings.ROUNDING_TYPE_UP)) {
            if (roundedNumber.compareTo(number) < 0) {
                return roundedNumber.add(new BigDecimal("2.5"));
            }
        } else if (roundingType.equals(JSettings.ROUNDING_TYPE_DOWN)) {
            if (roundedNumber.compareTo(number) > 0) {
                return roundedNumber.subtract(new BigDecimal("2.5"));
            }
        }

        return roundedNumber;
    }

    private static BigDecimal getLastPartsOf(BigDecimal number) {
        BigDecimal wholeNumbers = number.setScale(0, RoundingMode.DOWN);
        BigDecimal decimals = number.subtract(wholeNumbers);
        int lastDigit = wholeNumbers.intValue() % 10;
        BigDecimal lastDigitDecimal = new BigDecimal(lastDigit);
        return lastDigitDecimal.add(decimals);
    }

    protected static BigDecimal roundTo2(BigDecimal number, String roundingType) {
        int lastDigitAndDecimalTimes10 = number.multiply(BigDecimal.TEN).intValue() % 100;
        while (lastDigitAndDecimalTimes10 >= 20) {
            lastDigitAndDecimalTimes10 -= 20;
        }

        BigDecimal roundedTo1 = WeightRounder.roundTo1(number, roundingType);
        if (lastDigitAndDecimalTimes10 == 0) {
            return roundedTo1;
        }

        BigDecimal numberWithoutDecimals = number.subtract(WeightRounder.getDecimalPart(number));
        if (roundingType.equals(JSettings.ROUNDING_TYPE_UP)) {
            if (numberWithoutDecimals.intValue() % 2 == 0) {
                return numberWithoutDecimals.add(new BigDecimal("2"));
            } else {
                return numberWithoutDecimals.add(new BigDecimal("1"));
            }
        } else if (roundingType.equals(JSettings.ROUNDING_TYPE_DOWN)) {
            if (numberWithoutDecimals.intValue() % 2 == 0) {
                return numberWithoutDecimals;
            } else {
                return numberWithoutDecimals.subtract(BigDecimal.ONE);
            }
        }

        if (roundedTo1.compareTo(number) > 0) {
            return roundedTo1.subtract(BigDecimal.ONE);
        } else {
            return roundedTo1.add(BigDecimal.ONE);
        }
    }

    private static BigDecimal getDecimalPart(BigDecimal number) {
        BigDecimal wholeNumbers = number.setScale(0, RoundingMode.DOWN);
        return number.subtract(wholeNumbers);
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
