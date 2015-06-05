package com.stefankendall.BigLiftsPro.data.stores.fto.plans;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.stefankendall.BigLiftsPro.data.models.JLift;
import com.stefankendall.BigLiftsPro.data.stores.JSetData;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class JFTOAdvancedPlan implements JFTOPlan {
    @Override
    public Map<Integer, List<JSetData>> generate(JLift lift) {
        return ImmutableMap.<Integer,List<JSetData>>builder()
                .put(1, Lists.newArrayList(
                        JSetData.builder().withReps(5).withPercentage(new BigDecimal("75")).withLift(lift),
                        JSetData.builder().withReps(5).withPercentage(new BigDecimal("75")).withLift(lift),
                        JSetData.builder().withReps(5).withPercentage(new BigDecimal("75")).withLift(lift),
                        JSetData.builder().withReps(5).withPercentage(new BigDecimal("75")).withLift(lift),
                        JSetData.builder().withReps(5).withPercentage(new BigDecimal("75")).withLift(lift),
                        JSetData.builder().withReps(5).withPercentage(new BigDecimal("75")).withLift(lift)
                ))
                .put(2, Lists.newArrayList(
                        JSetData.builder().withReps(3).withPercentage(new BigDecimal("85")).withLift(lift),
                        JSetData.builder().withReps(3).withPercentage(new BigDecimal("85")).withLift(lift),
                        JSetData.builder().withReps(3).withPercentage(new BigDecimal("85")).withLift(lift),
                        JSetData.builder().withReps(3).withPercentage(new BigDecimal("85")).withLift(lift),
                        JSetData.builder().withReps(3).withPercentage(new BigDecimal("85")).withLift(lift),
                        JSetData.builder().withReps(3).withPercentage(new BigDecimal("85")).withLift(lift)
                ))
                .put(3, Lists.newArrayList(
                        JSetData.builder().withReps(1).withPercentage(new BigDecimal("95")).withLift(lift),
                        JSetData.builder().withReps(1).withPercentage(new BigDecimal("95")).withLift(lift),
                        JSetData.builder().withReps(1).withPercentage(new BigDecimal("95")).withLift(lift),
                        JSetData.builder().withReps(1).withPercentage(new BigDecimal("95")).withLift(lift),
                        JSetData.builder().withReps(1).withPercentage(new BigDecimal("95")).withLift(lift),
                        JSetData.builder().withReps(1).withPercentage(new BigDecimal("95")).withLift(lift)
                ))
                .put(4, Lists.newArrayList(
                        JSetData.builder().withReps(5).withPercentage(new BigDecimal("65")).withLift(lift),
                        JSetData.builder().withReps(5).withPercentage(new BigDecimal("65")).withLift(lift),
                        JSetData.builder().withReps(5).withPercentage(new BigDecimal("65")).withLift(lift)
                ))
                .build();
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
        return Lists.newArrayList("Week 1", "Week 2", "Week 3", "Deload (opt.)");
    }
}
