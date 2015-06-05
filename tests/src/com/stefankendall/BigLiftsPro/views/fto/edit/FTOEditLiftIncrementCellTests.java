package com.stefankendall.BigLiftsPro.views.fto.edit;

import com.stefankendall.BigLiftsPro.BLTestCase;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOLift;
import com.stefankendall.BigLiftsPro.data.stores.fto.JFTOLiftStore;
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
