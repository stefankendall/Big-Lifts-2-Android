package com.stefankendall.BigLifts.data.stores.fto.plans;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.stefankendall.BigLifts.data.models.JLift;
import com.stefankendall.BigLifts.data.stores.JSetData;

import java.util.List;
import java.util.Map;

public class JFTOPowerliftingPlan implements JFTOPlan {
    @Override
    public Map<Integer, List<JSetData>> generate(JLift lift) {
        Map<Integer, List<JSetData>> weeksToWorkouts = Maps.newHashMap(new JFTOStandardPlan().generate(lift));
        List<JSetData> week1 = weeksToWorkouts.get(1);
        List<JSetData> week2 = weeksToWorkouts.get(2);
        weeksToWorkouts.put(2, week1);
        weeksToWorkouts.put(1, week2);

        JSetData lastSet = Iterables.getLast(week1);
        lastSet.amrap = false;
        return ImmutableMap.copyOf(weeksToWorkouts);
    }

    @Override
    public List<Integer> deloadWeeks() {
        return new JFTOStandardPlan().deloadWeeks();
    }

    @Override
    public List<Integer> incrementMaxesWeeks() {
        return new JFTOStandardPlan().incrementMaxesWeeks();
    }

    @Override
    public List<String> weekNames() {
        return Lists.newArrayList("3/3/3", "5/5/5", "5/3/1", "deload");
    }
}
