package com.stefankendall.BigLifts.views.fto.plan.assistance.simplecustom;

import android.app.Activity;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.data.models.JWorkout;
import com.stefankendall.BigLifts.views.fto.barloading.AddCell;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;

import java.util.List;

public class FTOSimpleCustomEditWorkoutListAdapter extends SimpleListAdapter {
    private final JWorkout workout;

    public FTOSimpleCustomEditWorkoutListAdapter(Activity activity, JWorkout workout) {
        this.workout = workout;
        this.items = buildItems();
        this.activity = activity;
    }

    @Override
    public List<CustomListItem> buildItems() {
        return Lists.<CustomListItem>newArrayList(
                new AddCell("Add Set...")
        );
    }
}
