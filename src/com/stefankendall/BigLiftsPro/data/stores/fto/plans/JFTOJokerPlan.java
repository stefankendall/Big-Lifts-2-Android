package com.stefankendall.BigLiftsPro.data.stores.fto.plans;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.stefankendall.BigLiftsPro.data.models.JLift;
import com.stefankendall.BigLiftsPro.data.stores.JSetData;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class JFTOJokerPlan implements JFTOPlan {
    @Override
    public Map<Integer, List<JSetData>> generate(JLift lift) {
        Map<Integer, List<JSetData>> jokerSets = Maps.newHashMap();
        jokerSets.put(1, Lists.newArrayList(
                //from 85
                JSetData.builder().withReps(5).withPercentage(new BigDecimal("93.5")).withLift(lift),
                JSetData.builder().withReps(5).withPercentage(new BigDecimal("98.18")).withLift(lift),
                JSetData.builder().withReps(5).withPercentage(new BigDecimal("103.08")).withLift(lift)
        ));

        jokerSets.put(2, Lists.newArrayList(
                //from 90
                JSetData.builder().withReps(3).withPercentage(new BigDecimal("99")).withLift(lift),
                JSetData.builder().withReps(3).withPercentage(new BigDecimal("103.95")).withLift(lift),
                JSetData.builder().withReps(3).withPercentage(new BigDecimal("109.15")).withLift(lift)
        ));

        jokerSets.put(3, Lists.newArrayList(
                //from 95
                JSetData.builder().withReps(1).withPercentage(new BigDecimal("104.5")).withLift(lift),
                JSetData.builder().withReps(1).withPercentage(new BigDecimal("109.73")).withLift(lift),
                JSetData.builder().withReps(1).withPercentage(new BigDecimal("115.21")).withLift(lift)
        ));

        Map<Integer, List<JSetData>> setsByWeek = Maps.newHashMap(new JFTOStandardPlan().generate(lift));
        for (int week = 1; week <= 3; week++) {
            List<JSetData> sets = setsByWeek.get(week);
            sets.addAll(jokerSets.get(week));
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
