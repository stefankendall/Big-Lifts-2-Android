package com.stefankendall.BigLiftsPro.data.models;

import com.stefankendall.BigLiftsPro.BLTestCase;
import com.stefankendall.BigLiftsPro.data.stores.JLiftStore;
import com.stefankendall.BigLiftsPro.data.stores.JSetStore;
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
