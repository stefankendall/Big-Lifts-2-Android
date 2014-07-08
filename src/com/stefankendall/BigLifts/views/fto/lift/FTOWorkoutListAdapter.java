package com.stefankendall.BigLifts.views.fto.lift;

import android.app.Activity;
import com.google.common.collect.*;
import com.stefankendall.BigLifts.data.models.fto.JFTOWorkout;
import com.stefankendall.BigLifts.data.stores.fto.JFTOWorkoutStore;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.lists.HeaderListItem;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;

import java.util.ArrayList;
import java.util.List;

public class FTOWorkoutListAdapter extends SimpleListAdapter {
    public FTOWorkoutListAdapter(Activity context) {
        super(context);
    }

    @Override
    public List<? extends CustomListItem> buildItems() {
        ListMultimap<Integer, JFTOWorkout> workoutsByWeek = this.workoutsByWeek();
        List<Integer> weeks = Ordering.natural().immutableSortedCopy(workoutsByWeek.keySet());

        List<CustomListItem> items = Lists.newArrayList();
        for (int week : weeks) {
            items.add(new HeaderListItem(""));
        }

        return items;
    }

    protected ListMultimap<Integer, JFTOWorkout> workoutsByWeek() {
        ListMultimap<Integer, JFTOWorkout> workoutsByWeek = ArrayListMultimap.create();
        for (JFTOWorkout jftoWorkout : (List<JFTOWorkout>) JFTOWorkoutStore.instance().findAll()) {
            workoutsByWeek.put(jftoWorkout.week, jftoWorkout);
        }

        return workoutsByWeek;
    }
}
