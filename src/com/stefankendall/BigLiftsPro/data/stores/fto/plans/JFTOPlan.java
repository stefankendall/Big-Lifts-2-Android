package com.stefankendall.BigLiftsPro.data.stores.fto.plans;

import com.stefankendall.BigLiftsPro.data.models.JLift;
import com.stefankendall.BigLiftsPro.data.stores.JSetData;

import java.util.List;
import java.util.Map;

public interface JFTOPlan {
    Map<Integer, List<JSetData>> generate(JLift lift);

    List<Integer> deloadWeeks();

    List<Integer> incrementMaxesWeeks();

    List<String> weekNames();
}
