package com.stefankendall.BigLifts.views.fto.plan.assistance.simplecustom.editset;

import com.stefankendall.BigLifts.BLTestCase;
import com.stefankendall.BigLifts.data.models.JLift;
import com.stefankendall.BigLifts.data.models.JSet;
import com.stefankendall.BigLifts.data.models.fto.JFTOCustomAssistanceLift;
import com.stefankendall.BigLifts.data.stores.JSetStore;
import com.stefankendall.BigLifts.data.stores.fto.JFTOCustomAssistanceLiftStore;
import com.stefankendall.BigLifts.data.stores.fto.JFTOLiftStore;
import com.stefankendall.BigLifts.data.stores.fto.JFTOSetStore;
import junit.framework.Assert;

import java.math.BigDecimal;

public class FTOAssistanceLiftSelectorTests extends BLTestCase {
    public void testReturnsFtoAndAssistanceLifts() {
        JFTOCustomAssistanceLiftStore.instance().create();
        FTOAssistanceLiftSelector selector = new FTOAssistanceLiftSelector(null);
        Assert.assertEquals(selector.options().size(), 5);
    }

    public void testSelectsFirstOptionsWhenLiftNull() {
        FTOAssistanceLiftSelector selector = new FTOAssistanceLiftSelector((JSet) JSetStore.instance().create());
        Assert.assertEquals(selector.defaultSelection(), 0);
    }

    public void testSelectsFtoLift() {
        JSet set = JSetStore.instance().create((JLift) JFTOLiftStore.instance().atIndex(1), new BigDecimal(100));
        FTOAssistanceLiftSelector selector = new FTOAssistanceLiftSelector(set);
        Assert.assertEquals(selector.defaultSelection(), 1);
    }

    public void testSelectsCustomLift() {
        JFTOCustomAssistanceLiftStore.instance().create();
        JFTOCustomAssistanceLift setLift = (JFTOCustomAssistanceLift) JFTOCustomAssistanceLiftStore.instance().create();
        JFTOCustomAssistanceLiftStore.instance().create();

        JSet set = JSetStore.instance().create(setLift, new BigDecimal(100));
        FTOAssistanceLiftSelector selector = new FTOAssistanceLiftSelector(set);
        Assert.assertEquals(selector.defaultSelection(), 5);
    }

    public void testCanSelectFtoLift() {
        JSet set = JSetStore.instance().create(null, new BigDecimal(100));
        FTOAssistanceLiftSelector selector = new FTOAssistanceLiftSelector(set);
        selector.valueChanged(1);
        Assert.assertEquals(set.lift, JFTOLiftStore.instance().atIndex(1));
    }

    public void testCanSelectCustomLift() {
        JFTOCustomAssistanceLiftStore.instance().create();
        JFTOCustomAssistanceLift setLift = (JFTOCustomAssistanceLift) JFTOCustomAssistanceLiftStore.instance().create();
        JFTOCustomAssistanceLiftStore.instance().create();

        JSet set = JSetStore.instance().create(null, new BigDecimal(100));
        FTOAssistanceLiftSelector selector = new FTOAssistanceLiftSelector(set);
        selector.valueChanged(5);

        Assert.assertEquals(set.lift, setLift);
    }
}
