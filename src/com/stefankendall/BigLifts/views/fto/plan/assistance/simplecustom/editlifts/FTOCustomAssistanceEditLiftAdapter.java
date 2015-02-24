package com.stefankendall.BigLifts.views.fto.plan.assistance.simplecustom.editlifts;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.data.models.fto.JFTOCustomAssistanceLift;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;

import java.util.List;

public class FTOCustomAssistanceEditLiftAdapter extends SimpleListAdapter {
    private final JFTOCustomAssistanceLift customAssistanceLift;

    public FTOCustomAssistanceEditLiftAdapter(FragmentActivity activity, JFTOCustomAssistanceLift customAssistanceLift) {
        this.activity = activity;
        this.customAssistanceLift = customAssistanceLift;
        this.items = buildItems();
    }

    @Override
    public List<CustomListItem> buildItems() {
        return Lists.newArrayList(
                new CustomLiftNameCell(customAssistanceLift),
                new CustomLiftWeightCell(customAssistanceLift),
                new CustomLiftIncrementCell(customAssistanceLift),
                new CustomLiftUsesBarCell(customAssistanceLift)
        );
    }
}
