package com.stefankendall.BigLifts.data.stores.fto.plans;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.stefankendall.BigLifts.data.models.JLift;
import com.stefankendall.BigLifts.data.stores.JSetData;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class JFTOFirstSetLastsMultipleSetsPlan implements JFTOPlan {
    public Map<Integer, List<JSetData>> generate(JLift lift) {
        Map<Integer, List<JSetData>> firstSetLastSets = Maps.newHashMap();
        firstSetLastSets.put(1, Lists.newArrayList(
                JSetData.builder().withReps(5).withMaxReps(8).withPercentage(new BigDecimal("65")).withLift(lift),
                JSetData.builder().withReps(5).withMaxReps(8).withPercentage(new BigDecimal("65")).withLift(lift),
                JSetData.builder().withReps(5).withMaxReps(8).withPercentage(new BigDecimal("65")).withLift(lift),
                JSetData.builder().withReps(5).withMaxReps(8).withPercentage(new BigDecimal("65")).withLift(lift),
                JSetData.builder().withReps(5).withMaxReps(8).withPercentage(new BigDecimal("65")).withLift(lift).withOptional(true),
                JSetData.builder().withReps(5).withMaxReps(8).withPercentage(new BigDecimal("65")).withLift(lift).withOptional(true)
        ));

        firstSetLastSets.put(2, Lists.newArrayList(
                JSetData.builder().withReps(5).withMaxReps(8).withPercentage(new BigDecimal("70")).withLift(lift),
                JSetData.builder().withReps(5).withMaxReps(8).withPercentage(new BigDecimal("70")).withLift(lift),
                JSetData.builder().withReps(5).withMaxReps(8).withPercentage(new BigDecimal("70")).withLift(lift),
                JSetData.builder().withReps(5).withMaxReps(8).withPercentage(new BigDecimal("70")).withLift(lift),
                JSetData.builder().withReps(5).withMaxReps(8).withPercentage(new BigDecimal("70")).withLift(lift).withOptional(true),
                JSetData.builder().withReps(5).withMaxReps(8).withPercentage(new BigDecimal("70")).withLift(lift).withOptional(true)
        ));

        firstSetLastSets.put(3, Lists.newArrayList(
                JSetData.builder().withReps(5).withMaxReps(8).withPercentage(new BigDecimal("75")).withLift(lift),
                JSetData.builder().withReps(5).withMaxReps(8).withPercentage(new BigDecimal("75")).withLift(lift),
                JSetData.builder().withReps(5).withMaxReps(8).withPercentage(new BigDecimal("75")).withLift(lift),
                JSetData.builder().withReps(5).withMaxReps(8).withPercentage(new BigDecimal("75")).withLift(lift),
                JSetData.builder().withReps(5).withMaxReps(8).withPercentage(new BigDecimal("75")).withLift(lift).withOptional(true),
                JSetData.builder().withReps(5).withMaxReps(8).withPercentage(new BigDecimal("75")).withLift(lift).withOptional(true)
        ));

        Map<Integer, List<JSetData>> setsByWeek = Maps.newHashMap(new JFTOStandardPlan().generate(lift));
        for (int week = 1; week <= 3; week++) {
            List<JSetData> sets = setsByWeek.get(week);
            sets.addAll(firstSetLastSets.get(week));
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
