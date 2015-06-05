package com.stefankendall.BigLiftsPro.views.fto.settings;

import com.stefankendall.BigLiftsPro.BLTestCase;
import com.stefankendall.BigLiftsPro.data.models.JSettings;
import com.stefankendall.BigLiftsPro.data.stores.JSettingsStore;
import junit.framework.Assert;

public class RoundingTypeCellTests extends BLTestCase {
    public void testCanChangeRoundingType() {
        RoundingTypeCell cell = new RoundingTypeCell();
        cell.valueChanged(2);
        JSettings settings = (JSettings) JSettingsStore.instance().first();
        Assert.assertEquals(settings.roundingType, JSettings.ROUNDING_TYPE_UP);

        cell.valueChanged(1);
        Assert.assertEquals(settings.roundingType, JSettings.ROUNDING_TYPE_NORMAL);
    }
}
