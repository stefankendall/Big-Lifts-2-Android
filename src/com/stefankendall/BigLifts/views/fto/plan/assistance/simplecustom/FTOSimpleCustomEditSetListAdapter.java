package com.stefankendall.BigLifts.views.fto.plan.assistance.simplecustom;

import android.app.Activity;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;

import java.util.List;

public class FTOSimpleCustomEditSetListAdapter extends SimpleListAdapter {
    public FTOSimpleCustomEditSetListAdapter(Activity activity) {
        super(activity);
    }

    @Override
    public List<CustomListItem> buildItems() {
        return Lists.newArrayList();
    }
}
