package com.stefankendall.BigLiftsPro.views.fto.plan.assistance.simplecustom.editset;

import com.stefankendall.BigLiftsPro.data.models.JSet;
import com.stefankendall.BigLiftsPro.data.models.JWorkout;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOSet;
import com.stefankendall.BigLiftsPro.data.stores.JSetStore;
import com.stefankendall.BigLiftsPro.data.stores.fto.JFTOSetStore;
import com.stefankendall.BigLiftsPro.views.cells.SwitchCell;
import com.stefankendall.BigLiftsPro.views.fto.barloading.FieldWatcher;

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
