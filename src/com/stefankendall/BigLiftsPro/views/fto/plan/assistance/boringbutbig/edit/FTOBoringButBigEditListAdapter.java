package com.stefankendall.BigLiftsPro.views.fto.plan.assistance.boringbutbig.edit;

import android.support.v4.app.FragmentActivity;
import com.google.common.collect.Lists;
import com.stefankendall.BigLiftsPro.data.models.JModel;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOBoringButBigLift;
import com.stefankendall.BigLiftsPro.data.stores.fto.JFTOBoringButBigLiftStore;
import com.stefankendall.BigLiftsPro.views.lists.CustomListItem;
import com.stefankendall.BigLiftsPro.views.lists.SimpleListAdapter;

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
