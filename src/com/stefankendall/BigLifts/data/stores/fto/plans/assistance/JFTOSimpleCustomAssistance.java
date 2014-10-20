package com.stefankendall.BigLifts.data.stores.fto.plans.assistance;

import com.stefankendall.BigLifts.data.models.JModel;
import com.stefankendall.BigLifts.data.models.fto.JFTOCustomAssistanceLift;
import com.stefankendall.BigLifts.data.models.fto.JFTOCustomAssistanceWorkout;
import com.stefankendall.BigLifts.data.models.fto.JFTOLift;
import com.stefankendall.BigLifts.data.models.fto.JFTOWorkout;
import com.stefankendall.BigLifts.data.stores.fto.JFTOCustomAssistanceLiftStore;
import com.stefankendall.BigLifts.data.stores.fto.JFTOCustomAssistanceWorkoutStore;
import com.stefankendall.BigLifts.data.stores.fto.JFTOWorkoutStore;

public class JFTOSimpleCustomAssistance implements JFTOAssistanceProtocol {
    @Override
    public void setup() {
        for (JModel customWorkoutModel : JFTOCustomAssistanceWorkoutStore.instance().findAll()) {
            JFTOCustomAssistanceWorkout customAssistanceWorkout = (JFTOCustomAssistanceWorkout) customWorkoutModel;
            for (JModel ftoWorkoutModel : JFTOWorkoutStore.instance().findAll()) {
                JFTOWorkout jftoWorkout = (JFTOWorkout) ftoWorkoutModel;
                if (jftoWorkout.workout.sets.size() > 0) {
                    JFTOLift lift = (JFTOLift) jftoWorkout.workout.sets.get(0).lift;
                    if(customAssistanceWorkout.mainLift == lift){
                        CustomAssistanceHelper.addAssistanceToWorkout(jftoWorkout, customAssistanceWorkout.workout);
                    }
                }
            }
        }
    }

    @Override
    public void cycleChange() {
        for (JModel model : JFTOCustomAssistanceLiftStore.instance().findAll()) {
            JFTOCustomAssistanceLift lift = (JFTOCustomAssistanceLift) model;
            if (lift.weight != null && lift.increment != null) {
                lift.weight = lift.weight.add(lift.increment);
            }
        }
    }
}
