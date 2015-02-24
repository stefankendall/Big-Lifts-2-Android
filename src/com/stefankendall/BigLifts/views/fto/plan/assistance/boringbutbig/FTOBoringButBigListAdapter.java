package com.stefankendall.BigLifts.views.fto.plan.assistance.boringbutbig;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;

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
