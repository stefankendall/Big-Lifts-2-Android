package com.stefankendall.BigLiftsPro.views.fto.edit.change;

import android.support.v4.app.FragmentActivity;
import com.google.common.collect.Lists;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOLift;
import com.stefankendall.BigLiftsPro.data.stores.fto.JFTOLiftStore;
import com.stefankendall.BigLiftsPro.views.fto.barloading.AddCell;
import com.stefankendall.BigLiftsPro.views.lists.CustomListItem;
import com.stefankendall.BigLiftsPro.views.lists.SimpleListAdapter;
import com.stefankendall.BigLiftsPro.views.lists.SimpleListItem;
import com.stefankendall.BigLiftsPro.views.lists.SimpleListItemWithSubtitle;

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
