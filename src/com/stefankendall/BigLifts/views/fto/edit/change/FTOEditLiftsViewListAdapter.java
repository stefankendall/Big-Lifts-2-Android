package com.stefankendall.BigLifts.views.fto.edit.change;

import android.support.v4.app.FragmentActivity;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.data.models.fto.JFTOLift;
import com.stefankendall.BigLifts.data.stores.fto.JFTOLiftStore;
import com.stefankendall.BigLifts.views.fto.barloading.AddCell;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;
import com.stefankendall.BigLifts.views.lists.SimpleListItem;
import com.stefankendall.BigLifts.views.lists.SimpleListItemWithSubtitle;

import java.util.List;

public class FTOEditLiftsViewListAdapter extends SimpleListAdapter {
    public FTOEditLiftsViewListAdapter(FragmentActivity context) {
        super(context);
    }

    @Override
    public List<CustomListItem> buildItems() {
        List<CustomListItem> items = Lists.newArrayList();
        for (int i = 0; i < JFTOLiftStore.instance().count(); i++) {
            JFTOLift jftoLift = (JFTOLift) JFTOLiftStore.instance().atIndex(i);
            if (i == 0) {
                items.add(new SimpleListItemWithSubtitle(jftoLift.name, "Tap and hold"));
            } else {
                items.add(new SimpleListItem(jftoLift.name));
            }
        }

        items.add(new AddCell());
        return items;
    }
}
