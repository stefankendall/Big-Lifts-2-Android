package com.stefankendall.BigLifts.views.fto.lift.individual;

import android.app.Activity;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.data.models.JSet;
import com.stefankendall.BigLifts.data.models.fto.JFTOWorkout;
import com.stefankendall.BigLifts.views.cells.SetCell;
import com.stefankendall.BigLifts.views.cells.SetCellFactory;
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
    public List<CustomListItem> buildItems() {
        List<CustomListItem> items = Lists.newArrayList();

        if (this.shouldShowRepsToBeat()) {
            items.add(new FTOLiftWorkoutToolbar(this.jftoWorkout));
        }

        int row = 0;
        if (this.hasWarmup()) {
            items.add(new HeaderListItem("Warm-up"));
            items.addAll(this.itemsForSets(this.jftoWorkout.workout.warmupSets(), row));
            row += this.jftoWorkout.workout.warmupSets().size();
        }


        if (this.hasWorkSets()) {
            items.add(new HeaderListItem("Workout"));
            items.addAll(this.itemsForSets(this.jftoWorkout.workout.workSets(), row));
            row += this.jftoWorkout.workout.workSets().size();
        }

        if (this.hasAssistance()) {
            items.add(new HeaderListItem("Assistance"));
            items.addAll(this.itemsForSets(this.jftoWorkout.workout.assistanceSets(), row));
        }

        return items;
    }

    private boolean shouldShowRepsToBeat() {
        return this.jftoWorkout.workout.amrapSets().size() > 0;
    }

    private Collection<CustomListItem> itemsForSets(List<JSet> sets, int row) {
        List<CustomListItem> items = Lists.newArrayList();
        for (JSet set : sets) {
            SetChange setChange = FTOWorkoutChangeCache.instance().changeForWorkout(this.jftoWorkout, row++);
            items.add(SetCellFactory.create(set, setChange));
        }
        return items;
    }

    public int setNumberForPosition(int position) {
        if (position >= this.items.size()) {
            return -1;
        }

        CustomListItem item = this.items.get(position);
        if (!(item instanceof SetCell)) {
            return -1;
        }

        int headersPassed = 0;
        int ONE_FOR_TOOLBAR = this.shouldShowRepsToBeat() ? 1 : 0;

        if (this.hasWarmup()) {
            headersPassed++;
        }
        if (this.hasWorkSets() && position - headersPassed - ONE_FOR_TOOLBAR > this.jftoWorkout.workout.warmupSets().size() - 1) {
            headersPassed++;
        }
        if (this.hasAssistance() && position - headersPassed - ONE_FOR_TOOLBAR > this.jftoWorkout.workout.warmupSets().size() +
                this.jftoWorkout.workout.workSets().size() - 1) {
            headersPassed++;
        }


        return position - headersPassed - ONE_FOR_TOOLBAR;
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
