package com.stefankendall.BigLiftsPro.allprograms.formulas;

import com.stefankendall.BigLiftsPro.BLTestCase;
import junit.framework.Assert;

import java.math.BigDecimal;

public class WilksCoefficientCalculatorTests extends BLTestCase {
    public void testCalculatesWilks() {
        Assert.assertEquals(
                WilksCoefficientCalculator.calculate(new BigDecimal("300"), new BigDecimal("170"), true, "kg"),
                new BigDecimal("162.943")
        );
        Assert.assertEquals(
                WilksCoefficientCalculator.calculate(new BigDecimal("300"), new BigDecimal("150"), true, "kg"),
                new BigDecimal("165.989")
        );
        Assert.assertEquals(
                WilksCoefficientCalculator.calculate(new BigDecimal("200"), new BigDecimal("150"), true, "kg"),
                new BigDecimal("110.659")
        );
    }

    public void testCalculatesWilksLbs() {
        Assert.assertEquals(
                WilksCoefficientCalculator.calculate(new BigDecimal("300"), new BigDecimal("170"), true, "lbs"),
                new BigDecimal("95.145")
        );
        Assert.assertEquals(
                WilksCoefficientCalculator.calculate(new BigDecimal("300"), new BigDecimal("150"), true, "lbs"),
                new BigDecimal("104.256")
        );
        Assert.assertEquals(
                WilksCoefficientCalculator.calculate(new BigDecimal("200"), new BigDecimal("150"), true, "lbs"),
                new BigDecimal("69.504")
        );
    }

    public void testCalculatesForFemale() {
        Assert.assertEquals(
                WilksCoefficientCalculator.calculate(new BigDecimal("300"), new BigDecimal("170"), false, "kg"),
                new BigDecimal("237.898")
        );
        Assert.assertEquals(
                WilksCoefficientCalculator.calculate(new BigDecimal("300"), new BigDecimal("150"), false, "kg"),
                new BigDecimal("230.845")
        );
        Assert.assertEquals(
                WilksCoefficientCalculator.calculate(new BigDecimal("200"), new BigDecimal("150"), false, "kg"),
                new BigDecimal("153.896")
        );
    }
}
