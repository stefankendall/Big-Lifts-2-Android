package com.stefankendall.BigLiftsPro.data.stores.fto.plans;

import com.google.common.collect.Lists;
import com.stefankendall.BigLiftsPro.data.models.JLift;
import com.stefankendall.BigLiftsPro.data.stores.JSetData;

import java.math.BigDecimal;
import java.util.List;

public class JFTODeload {
    public static List<JSetData> deloadLifts(JLift lift) {
        return Lists.newArrayList(
                JSetData.builder().withReps(5).withPercentage(new BigDecimal("40")).withLift(lift).withAmrap(false).withWarmup(false),
                JSetData.builder().withReps(5).withPercentage(new BigDecimal("50")).withLift(lift).withAmrap(false).withWarmup(false),
                JSetData.builder().withReps(5).withPercentage(new BigDecimal("60")).withLift(lift).withAmrap(false).withWarmup(false)
        );
    }
}
