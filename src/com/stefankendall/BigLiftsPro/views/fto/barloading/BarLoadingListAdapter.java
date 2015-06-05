package com.stefankendall.BigLiftsPro.views.fto.barloading;

import android.support.v4.app.FragmentActivity;
import com.google.common.collect.Lists;
import com.stefankendall.BigLiftsPro.data.models.JPlate;
import com.stefankendall.BigLiftsPro.data.stores.JPlateStore;
import com.stefankendall.BigLiftsPro.views.lists.CustomListItem;
import com.stefankendall.BigLiftsPro.views.lists.HeaderListItem;
import com.stefankendall.BigLiftsPro.views.lists.SimpleListAdapter;

import java.util.List;

public class BarLoadingListAdapter extends SimpleListAdapter {
    private final BarLoadingFragment fragment;

    public BarLoadingListAdapter(FragmentActivity context, BarLoadingFragment fragment) {
        this.activity = context;
        this.fragment = fragment;
        this.items = buildItems();
    }

    @Override
    public List<CustomListItem> buildItems() {
        List<CustomListItem> items = Lists.newArrayList();
        items.add(new HeaderListItem("Plates"));
        for (JPlate p : JPlateStore.instance().findAll()) {
            items.add(new PlateCell(p, this.fragment));
        }
        items.add(new AddCell());
        return items;
    }
}
