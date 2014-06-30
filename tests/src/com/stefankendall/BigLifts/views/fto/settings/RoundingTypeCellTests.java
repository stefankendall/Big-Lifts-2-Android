package com.stefankendall.BigLifts.views.fto.settings;

import com.stefankendall.BigLifts.BLTestCase;
import com.stefankendall.BigLifts.data.models.JSettings;
import com.stefankendall.BigLifts.data.stores.JSettingsStore;
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
