package com.stefankendall.BigLifts.views.fto.plan;

import android.app.Activity;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.views.cells.ParameterizedDecimalInputCell;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.lists.HeaderListItem;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;

import java.util.List;

public class FTOPlanListAdapter extends SimpleListAdapter {
    public FTOPlanListAdapter(Activity activity) {
        super(activity);
    }

    @Override
    public List<? extends CustomListItem> buildItems() {
        return Lists.newArrayList(
                new HeaderListItem("Lifting"),
                new TrainingMaxCell(),
                new HeaderListItem("Variation")
        );
    }
}
