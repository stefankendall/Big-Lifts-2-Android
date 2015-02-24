package com.stefankendall.BigLifts.views.fto.plan.assistance.boringbutbig.edit;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.data.models.JModel;
import com.stefankendall.BigLifts.data.models.fto.JFTOBoringButBigLift;
import com.stefankendall.BigLifts.data.stores.fto.JFTOBoringButBigLiftStore;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;

import java.util.List;

public class FTOBoringButBigEditListAdapter extends SimpleListAdapter {
    public FTOBoringButBigEditListAdapter(FragmentActivity activity) {
        super(activity);
    }

    @Override
    public List<CustomListItem> buildItems() {
        List<CustomListItem> items = Lists.newArrayList();
        for (JModel model : JFTOBoringButBigLiftStore.instance().findAll()) {
            JFTOBoringButBigLift lift = (JFTOBoringButBigLift) model;
            items.add(new FTOBoringButBigEditCell(lift));
        }
        return items;
    }
}
