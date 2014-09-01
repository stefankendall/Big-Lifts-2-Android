package com.stefankendall.BigLifts.views.fto.plan.assistance;

import android.app.Activity;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.views.fto.plan.PlanListItem;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;

import java.util.List;

public class FTOAssistanceListAdapter extends SimpleListAdapter {
    public FTOAssistanceListAdapter(Activity context) {
        super(context);
    }

    @Override
    public List<CustomListItem> buildItems() {
        return Lists.<CustomListItem>newArrayList(
                new AssistancePlanListItem("None", "", null),
                new AssistancePlanListItem("Boring But Big", "5 sets 10 reps @50% of main lift", null)
        );
    }
}
