package com.stefankendall.BigLifts.data.stores.fto.plans;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.data.models.JLift;
import com.stefankendall.BigLifts.data.stores.JSetData;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class JFTOFivesProgression implements JFTOPlan {
    @Override
    public Map<Integer, List<JSetData>> generate(JLift lift) {
        return ImmutableMap.<Integer,List<JSetData>>builder()
                .put(1, Lists.newArrayList(
                        JSetData.builder().withReps(5).withPercentage(new BigDecimal("40")).withLift(lift).withWarmup(true),
                        JSetData.builder().withReps(5).withPercentage(new BigDecimal("50")).withLift(lift).withWarmup(true),
                        JSetData.builder().withReps(3).withPercentage(new BigDecimal("60")).withLift(lift).withWarmup(true),
                        JSetData.builder().withReps(5).withPercentage(new BigDecimal("65")).withLift(lift),
                        JSetData.builder().withReps(5).withPercentage(new BigDecimal("75")).withLift(lift),
                        JSetData.builder().withReps(5).withPercentage(new BigDecimal("85")).withLift(lift)
                ))
                .put(2, Lists.newArrayList(
                        JSetData.builder().withReps(5).withPercentage(new BigDecimal("40")).withLift(lift).withWarmup(true),
                        JSetData.builder().withReps(5).withPercentage(new BigDecimal("50")).withLift(lift).withWarmup(true),
                        JSetData.builder().withReps(3).withPercentage(new BigDecimal("60")).withLift(lift).withWarmup(true),
                        JSetData.builder().withReps(5).withPercentage(new BigDecimal("70")).withLift(lift),
                        JSetData.builder().withReps(5).withPercentage(new BigDecimal("80")).withLift(lift),
                        JSetData.builder().withReps(5).withPercentage(new BigDecimal("90")).withLift(lift)
                ))
                .put(3, Lists.newArrayList(
                        JSetData.builder().withReps(5).withPercentage(new BigDecimal("40")).withLift(lift).withWarmup(true),
                        JSetData.builder().withReps(5).withPercentage(new BigDecimal("50")).withLift(lift).withWarmup(true),
                        JSetData.builder().withReps(3).withPercentage(new BigDecimal("60")).withLift(lift).withWarmup(true),
                        JSetData.builder().withReps(5).withPercentage(new BigDecimal("75")).withLift(lift),
                        JSetData.builder().withReps(5).withPercentage(new BigDecimal("85")).withLift(lift),
                        JSetData.builder().withReps(5).withPercentage(new BigDecimal("95")).withLift(lift)
                ))
                .put(4, JFTODeload.deloadLifts(lift))
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
        return new JFTOStandardPlan().weekNames();
    }
}
