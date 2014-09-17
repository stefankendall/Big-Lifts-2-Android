package com.stefankendall.BigLifts.views.fto.lift.individual.change;

import android.app.Activity;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.views.cells.NumberInputCell;
import com.stefankendall.BigLifts.views.cells.ParameterizedDecimalInputCell;
import com.stefankendall.BigLifts.views.fto.lift.individual.SetChange;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;

import java.util.List;

public class FTOSetChangeFormListAdapter extends SimpleListAdapter {
    public FTOSetChangeFormListAdapter(Activity activity) {
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
