package com.stefankendall.BigLifts.views.fto.lift.individual;

import android.app.Activity;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.data.models.JSet;
import com.stefankendall.BigLifts.data.models.fto.JFTOWorkout;
import com.stefankendall.BigLifts.views.cells.SetCell;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.lists.HeaderListItem;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;

import java.util.Collection;
import java.util.List;

public class FTOIndividualWorkoutListAdapter extends SimpleListAdapter {
    protected JFTOWorkout jftoWorkout;

    public FTOIndividualWorkoutListAdapter(Activity context, JFTOWorkout jftoWorkout) {
        this.jftoWorkout = jftoWorkout;
        this.items = buildItems();
        this.activity = context;
    }

    @Override
    public List<? extends CustomListItem> buildItems() {
        List<CustomListItem> items = Lists.newArrayList();

        if (this.hasWarmup()) {
            items.add(new HeaderListItem("Warm-up"));
            items.addAll(this.itemsForSets(this.jftoWorkout.workout.warmupSets()));
        }
        if (this.hasWorkSets()) {
            items.add(new HeaderListItem("Workout"));
            items.addAll(this.itemsForSets(this.jftoWorkout.workout.workSets()));
        }
        if (this.hasAssistance()) {
            items.add(new HeaderListItem("Assistance"));
            items.addAll(this.itemsForSets(this.jftoWorkout.workout.assistanceSets()));
        }

        return items;
    }

    private Collection<CustomListItem> itemsForSets(List<JSet> sets) {
        List<CustomListItem> items = Lists.newArrayList();
        for (JSet set : sets) {
            items.add(new SetCell(set));
        }
        return items;
    }

    protected boolean hasWarmup() {
        return this.jftoWorkout.workout.warmupSets().size() > 0;
    }

    protected boolean hasAssistance() {
        return this.jftoWorkout.workout.assistanceSets().size() > 0;
    }

    protected boolean hasWorkSets() {
        return this.jftoWorkout.workout.workSets().size() > 0;
    }
}
