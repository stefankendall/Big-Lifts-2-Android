package com.stefankendall.BigLifts.views.fto.plan.assistance.simplecustom.editset;

import com.stefankendall.BigLifts.data.models.JSet;
import com.stefankendall.BigLifts.data.models.JWorkout;
import com.stefankendall.BigLifts.data.models.fto.JFTOSet;
import com.stefankendall.BigLifts.data.stores.JSetStore;
import com.stefankendall.BigLifts.data.stores.fto.JFTOSetStore;
import com.stefankendall.BigLifts.views.cells.SwitchCell;
import com.stefankendall.BigLifts.views.fto.barloading.FieldWatcher;

public class UseTrainingMaxCell extends SwitchCell {
    private final JWorkout workout;
    private final FieldWatcher watcher;
    private JSet set;

    public UseTrainingMaxCell(FieldWatcher watcher, JWorkout workout, JSet set) {
        this.watcher = watcher;
        this.workout = workout;
        this.set = set;
    }

    @Override
    protected String label() {
        return "Use training max?";
    }

    @Override
    protected boolean defaultState() {
        return this.set instanceof JFTOSet;
    }

    @Override
    protected void valueChanged(boolean useTrainingMax) {
        if (useTrainingMax != (set instanceof JFTOSet)) {
            JSet newSet;
            if (useTrainingMax) {
                newSet = JFTOSetStore.instance().createFromSet(this.set);
            } else {
                newSet = JSetStore.instance().createFromSet(this.set);
            }

            int index = workout.sets.indexOf(this.set);
            workout.sets.add(index, newSet);
            workout.removeSet(this.set);
            this.watcher.fieldChanged();
        }
    }
}
