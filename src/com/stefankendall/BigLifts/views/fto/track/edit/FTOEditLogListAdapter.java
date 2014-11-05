package com.stefankendall.BigLifts.views.fto.track.edit;

import android.app.Activity;
import android.widget.ListAdapter;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.data.models.JWorkoutLog;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;

import java.util.List;

public class FTOEditLogListAdapter extends SimpleListAdapter {
    private final JWorkoutLog workoutLog;

    public FTOEditLogListAdapter(Activity activity, JWorkoutLog workoutLog) {
        this.workoutLog = workoutLog;
        this.items = buildItems();
        this.activity = activity;
    }

    @Override
    public List<CustomListItem> buildItems() {
        return Lists.newArrayList();
    }
}
