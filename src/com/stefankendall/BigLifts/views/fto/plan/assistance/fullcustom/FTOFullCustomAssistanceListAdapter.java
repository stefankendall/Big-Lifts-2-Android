package com.stefankendall.BigLifts.views.fto.plan.assistance.fullcustom;

import android.app.Activity;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.stefankendall.BigLifts.data.stores.fto.JFTOWorkoutStore;
import com.stefankendall.BigLifts.views.fto.lift.FTOSectionTitleHelper;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.lists.HeaderListItem;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;

import java.util.List;

public class FTOFullCustomAssistanceListAdapter extends SimpleListAdapter {
    public FTOFullCustomAssistanceListAdapter(Activity activity) {
        super(activity);
    }

    @Override
    public List<CustomListItem> buildItems() {
        List<Integer> weeks = Lists.newArrayList(Iterables.filter(JFTOWorkoutStore.instance().unique("week"), Integer.class));
        List<CustomListItem> items = Lists.newArrayList();
        for (int week : weeks) {
            items.add(new HeaderListItem(new FTOSectionTitleHelper().titleForSection(week - 1)));
        }
        return items;
    }
}



