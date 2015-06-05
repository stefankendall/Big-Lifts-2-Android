package com.stefankendall.BigLiftsPro.views.fto.plan.assistance.simplecustom;

import android.support.v4.app.FragmentActivity;
import com.google.common.collect.Lists;
import com.stefankendall.BigLiftsPro.data.models.JModel;
import com.stefankendall.BigLiftsPro.data.models.JSet;
import com.stefankendall.BigLiftsPro.data.models.JWorkout;
import com.stefankendall.BigLiftsPro.views.cells.SetCell;
import com.stefankendall.BigLiftsPro.views.fto.barloading.AddCell;
import com.stefankendall.BigLiftsPro.views.fto.lift.individual.SetChange;
import com.stefankendall.BigLiftsPro.views.lists.CustomListItem;
import com.stefankendall.BigLiftsPro.views.lists.HeaderListItem;
import com.stefankendall.BigLiftsPro.views.lists.SimpleListAdapter;

import java.util.List;

public class FTOSimpleCustomEditWorkoutListAdapter extends SimpleListAdapter {
    private final JWorkout workout;

    public FTOSimpleCustomEditWorkoutListAdapter(FragmentActivity activity, JWorkout workout) {
        this.workout = workout;
        this.items = buildItems();
        this.activity = activity;
    }

    @Override
    public List<CustomListItem> buildItems() {
        List<CustomListItem> items = Lists.newArrayList();
        items.add(new HeaderListItem("Tap and hold to remove"));
        for (JModel model : workout.sets) {
            items.add(new SetCell((JSet) model, new SetChange()));
        }
        items.add(new AddCell("Add Set..."));
        return items;
    }
}
