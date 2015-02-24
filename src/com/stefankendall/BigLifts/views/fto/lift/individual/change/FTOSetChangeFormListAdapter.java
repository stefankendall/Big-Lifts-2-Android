package com.stefankendall.BigLifts.views.fto.lift.individual.change;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.views.fto.lift.individual.SetChange;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;

import java.util.List;

public class FTOSetChangeFormListAdapter extends SimpleListAdapter {
    public FTOSetChangeFormListAdapter(FragmentActivity activity) {
        super(activity);
    }

    @Override
    public List<CustomListItem> buildItems() {
        return Lists.<CustomListItem>newArrayList(
                new WeightCell(SetChange.instance().modifyingSet.roundedEffectiveWeight()),
                new RepsCell(SetChange.instance().modifyingSet.reps)
        );
    }
}
