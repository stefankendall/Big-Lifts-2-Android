package com.stefankendall.BigLifts.data;

import com.stefankendall.BigLifts.BLTestCase;
import com.stefankendall.BigLifts.data.models.fto.JFTOLift;
import com.stefankendall.BigLifts.data.stores.fto.JFTOLiftStore;

public class ObjectHelperTests extends BLTestCase {
    public void testGetsIntegerFromObject() {
        JFTOLift ftoLift = (JFTOLift) JFTOLiftStore.instance().create();
        ftoLift.order = 9;
        assertEquals(ObjectHelper.getProperty(ftoLift, "order"), 9);
    }
}
