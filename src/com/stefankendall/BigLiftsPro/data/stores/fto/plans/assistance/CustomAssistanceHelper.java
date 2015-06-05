package com.stefankendall.BigLiftsPro.data.stores.fto.plans.assistance;

import com.stefankendall.BigLiftsPro.data.models.JModel;
import com.stefankendall.BigLiftsPro.data.models.JSet;
import com.stefankendall.BigLiftsPro.data.models.JWorkout;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOWorkout;
import com.stefankendall.BigLiftsPro.data.stores.BLJStoreManager;
import com.stefankendall.BigLiftsPro.data.stores.JSetStore;

public class CustomAssistanceHelper {
    public static void addAssistanceToWorkout(JFTOWorkout jftoWorkout, JWorkout assistance) {
        for (JModel setModel : assistance.sets) {
            JSet set = (JSet) setModel;
            JSetStore store = (JSetStore) BLJStoreManager.instance().storeForModel(set.getClass(), set.uuid);
            JSet assistanceSet = store.createFromSet(set);
            assistanceSet.assistance = true;
            jftoWorkout.workout.addSet(assistanceSet);
        }
    }
}
