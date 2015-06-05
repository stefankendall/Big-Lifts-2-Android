package com.stefankendall.BigLiftsPro.data.stores.fto;

import com.stefankendall.BigLiftsPro.BLTestCase;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOCustomAssistanceLift;
import junit.framework.Assert;

public class JFTOCustomAssistanceLiftStoreTests extends BLTestCase {
    public void testSetsOrderOnCreate() {
        JFTOCustomAssistanceLift lift1 = (JFTOCustomAssistanceLift) JFTOCustomAssistanceLiftStore.instance().create();
        JFTOCustomAssistanceLift lift2 = (JFTOCustomAssistanceLift) JFTOCustomAssistanceLiftStore.instance().create();

        Assert.assertEquals(lift1.order, 1);
        Assert.assertEquals(lift2.order, 2);
    }
}
