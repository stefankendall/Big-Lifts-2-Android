package com.stefankendall.BigLiftsPro.views.fto.plan.assistance.simplecustom.editlifts;

import android.support.v4.app.FragmentActivity;
import com.google.common.collect.Lists;
import com.stefankendall.BigLiftsPro.data.models.JModel;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOCustomAssistanceLift;
import com.stefankendall.BigLiftsPro.data.stores.fto.JFTOCustomAssistanceLiftStore;
import com.stefankendall.BigLiftsPro.views.fto.barloading.AddCell;
import com.stefankendall.BigLiftsPro.views.lists.CustomListItem;
import com.stefankendall.BigLiftsPro.views.lists.HeaderListItem;
import com.stefankendall.BigLiftsPro.views.lists.SimpleListAdapter;
import com.stefankendall.BigLiftsPro.views.lists.SimpleListItem;

import java.util.List;

public class FTOCustomAssistanceEditLiftsAdapter extends SimpleListAdapter {
    public FTOCustomAssistanceEditLiftsAdapter(FragmentActivity context) {
        super(context);
    }

    @Override
    public List<CustomListItem> buildItems() {
        List<CustomListItem> items = Lists.<CustomListItem>newArrayList(
                new HeaderListItem("Tap and hold to remove")
        );

        for (JModel customLiftModel : JFTOCustomAssistanceLiftStore.instance().findAll()) {
            JFTOCustomAssistanceLift lift = (JFTOCustomAssistanceLift) customLiftModel;
            items.add(new SimpleListItem(lift.name));
        }

        items.add(new AddCell());

        return items;
    }
}
