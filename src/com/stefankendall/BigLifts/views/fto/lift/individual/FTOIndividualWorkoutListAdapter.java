package com.stefankendall.BigLifts.views.fto.lift.individual;

import android.app.Activity;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.data.models.fto.JFTOWorkout;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;

import java.util.List;

public class FTOIndividualWorkoutListAdapter extends SimpleListAdapter {
    protected JFTOWorkout jftoWorkout;

    public FTOIndividualWorkoutListAdapter(Activity context, JFTOWorkout jftoWorkout) {
        super(context);
        this.jftoWorkout = jftoWorkout;
    }

    @Override
    public List<? extends CustomListItem> buildItems() {
        return Lists.newArrayList();
    }
}
