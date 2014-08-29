package com.stefankendall.BigLifts.views.fto.plan;

import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.stefankendall.BigLifts.data.stores.fto.JFTOVariantStore;
import com.stefankendall.BigLifts.views.ListFragmentWithControls;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;

public class FTOPlanFragment extends ListFragmentWithControls {
    @Override
    protected ListAdapter getListAdapterForControls() {
        return new FTOPlanListAdapter(getActivity());
    }

    protected void removeListSelection() {
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        CustomListItem item = (CustomListItem) this.getListAdapter().getItem(position);
        if (item instanceof PlanListItem) {
            PlanListItem planListItem = (PlanListItem) item;
            JFTOVariantStore.instance().changeTo(planListItem.variant);
            SimpleListAdapter listAdapter = (SimpleListAdapter) this.getListAdapter();
            listAdapter.reload();
        }
    }
}
