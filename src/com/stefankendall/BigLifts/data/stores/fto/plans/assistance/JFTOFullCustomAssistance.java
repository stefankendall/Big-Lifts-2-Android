package com.stefankendall.BigLifts.data.stores.fto.plans.assistance;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.stefankendall.BigLifts.data.models.JModel;
import com.stefankendall.BigLifts.data.models.JSet;
import com.stefankendall.BigLifts.data.models.fto.JFTOFullCustomAssistanceWorkout;
import com.stefankendall.BigLifts.data.models.fto.JFTOWorkout;
import com.stefankendall.BigLifts.data.stores.fto.JFTOFullCustomAssistanceWorkoutStore;
import com.stefankendall.BigLifts.data.stores.fto.JFTOWorkoutStore;

import java.util.List;

public class JFTOFullCustomAssistance implements JFTOAssistanceProtocol {
    @Override
    public void setup() {
        for (JModel model : JFTOFullCustomAssistanceWorkoutStore.instance().findAll()) {
            JFTOFullCustomAssistanceWorkout fullCustomAssistanceWorkout = (JFTOFullCustomAssistanceWorkout) model;
            JFTOWorkout ftoWorkout = this.findFtoWorkoutFor(fullCustomAssistanceWorkout);
            CustomAssistanceHelper.addAssistanceToWorkout(ftoWorkout, fullCustomAssistanceWorkout.workout);
        }
    }

    private JFTOWorkout findFtoWorkoutFor(final JFTOFullCustomAssistanceWorkout fullCustomAssistanceWorkout) {
        Optional<? extends JModel> foundWorkout = Iterables.tryFind(JFTOWorkoutStore.instance().findAll(), new Predicate<JModel>() {
            @Override
            public boolean apply(JModel model) {
                JFTOWorkout jftoWorkout = (JFTOWorkout) model;
                List<JSet> sets = jftoWorkout.workout.sets;
                if (sets.size() == 0) {
                    return false;
                }
                return jftoWorkout.week == fullCustomAssistanceWorkout.week && fullCustomAssistanceWorkout.mainLift == sets.get(0).lift;
            }
        });

        return foundWorkout.isPresent() ? (JFTOWorkout) foundWorkout.get() : null;
    }

    @Override
    public void cycleChange() {
        new JFTOSimpleCustomAssistance().cycleChange();
    }
}
