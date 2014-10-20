package com.stefankendall.BigLifts.views.fto.plan.assistance.simplecustom.editlifts;

import android.app.Activity;
import android.widget.ListAdapter;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.data.models.fto.JFTOCustomAssistanceLift;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;

import java.util.List;

public class FTOCustomAssistanceEditLiftAdapter extends SimpleListAdapter {
    private final JFTOCustomAssistanceLift customAssistanceLift;

    public FTOCustomAssistanceEditLiftAdapter(Activity activity, JFTOCustomAssistanceLift customAssistanceLift) {
        this.items = buildItems();
        this.activity = activity;
        this.customAssistanceLift = customAssistanceLift;
    }

    @Override
    public List<CustomListItem> buildItems() {
        return Lists.newArrayList();
    }
}
