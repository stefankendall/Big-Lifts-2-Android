package com.stefankendall.BigLiftsPro.views.fto.lift.individual;

import android.support.v4.app.FragmentActivity;
import com.google.common.collect.Lists;
import com.stefankendall.BigLiftsPro.data.models.JSet;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOWorkout;
import com.stefankendall.BigLiftsPro.views.cells.SetCell;
import com.stefankendall.BigLiftsPro.views.cells.SetCellFactory;
import com.stefankendall.BigLiftsPro.views.fto.lift.individual.timer.RestCountdownCell;
import com.stefankendall.BigLiftsPro.views.fto.lift.individual.timer.RestTimer;
import com.stefankendall.BigLiftsPro.views.lists.CustomListItem;
import com.stefankendall.BigLiftsPro.views.lists.HeaderListItem;
import com.stefankendall.BigLiftsPro.views.lists.SimpleListAdapter;

import java.util.Collection;
import java.util.List;

public class FTOIndividualWorkoutListAdapter extends SimpleListAdapter {
    protected JFTOWorkout jftoWorkout;

    public FTOIndividualWorkoutListAdapter(FragmentActivity context, JFTOWorkout jftoWorkout) {
        this.jftoWorkout = jftoWorkout;

        if (this.jftoWorkout == null) {
            throw new IllegalStateException("JFTOWorkout is null.");
        }
        if (this.jftoWorkout.workout == null) {
            throw new IllegalStateException("Workout is null.");
        }

        this.items = buildItems();
        this.activity = context;
    }

    @Override
    public List<CustomListItem> buildItems() {
        List<CustomListItem> items = Lists.newArrayList();

        if (RestTimer.instance().secondsLeft() > 0) {
            items.add(new RestCountdownCell());
        }

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

    protected boolean shouldShowRepsToBeat() {
        List<JSet> amrapSets = this.jftoWorkout.workout.amrapSets();
        return amrapSets.size() > 0;
    }

    private Collection<CustomListItem> itemsForSets(List<JSet> sets, int row) {
        List<CustomListItem> items = Lists.newArrayList();
        for (JSet set : sets) {
            boolean isComplete = FTOWorkoutChangeCache.instance().isComplete(row);
            SetChange setChange = FTOWorkoutChangeCache.instance().changeForWorkout(this.jftoWorkout, row++);
            SetCell setCell = (SetCell) SetCellFactory.create(set, setChange);
            setCell.setComplete(isComplete);
            items.add(setCell);
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
        int ONE_FOR_TIMER = (this.items.size() > 0 && this.items.get(0) instanceof RestCountdownCell) ? 1 : 0;

        if (this.hasWarmup()) {
            headersPassed++;
        }
        if (this.hasWorkSets() && position - headersPassed - ONE_FOR_TOOLBAR - ONE_FOR_TIMER > this.jftoWorkout.workout.warmupSets().size() - 1) {
            headersPassed++;
        }
        if (this.hasAssistance() && position - headersPassed - ONE_FOR_TOOLBAR - ONE_FOR_TIMER > this.jftoWorkout.workout.warmupSets().size() +
                this.jftoWorkout.workout.workSets().size() - 1) {
            headersPassed++;
        }

        return position - headersPassed - ONE_FOR_TOOLBAR - ONE_FOR_TIMER;
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
