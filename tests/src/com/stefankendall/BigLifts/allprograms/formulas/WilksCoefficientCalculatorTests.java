package com.stefankendall.BigLifts.allprograms.formulas;

import com.stefankendall.BigLifts.BLTestCase;

import java.math.BigDecimal;

public class WilksCoefficientCalculatorTests extends BLTestCase {
    public void testCalculatesWilks() {
        assertEquals(
                WilksCoefficientCalculator.calculate(new BigDecimal("300"), new BigDecimal("170"), true, "kg"),
                new BigDecimal("162.943")
        );
        assertEquals(
                WilksCoefficientCalculator.calculate(new BigDecimal("300"), new BigDecimal("150"), true, "kg"),
                new BigDecimal("165.989")
        );
        assertEquals(
                WilksCoefficientCalculator.calculate(new BigDecimal("200"), new BigDecimal("150"), true, "kg"),
                new BigDecimal("110.659")
        );
    }

    public void testCalculatesWilksLbs() {
        assertEquals(
                WilksCoefficientCalculator.calculate(new BigDecimal("300"), new BigDecimal("170"), true, "lbs"),
                new BigDecimal("95.145")
        );
        assertEquals(
                WilksCoefficientCalculator.calculate(new BigDecimal("300"), new BigDecimal("150"), true, "lbs"),
                new BigDecimal("104.256")
        );
        assertEquals(
                WilksCoefficientCalculator.calculate(new BigDecimal("200"), new BigDecimal("150"), true, "lbs"),
                new BigDecimal("69.504")
        );
    }

    public void testCalculatesForFemale() {
        assertEquals(
                WilksCoefficientCalculator.calculate(new BigDecimal("300"), new BigDecimal("170"), false, "kg"),
                new BigDecimal("237.898")
        );
        assertEquals(
                WilksCoefficientCalculator.calculate(new BigDecimal("300"), new BigDecimal("150"), false, "kg"),
                new BigDecimal("230.845")
        );
        assertEquals(
                WilksCoefficientCalculator.calculate(new BigDecimal("200"), new BigDecimal("150"), false, "kg"),
                new BigDecimal("153.896")
        );
    }
}
