package com.stefankendall.BigLifts.data.stores.fto;

import com.stefankendall.BigLifts.BLTestCase;
import com.stefankendall.BigLifts.data.models.fto.JFTOLift;
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

    public void testIncrementsHonorsUnits(){
        Assert.fail();
    }

    public void testIncrementsLifts(){
        JFTOLift squat = (JFTOLift) JFTOLiftStore.instance().find("name", "Squat");
        BigDecimal weight = new BigDecimal(String.valueOf(squat.weight));
        JFTOLiftStore.instance().incrementLifts();
        Assert.assertEquals(squat.weight, weight.add(new BigDecimal("10")));
    }
}
