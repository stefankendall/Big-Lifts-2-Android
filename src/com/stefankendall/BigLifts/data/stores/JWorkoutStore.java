package com.stefankendall.BigLifts.data.stores;

import com.stefankendall.BigLifts.data.models.JModel;
import com.stefankendall.BigLifts.data.models.JWorkout;

public class JWorkoutStore extends BLJStore {
    @Override
    public Class<? extends JModel> modelClass() {
        return JWorkout.class;
    }

    public static JWorkoutStore instance() {
        return (JWorkoutStore) BLJStore.instance(JWorkoutStore.class);
    }
}
