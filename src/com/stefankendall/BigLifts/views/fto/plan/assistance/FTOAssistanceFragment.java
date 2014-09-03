package com.stefankendall.BigLifts.views.fto.plan.assistance;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.stefankendall.BigLifts.data.models.fto.JFTOAssistance;
import com.stefankendall.BigLifts.data.stores.fto.JFTOAssistanceStore;
import com.stefankendall.BigLifts.data.stores.fto.JFTOVariantStore;
import com.stefankendall.BigLifts.views.fto.plan.PlanListItem;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;

public class FTOAssistanceFragment extends ListFragment {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        this.setListAdapter(new FTOAssistanceListAdapter(this.getActivity()));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        CustomListItem item = (CustomListItem) this.getListAdapter().getItem(position);
        if (item instanceof PlanListItem) {
            SimpleListAdapter listAdapter = (SimpleListAdapter) this.getListAdapter();
            listAdapter.reload();

            PlanListItem planListItem = (PlanListItem) item;
            if (planListItem.segue != null) {
                Intent i = new Intent(this.getActivity(), planListItem.segue);
                startActivity(i);
            } else {
                JFTOAssistanceStore.instance().changeTo(planListItem.variant);
            }
        }
    }
}
