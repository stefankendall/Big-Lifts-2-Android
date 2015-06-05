package com.stefankendall.BigLiftsPro.data;

import com.stefankendall.BigLiftsPro.BLTestCase;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOLift;
import com.stefankendall.BigLiftsPro.data.stores.fto.JFTOLiftStore;
import junit.framework.Assert;

public class ObjectHelperTests extends BLTestCase {
    public void testGetsIntegerFromObject() {
        JFTOLift ftoLift = (JFTOLift) JFTOLiftStore.instance().create();
        ftoLift.order = 9;
        Assert.assertEquals(ObjectHelper.getProperty(ftoLift, "order"), 9);
    }
}
