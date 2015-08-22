package com.stefankendall.BigLifts.data.stores.fto.plans;

import com.stefankendall.BigLifts.data.models.JLift;
import com.stefankendall.BigLifts.data.stores.JSetData;

import java.util.List;
import java.util.Map;

public interface JFTOPlan {
    Map<Integer, List<JSetData>> generate(JLift lift);

    List<Integer> deloadWeeks();

    List<Integer> incrementMaxesWeeks();

    List<String> weekNames();
}
