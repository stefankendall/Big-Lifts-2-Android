package com.stefankendall.BigLifts.views.fto.plan;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.data.stores.JPurchaseStore;
import com.stefankendall.BigLifts.data.stores.fto.JFTOVariantStore;
import com.stefankendall.BigLifts.views.ListFragmentWithControls;
import com.stefankendall.BigLifts.views.fto.plan.assistance.FTOAssistanceActivity;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;

public class FTOPlanFragment extends ListFragmentWithControls {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setHasOptionsMenu(true);
    }

    @Override
    protected void restore(Bundle savedInstanceState) {
    }

    @Override
    protected void save(Bundle outState) {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        JPurchaseStore.instance().whenPurchase(new Function<Void, Void>() {
            @Override
            public Void apply(Void aVoid) {
                FTOPlanFragment.this.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        SimpleListAdapter adapter = (SimpleListAdapter) FTOPlanFragment.this.getListAdapter();
                        adapter.reload();
                    }
                });
                return null;
            }
        });
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroy();
        JPurchaseStore.instance().whenPurchase(Functions.<Void>identity());
    }

    @Override
    protected ListAdapter getListAdapterForControls() {
        return new FTOPlanListAdapter(getActivity());
    }

    protected void removeListSelection() {
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.single_button, menu);
        MenuItem button = menu.findItem(R.id.button);
        button.setTitle("Assistance");
        button.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                FTOPlanFragment.this.buttonTapped();
                return true;
            }
        });
    }

    public void buttonTapped() {
        startActivity(new Intent(this.getActivity(), FTOAssistanceActivity.class));
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
