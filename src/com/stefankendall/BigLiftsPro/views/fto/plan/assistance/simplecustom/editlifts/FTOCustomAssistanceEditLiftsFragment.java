package com.stefankendall.BigLiftsPro.views.fto.plan.assistance.simplecustom.editlifts;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.stefankendall.BigLiftsPro.R;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOCustomAssistanceLift;
import com.stefankendall.BigLiftsPro.data.stores.fto.JFTOCustomAssistanceLiftStore;
import com.stefankendall.BigLiftsPro.views.BLListFragment;

public class FTOCustomAssistanceEditLiftsFragment extends BLListFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        this.setListAdapter(new FTOCustomAssistanceEditLiftsAdapter(this.getActivity()));
    }

    @Override
    protected void restore(Bundle savedInstanceState) {
    }

    @Override
    protected void save(Bundle outState) {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        registerForContextMenu(this.getListView());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        if (info.position != 0 && info.position != this.getListAdapter().getCount() - 1) {
            MenuInflater inflater = this.getActivity().getMenuInflater();
            inflater.inflate(R.menu.one_button_context_menu, menu);
            MenuItem item1 = menu.findItem(R.id.context_button_1);
            item1.setTitle("Delete");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        JFTOCustomAssistanceLiftStore.instance().removeAtIndex(info.position - 1);
        this.setListAdapter(new FTOCustomAssistanceEditLiftsAdapter(this.getActivity()));
        return true;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        JFTOCustomAssistanceLift lift = null;
        if (position == this.getListAdapter().getCount() - 1) {
            lift = (JFTOCustomAssistanceLift) JFTOCustomAssistanceLiftStore.instance().create();
            lift.name = "New Lift";
        } else if (position > 0) {
            lift = (JFTOCustomAssistanceLift) JFTOCustomAssistanceLiftStore.instance().atIndex(position - 1);
        }

        if (lift != null) {
            Intent intent = new Intent(getActivity(), FTOCustomAssistanceEditLiftActivity.class);
            intent.putExtra(FTOCustomAssistanceEditLiftActivity.EXTRA_CUSTOM_LIFT, lift.uuid);
            startActivityForResult(intent, 0);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        this.setListAdapter(new FTOCustomAssistanceEditLiftsAdapter(this.getActivity()));
    }
}
