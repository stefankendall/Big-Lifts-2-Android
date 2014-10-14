package com.stefankendall.BigLifts.views.fto.plan.assistance.simplecustom.editset;

import com.stefankendall.BigLifts.BLTestCase;
import com.stefankendall.BigLifts.data.stores.fto.JFTOCustomAssistanceLiftStore;
import junit.framework.Assert;

public class FTOAssistanceLiftSelectorTests extends BLTestCase {
    public void testReturnsFtoAndAssistanceLifts() {
        JFTOCustomAssistanceLiftStore.instance().create();
        FTOAssistanceLiftSelector selector = new FTOAssistanceLiftSelector(null);
        Assert.assertEquals(selector.options().size(), 5);
    }
}
