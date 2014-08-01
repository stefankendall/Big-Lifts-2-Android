package com.stefankendall.BigLifts.views.fto.lift.individual;

import android.app.Activity;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.data.models.fto.JFTOWorkout;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.lists.HeaderListItem;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;

import java.util.ArrayList;
import java.util.List;

public class FTOIndividualWorkoutListAdapter extends SimpleListAdapter {
    protected JFTOWorkout jftoWorkout;

    public FTOIndividualWorkoutListAdapter(Activity context, JFTOWorkout jftoWorkout) {
        super(context);
        this.jftoWorkout = jftoWorkout;
    }

    @Override
    public List<? extends CustomListItem> buildItems() {
        List<CustomListItem> items = Lists.newArrayList();

        if (this.hasWarmup()) {
            items.add(new HeaderListItem("Warm-up"));
        }
        if (this.hasWorkSets()) {
            items.add(new HeaderListItem("Workout"));
        }
        if (this.hasAssistance()) {
            items.add(new HeaderListItem("Assistance"));
        }

        return items;
    }

    protected boolean hasWarmup() {
        return this.jftoWorkout.workout.warmupSets().size() > 0;
    }

    protected boolean hasAssistance() {
        return this.jftoWorkout.workout.warmupSets().size() > 0;
    }

    protected boolean hasWorkSets() {
        return this.jftoWorkout.workout.workSets().size() > 0;
    }
}
