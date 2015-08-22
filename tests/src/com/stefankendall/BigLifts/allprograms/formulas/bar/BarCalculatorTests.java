package com.stefankendall.BigLifts.allprograms.formulas.bar;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.BLTestCase;
import com.stefankendall.BigLifts.data.models.JPlate;
import com.stefankendall.BigLifts.data.models.JSettings;
import com.stefankendall.BigLifts.data.stores.JBarStore;
import com.stefankendall.BigLifts.data.stores.JPlateStore;
import com.stefankendall.BigLifts.data.stores.JSettingsStore;
import junit.framework.Assert;

import java.math.BigDecimal;
import java.util.List;

public class BarCalculatorTests extends BLTestCase {
    protected BarCalculator calculator;

    public void setUp() throws Exception {
        super.setUp();
        this.calculator = new BarCalculator(JPlateStore.instance().findAll(), new BigDecimal("45"));
    }

    public void testMakesSimpleWeight() {
        List<BigDecimal> expected225 = Lists.newArrayList(new BigDecimal("45"), new BigDecimal("45"));
        assertCalculatesWeight(new BigDecimal("225"), expected225);

        List<BigDecimal> expected260 = Lists.newArrayList(
                new BigDecimal("45"),
                new BigDecimal("45"),
                new BigDecimal("10"),
                new BigDecimal("5"),
                new BigDecimal("2.5")
        );
        assertCalculatesWeight(new BigDecimal("260"), expected260);

        List<BigDecimal> expected180 = Lists.newArrayList(
                new BigDecimal("45"),
                new BigDecimal("10"),
                new BigDecimal("10"),
                new BigDecimal("2.5")
        );
        assertCalculatesWeight(new BigDecimal("180"), expected180);
    }

    public void testDoesntCrashWithNoPlates() {
        this.calculator = new BarCalculator(Lists.<JPlate>newArrayList(), new BigDecimal("45"));
        this.calculator.platesToMakeWeight(new BigDecimal("145"));
    }

    public void testMakesCorrectWeightWhenSmallerPlatesGetCloser() {
        setupNonLinearProgressionWeights();

        List<BigDecimal> expected = Lists.newArrayList(
                new BigDecimal("25"),
                new BigDecimal("25"),
                new BigDecimal("2.5"),
                new BigDecimal("1"),
                new BigDecimal("0.5")
        );
        this.calculator = new BarCalculator(JPlateStore.instance().findAll(), new BigDecimal("20"));
        assertCalculatesWeight(new BigDecimal("128"), expected);
    }

    private void setupNonLinearProgressionWeights() {
        JPlateStore.instance().empty();
        JPlateStore.instance().createPlate(new BigDecimal("25"), 6);
        JPlateStore.instance().createPlate(new BigDecimal("20"), 6);
        JPlateStore.instance().createPlate(new BigDecimal("15"), 6);
        JPlateStore.instance().createPlate(new BigDecimal("10"), 6);
        JPlateStore.instance().createPlate(new BigDecimal("5"), 6);
        JPlateStore.instance().createPlate(new BigDecimal("2.5"), 6);
        JPlateStore.instance().createPlate(new BigDecimal("1.25"), 6);
        JPlateStore.instance().createPlate(new BigDecimal("1"), 6);
        JPlateStore.instance().createPlate(new BigDecimal("0.5"), 6);
    }

    public void testManyWeightsDontCrash() {
        setupNonLinearProgressionWeights();
        this.calculator = new BarCalculator(JPlateStore.instance().findAll(), new BigDecimal("45"));
        this.calculator.platesToMakeWeight(new BigDecimal("200"));
        this.calculator.platesToMakeWeight(new BigDecimal("201"));
        this.calculator.platesToMakeWeight(new BigDecimal("201.5"));
        this.calculator.platesToMakeWeight(new BigDecimal("202"));
        this.calculator.platesToMakeWeight(new BigDecimal("202.5"));
        this.calculator.platesToMakeWeight(new BigDecimal("222.222"));
        this.calculator.platesToMakeWeight(new BigDecimal("200.251"));
        this.calculator.platesToMakeWeight(new BigDecimal("751.58"));
        this.calculator.platesToMakeWeight(new BigDecimal("752"));
        this.calculator.platesToMakeWeight(new BigDecimal("753"));
        this.calculator.platesToMakeWeight(new BigDecimal("754"));
        this.calculator.platesToMakeWeight(new BigDecimal("101"));
        this.calculator.platesToMakeWeight(new BigDecimal("3"));
    }

    public void testCopyPlatesReturnsNewPlates() {
        List<PlateRemaining> copy = this.calculator.copyPlates(JPlateStore.instance().findAll());
        Assert.assertEquals(copy.size(), JPlateStore.instance().count());
    }

    public void testCalculates70kg() {
        JSettings settings = (JSettings) JSettingsStore.instance().first();
        settings.units = "kg";
        JBarStore.instance().adjustForKg();
        JPlateStore.instance().adjustForKg();

        this.calculator = new BarCalculator(JPlateStore.instance().findAll(), new BigDecimal("20"));
        List<BigDecimal> expected70 = Lists.newArrayList(
                new BigDecimal("20"),
                new BigDecimal("5")
        );

        assertCalculatesWeight(new BigDecimal("70"), expected70);
    }

    public void testFindPlateClosestToWeightMatch() {
        PlateRemaining plate45 = new PlateRemaining(new BigDecimal("45"), 6);
        PlateRemaining plate35 = new PlateRemaining(new BigDecimal("35"), 6);
        List<PlateRemaining> plates = Lists.newArrayList(plate45, plate35);
        PlateRemaining p = this.calculator.findPlateClosestToWeight(new BigDecimal(100), plates);
        assertEquals(p, plate45);
    }

    public void testFindPlateClosestToWeightMatchBelowTop() {
        PlateRemaining plate45 = new PlateRemaining(new BigDecimal("45"), 6);
        PlateRemaining plate35 = new PlateRemaining(new BigDecimal("35"), 6);
        PlateRemaining plate25 = new PlateRemaining(new BigDecimal("25"), 6);
        List<PlateRemaining> plates = Lists.newArrayList(plate45, plate35, plate25);

        PlateRemaining p = this.calculator.findPlateClosestToWeight(new BigDecimal(60), plates);
        assertEquals(p, plate25);
    }

    public void testFindPlateClosestToWeightNoMatch() {
        PlateRemaining plate45 = new PlateRemaining(new BigDecimal(45), 6);
        PlateRemaining plate35 = new PlateRemaining(new BigDecimal(35), 6);
        List<PlateRemaining> plates = Lists.newArrayList(plate45, plate35);

        PlateRemaining p = this.calculator.findPlateClosestToWeight(new BigDecimal("25"), plates);
        Assert.assertNull(p);
    }

    public void testHandlesNullBarWeight() {
        this.calculator = new BarCalculator(JPlateStore.instance().findAll(), null);
        Assert.assertNotNull(this.calculator.platesToMakeWeight(new BigDecimal("60")));
    }

    private void assertCalculatesWeight(BigDecimal weightToMake, List<BigDecimal> expectedPlates) {
        List<BigDecimal> plates = this.calculator.platesToMakeWeight(weightToMake);
        Joiner commaJoiner = Joiner.on(",");
        Assert.assertEquals(commaJoiner.join(plates), commaJoiner.join(expectedPlates));
    }
}
