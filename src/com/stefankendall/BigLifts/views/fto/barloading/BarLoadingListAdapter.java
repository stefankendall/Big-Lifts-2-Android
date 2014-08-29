package com.stefankendall.BigLifts.views.fto.barloading;

import android.app.Activity;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.data.models.JPlate;
import com.stefankendall.BigLifts.data.stores.JPlateStore;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.lists.HeaderListItem;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;

import java.util.List;

public class BarLoadingListAdapter extends SimpleListAdapter {
    private final BarLoadingFragment fragment;

    public BarLoadingListAdapter(Activity context, BarLoadingFragment fragment) {
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
