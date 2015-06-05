package com.stefankendall.BigLiftsPro.views.fto.plan.assistance.boringbutbig;

import android.support.v4.app.FragmentActivity;
import com.google.common.collect.Lists;
import com.stefankendall.BigLiftsPro.views.lists.CustomListItem;
import com.stefankendall.BigLiftsPro.views.lists.SimpleListAdapter;

import java.util.List;

public class FTOBoringButBigListAdapter extends SimpleListAdapter {
    public FTOBoringButBigListAdapter(FragmentActivity context) {
        super(context);
    }

    @Override
    public List<CustomListItem> buildItems() {
        return Lists.newArrayList(
                new BoringButBigPercentageCell(),
                new ThreeMonthChallengeCell()
        );
    }
}
