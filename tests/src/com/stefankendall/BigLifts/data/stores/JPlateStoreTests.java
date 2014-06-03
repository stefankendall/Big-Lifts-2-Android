package com.stefankendall.BigLifts.data.stores;

import com.stefankendall.BigLifts.BLTestCase;
import com.stefankendall.BigLifts.data.models.JPlate;
import com.stefankendall.BigLifts.data.models.JSettings;
import junit.framework.Assert;

import java.math.BigDecimal;
import java.util.List;

public class JPlateStoreTests extends BLTestCase {
    public void testHasDefaultPlates() {
        Assert.assertEquals(JPlateStore.instance().count(), 6);
        JPlate plate = (JPlate) JPlateStore.instance().first();
        Assert.assertEquals(plate.count, 6);
        Assert.assertEquals(plate.weight, new BigDecimal("45"));
    }

    public void testCanFindPlatesSortedByWeight() {
        List<JPlate> allPlates = JPlateStore.instance().findAll();
        Assert.assertTrue(allPlates.size() > 0);
        for (int i = 0; i < allPlates.size() - 1; i++) {
            JPlate current = allPlates.get(i);
            JPlate next = allPlates.get(i + 1);
            Assert.assertTrue(current.weight.compareTo(next.weight) > 0);
        }
    }

    public void testAdjustsWhenUnitsChange() {
        JSettings settings = (JSettings) JSettingsStore.instance().first();
        settings.setUnits("kg");
        JPlate plate = (JPlate) JPlateStore.instance().first();
        Assert.assertEquals(plate.weight, new BigDecimal("20"));
    }
}
