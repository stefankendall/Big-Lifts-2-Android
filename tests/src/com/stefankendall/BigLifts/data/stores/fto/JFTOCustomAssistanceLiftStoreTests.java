package com.stefankendall.BigLifts.data.stores.fto;

import com.stefankendall.BigLifts.BLTestCase;
import com.stefankendall.BigLifts.data.models.fto.JFTOCustomAssistanceLift;
import junit.framework.Assert;

public class JFTOCustomAssistanceLiftStoreTests extends BLTestCase {
    public void testSetsOrderOnCreate() {
        JFTOCustomAssistanceLift lift1 = (JFTOCustomAssistanceLift) JFTOCustomAssistanceLiftStore.instance().create();
        JFTOCustomAssistanceLift lift2 = (JFTOCustomAssistanceLift) JFTOCustomAssistanceLiftStore.instance().create();

        Assert.assertEquals(lift1.order, 1);
        Assert.assertEquals(lift2.order, 2);
    }
}
