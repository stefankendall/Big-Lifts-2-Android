package com.stefankendall.BigLifts.data.numbers;

import java.math.BigDecimal;

public class BigDecimals {
    public static BigDecimal parse(String s) {
        try {
            return new BigDecimal(s);
        } catch (Exception ignored) {
            return BigDecimal.ZERO;
        }
    }

    public static String print(BigDecimal number) {
        if (number == null) {
            return "";
        }

        return number.stripTrailingZeros().toPlainString();
    }
}
