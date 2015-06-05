package com.stefankendall.BigLiftsPro.data.stores;

import com.stefankendall.BigLiftsPro.BLTestCase;
import com.stefankendall.BigLiftsPro.data.models.JBar;
import com.stefankendall.BigLiftsPro.data.models.JSettings;
import junit.framework.Assert;

import java.math.BigDecimal;

public class JBarStoreTest extends BLTestCase {
    public void testBarWeightHasDefaults() {
        JBar bar = (JBar) JBarStore.instance().first();
        Assert.assertEquals(bar.weight, new BigDecimal("45"));
    }

    public void testBarWeightAdjustsWithUnits() {
        JSettings settings = (JSettings) JSettingsStore.instance().first();
        settings.setUnits("kg");
        JBar bar = (JBar) JBarStore.instance().first();
        Assert.assertEquals(bar.weight, new BigDecimal("20"));
    }
}
