package com.stefankendall.BigLiftsPro.views.fto.plan.assistance.simplecustom.editset;

import com.google.common.collect.Lists;
import com.stefankendall.BigLiftsPro.data.models.JSet;
import com.stefankendall.BigLiftsPro.data.models.JWorkout;
import com.stefankendall.BigLiftsPro.views.lists.CustomListItem;
import com.stefankendall.BigLiftsPro.views.lists.SimpleListAdapter;

import java.util.List;

public class FTOSimpleCustomEditSetListAdapter extends SimpleListAdapter {
    private final JWorkout workout;
    private final JSet set;
    private final FTOSimpleCustomEditSetFragment fragment;

    public FTOSimpleCustomEditSetListAdapter(FTOSimpleCustomEditSetFragment fragment, JWorkout workout, JSet set) {
        this.fragment = fragment;
        this.activity = fragment.getActivity();
        this.workout = workout;
        this.set = set;

        this.items = buildItems();
    }

    @Override
    public List<CustomListItem> buildItems() {
        return Lists.newArrayList(
                new UseTrainingMaxCell(this.fragment, this.workout, this.set),
                new FTOAssistanceLiftSelector(this.set),
                new PercentOfLiftWeightCell(this.set),
                new CustomRepsCell(this.set)
        );
    }
}
