package com.stefankendall.BigLiftsPro.data.models.fto;

import com.google.common.collect.Lists;
import com.stefankendall.BigLiftsPro.data.models.JModel;
import com.stefankendall.BigLiftsPro.data.models.JWorkout;

import java.util.List;

public class JFTOWorkout extends JModel {
    public JWorkout workout;
    public int week;
    public int order;
    public boolean done;
    public boolean deload;
    public boolean incrementAfterWeek;

    @Override
    public List<String> cascadeDeleteProperties() {
        return Lists.newArrayList("workout");
    }
}
