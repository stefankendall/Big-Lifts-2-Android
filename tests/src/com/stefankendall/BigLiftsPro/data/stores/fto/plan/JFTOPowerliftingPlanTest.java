package com.stefankendall.BigLiftsPro.data.stores.fto.plan;

import com.google.common.collect.Iterables;
import com.stefankendall.BigLiftsPro.BLTestCase;
import com.stefankendall.BigLiftsPro.data.stores.JSetData;
import com.stefankendall.BigLiftsPro.data.stores.fto.plans.JFTOPowerliftingPlan;
import junit.framework.Assert;

import java.util.List;
import java.util.Map;

public class JFTOPowerliftingPlanTest extends BLTestCase {
    public void testGeneratesPowerliftingPlan() {
        Map<Integer, List<JSetData>> sets = new JFTOPowerliftingPlan().generate(null);
        JSetData data = Iterables.getLast(sets.get(1));
        Assert.assertEquals(data.reps, 3);
    }

    public void testWeek2HasNoAmrap() {
        Map<Integer, List<JSetData>> sets = new JFTOPowerliftingPlan().generate(null);
        JSetData data = Iterables.getLast(sets.get(2));
        Assert.assertFalse(data.amrap);
    }
}
