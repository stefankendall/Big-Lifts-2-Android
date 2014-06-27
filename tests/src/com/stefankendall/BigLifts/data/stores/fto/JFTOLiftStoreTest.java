package com.stefankendall.BigLifts.data.stores.fto;

import com.stefankendall.BigLifts.BLTestCase;
import com.stefankendall.BigLifts.data.models.JSettings;
import com.stefankendall.BigLifts.data.models.fto.JFTOLift;
import com.stefankendall.BigLifts.data.stores.JSettingsStore;
import junit.framework.Assert;

import java.math.BigDecimal;

public class JFTOLiftStoreTest extends BLTestCase {
    public void testSetsIncrement() {
        JFTOLift lift = (JFTOLift) JFTOLiftStore.instance().first();
        Assert.assertNotNull(lift.increment);
    }

    public void testOrdersNewLifts() {
        JFTOLift newLift = (JFTOLift) JFTOLiftStore.instance().create();
        Assert.assertEquals(newLift.order, 4);
    }

    public void testIncrementsHonorsUnits() {
        JSettings settings = (JSettings) JSettingsStore.instance().first();
        settings.setUnits("kg");
        JFTOLift press = (JFTOLift) JFTOLiftStore.instance().find("name", "Press");
        Assert.assertEquals(press.increment, new BigDecimal("2"));
        settings.setUnits("lbs");
        Assert.assertEquals(press.increment, new BigDecimal("5"));
    }

    public void testIncrementsLifts() {
        JFTOLift squat = (JFTOLift) JFTOLiftStore.instance().find("name", "Squat");
        BigDecimal weight = new BigDecimal(String.valueOf(squat.weight));
        JFTOLiftStore.instance().incrementLifts();
        Assert.assertEquals(squat.weight, weight.add(new BigDecimal("10")));
    }
}
