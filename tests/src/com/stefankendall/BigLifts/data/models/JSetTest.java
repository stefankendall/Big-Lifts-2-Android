package com.stefankendall.BigLifts.data.models;

import com.stefankendall.BigLifts.BLTestCase;
import com.stefankendall.BigLifts.data.stores.JLiftStore;
import com.stefankendall.BigLifts.data.stores.JSetStore;
import junit.framework.Assert;

import java.math.BigDecimal;

public class JSetTest extends BLTestCase {

    public void testHandlesNullPercentages() {
        JSet set = (JSet) JSetStore.instance().create();
        set.percentage = null;
        set.lift = (JLift) JLiftStore.instance().create();
        set.lift.weight = new BigDecimal("100");

        Assert.assertEquals(set.effectiveWeight(), BigDecimal.ZERO);
    }
}
