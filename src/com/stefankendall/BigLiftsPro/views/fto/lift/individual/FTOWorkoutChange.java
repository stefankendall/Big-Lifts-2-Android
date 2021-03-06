package com.stefankendall.BigLiftsPro.views.fto.lift.individual;

import com.google.common.collect.Maps;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOWorkout;

import java.util.Map;

public class FTOWorkoutChange {
    public JFTOWorkout workout;
    public Map<Integer, SetChange> changesBySet;

    public FTOWorkoutChange(JFTOWorkout workout) {
        this.workout = workout;
        this.changesBySet = Maps.newHashMap();
    }
}
