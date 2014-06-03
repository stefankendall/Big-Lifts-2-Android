package com.stefankendall.BigLifts.data.stores;

import com.stefankendall.BigLifts.BLTestCase;
import com.stefankendall.BigLifts.data.models.JSettings;
import junit.framework.Assert;

import java.math.BigDecimal;

public class JSettingsStoreTest extends BLTestCase {
    public void testLoadsDefaults() {
        JSettings settings = (JSettings) JSettingsStore.instance().first();
        Assert.assertNotNull(settings);
        Assert.assertEquals(settings.units, "lbs");
        Assert.assertEquals(settings.roundTo, new BigDecimal("5"));
    }

    public void testAdjustsRoundToWhenKgSelected() {
        JSettings settings = (JSettings) JSettingsStore.instance().first();
        settings.units = "kg";
        JSettingsStore.instance().adjustForKg();
        Assert.assertEquals(settings.roundTo, BigDecimal.ONE);
    }
}
