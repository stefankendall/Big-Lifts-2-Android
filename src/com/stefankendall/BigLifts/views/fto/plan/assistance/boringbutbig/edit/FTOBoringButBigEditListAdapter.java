package com.stefankendall.BigLifts.views.fto.plan.assistance.boringbutbig.edit;

import android.app.Activity;
import android.widget.ListAdapter;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;

import java.util.List;

public class FTOBoringButBigEditListAdapter extends SimpleListAdapter {
    public FTOBoringButBigEditListAdapter(Activity activity) {
        super(activity);
    }

    @Override
    public List<CustomListItem> buildItems() {
        return Lists.newArrayList();
    }
}
