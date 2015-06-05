package com.stefankendall.BigLiftsPro.views.fto.edit;

import android.support.v4.app.FragmentActivity;
import com.google.common.collect.Lists;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOLift;
import com.stefankendall.BigLiftsPro.data.stores.fto.JFTOLiftStore;
import com.stefankendall.BigLiftsPro.views.lists.CustomListItem;
import com.stefankendall.BigLiftsPro.views.lists.HeaderListItem;
import com.stefankendall.BigLiftsPro.views.lists.LeftRightHeaderListItem;
import com.stefankendall.BigLiftsPro.views.lists.SimpleListAdapter;

import java.util.List;

public class FTOEditViewListAdapter extends SimpleListAdapter {
    public FTOEditViewListAdapter(FragmentActivity context) {
        super(context);
    }

    @Override
    public List<CustomListItem> buildItems() {
        List<CustomListItem> view = Lists.<CustomListItem>newArrayList(
                new LeftRightHeaderListItem("Max", "Training Max")
        );

        for (int i = 0; i < JFTOLiftStore.instance().count(); i++) {
            JFTOLift jftoLift = this.liftAtIndex(i);
            view.add(new FTOEditLiftCell(jftoLift));
        }

        view.add(new HeaderListItem("Increment"));

        for (int i = 0; i < JFTOLiftStore.instance().count(); i++) {
            JFTOLift jftoLift = this.liftAtIndex(i);
            view.add(new FTOEditLiftIncrementCell(jftoLift));
        }

        return view;
    }

    private JFTOLift liftAtIndex(int i) {
        return (JFTOLift) JFTOLiftStore.instance().atIndex(i);
    }
}
