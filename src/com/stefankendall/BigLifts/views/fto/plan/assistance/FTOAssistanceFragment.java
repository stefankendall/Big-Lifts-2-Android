package com.stefankendall.BigLifts.views.fto.plan.assistance;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.stefankendall.BigLifts.data.models.fto.JFTOAssistance;
import com.stefankendall.BigLifts.data.stores.fto.JFTOAssistanceStore;
import com.stefankendall.BigLifts.views.fto.plan.PlanListItem;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;

public class FTOAssistanceFragment extends ListFragment {

    private BiMap<Integer, String> variantsByResultCode;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        variantsByResultCode = ImmutableBiMap.<Integer, String>builder()
                .put(1, JFTOAssistance.BORING_BUT_BIG)
                .put(2, JFTOAssistance.SIMPLE_CUSTOM)
                .build();
        this.setListAdapter(new FTOAssistanceListAdapter(this.getActivity()));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        CustomListItem item = (CustomListItem) this.getListAdapter().getItem(position);
        if (item instanceof PlanListItem) {
            PlanListItem planListItem = (PlanListItem) item;
            if (planListItem.segue != null) {
                Intent i = new Intent(this.getActivity(), planListItem.segue);
                startActivityForResult(i, variantsByResultCode.inverse().get(JFTOAssistance.BORING_BUT_BIG));
            } else {
                JFTOAssistanceStore.instance().changeTo(planListItem.variant);
                ((SimpleListAdapter) this.getListAdapter()).reload();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String variant = this.variantsByResultCode.get(requestCode);
        JFTOAssistanceStore.instance().changeTo(variant);
        ((SimpleListAdapter) this.getListAdapter()).reload();
    }
}
