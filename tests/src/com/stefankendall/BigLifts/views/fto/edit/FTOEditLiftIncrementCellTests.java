package com.stefankendall.BigLifts.views.fto.edit;

import com.stefankendall.BigLifts.BLTestCase;
import com.stefankendall.BigLifts.data.models.fto.JFTOLift;
import com.stefankendall.BigLifts.data.stores.fto.JFTOLiftStore;
import junit.framework.Assert;

import java.math.BigDecimal;

public class FTOEditLiftIncrementCellTests extends BLTestCase {
    public void testHandlesEmptyString() {
        JFTOLift lift = (JFTOLift) JFTOLiftStore.instance().create();
        FTOEditLiftIncrementCell cell = new FTOEditLiftIncrementCell(lift);
        cell.updateIncrement("");
        Assert.assertEquals(lift.increment, new BigDecimal("0"));
    }
}
