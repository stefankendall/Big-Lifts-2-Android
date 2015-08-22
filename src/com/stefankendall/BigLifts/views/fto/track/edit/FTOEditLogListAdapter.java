package com.stefankendall.BigLifts.views.fto.track.edit;

import android.support.v4.app.FragmentActivity;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.data.models.JWorkoutLog;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;

import java.util.List;

public class FTOEditLogListAdapter extends SimpleListAdapter {
    private final JWorkoutLog workoutLog;

    public FTOEditLogListAdapter(FragmentActivity activity, JWorkoutLog workoutLog) {
        this.workoutLog = workoutLog;
        this.activity = activity;
        this.items = buildItems();
    }

    @Override
    public List<CustomListItem> buildItems() {
        return Lists.<CustomListItem>newArrayList(
                new LogDateEditCell(this.workoutLog, this.activity)
        );
    }
}
