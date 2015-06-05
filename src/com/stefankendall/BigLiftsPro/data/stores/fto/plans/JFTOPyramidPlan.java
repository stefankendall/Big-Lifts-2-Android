package com.stefankendall.BigLiftsPro.data.stores.fto.plans;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.stefankendall.BigLiftsPro.data.models.JLift;
import com.stefankendall.BigLiftsPro.data.stores.JSetData;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class JFTOPyramidPlan implements JFTOPlan {
    @Override
    public Map<Integer, List<JSetData>> generate(JLift lift) {
        Map<Integer, List<JSetData>> pyramidSets = Maps.newHashMap();
        pyramidSets.put(1, Lists.newArrayList(
                JSetData.builder().withReps(5).withPercentage(new BigDecimal("75")).withLift(lift),
                JSetData.builder().withReps(5).withPercentage(new BigDecimal("65")).withLift(lift).withAmrap(true)
        ));

        pyramidSets.put(2, Lists.newArrayList(
                JSetData.builder().withReps(3).withPercentage(new BigDecimal("80")).withLift(lift),
                JSetData.builder().withReps(3).withPercentage(new BigDecimal("70")).withLift(lift).withAmrap(true)
        ));

        pyramidSets.put(3, Lists.newArrayList(
                JSetData.builder().withReps(3).withPercentage(new BigDecimal("85")).withLift(lift),
                JSetData.builder().withReps(3).withPercentage(new BigDecimal("75")).withLift(lift).withAmrap(true)
        ));

        Map<Integer, List<JSetData>> setsByWeek = Maps.newHashMap(new JFTOStandardPlan().generate(lift));
        for (int week = 1; week <= 3; week++) {
            List<JSetData> sets = setsByWeek.get(week);
            sets.addAll(pyramidSets.get(week));
        }
        return ImmutableMap.copyOf(setsByWeek);
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
        return new JFTOStandardPlan().weekNames();
    }
}
