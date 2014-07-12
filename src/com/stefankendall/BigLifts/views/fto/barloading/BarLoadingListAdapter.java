package com.stefankendall.BigLifts.views.fto.barloading;

import android.app.Activity;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.data.models.JPlate;
import com.stefankendall.BigLifts.data.stores.JPlateStore;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.lists.HeaderListItem;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;

import java.util.List;

public class BarLoadingListAdapter extends SimpleListAdapter {
    private final RefreshingList refreshingList;

    public BarLoadingListAdapter(Activity context, RefreshingList refreshingList) {
        this.activity = context;
        this.refreshingList = refreshingList;
        this.items = buildItems();
    }

    @Override
    public List<? extends CustomListItem> buildItems() {
        List<CustomListItem> items = Lists.newArrayList();
        items.add(new HeaderListItem("Plates"));
        for (JPlate p : JPlateStore.instance().findAll()) {
            items.add(new PlateCell(p, this.refreshingList));
        }
        items.add(new AddCell(new Function<Void, Void>() {
            @Override
            public Void apply(Void aVoid) {
                return null;
            }
        }));

        return items;
    }
}
