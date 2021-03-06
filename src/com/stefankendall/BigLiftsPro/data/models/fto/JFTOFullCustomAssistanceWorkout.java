package com.stefankendall.BigLiftsPro.data.models.fto;

import com.google.common.collect.Lists;
import com.stefankendall.BigLiftsPro.data.models.JModel;
import com.stefankendall.BigLiftsPro.data.models.JWorkout;

import java.util.List;

public class JFTOFullCustomAssistanceWorkout extends JModel {
    public JFTOLift mainLift;
    public int week;
    public JWorkout workout;

    @Override
    public List<String> cascadeDeleteProperties() {
        return Lists.newArrayList("workout");
    }
}
