package com.stefankendall.BigLifts.views.fto.edit.change;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.data.models.fto.JFTOLift;
import com.stefankendall.BigLifts.data.stores.fto.JFTOLiftStore;
import com.stefankendall.BigLifts.views.ListFragmentWithControls;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;

public class FTOEditLiftsViewFragment extends ListFragmentWithControls {
    static int UP = R.id.context_button_1;
    static int DOWN = R.id.context_button_2;
    static int CHANGE_NAME = R.id.context_button_3;
    static int DELETE = R.id.context_button_4;
    private AlertDialog dialog;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        registerForContextMenu(this.getListView());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        if (info.position == this.getListAdapter().getCount() - 1) {
            return;
        }

        MenuInflater inflater = this.getActivity().getMenuInflater();
        inflater.inflate(R.menu.four_button_context_menu, menu);

        MenuItem item1 = menu.findItem(UP);
        item1.setTitle("Up");
        item1.setVisible(info.position > 0);

        MenuItem item2 = menu.findItem(DOWN);
        item2.setTitle("Down");
        final int ONE_FOR_ADD_ROW = 1;
        item2.setVisible(info.position < this.getListAdapter().getCount() - 1 - ONE_FOR_ADD_ROW);

        MenuItem item3 = menu.findItem(CHANGE_NAME);
        item3.setTitle("Change Name");

        MenuItem item4 = menu.findItem(DELETE);
        item4.setTitle("Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        JFTOLift toMove = (JFTOLift) JFTOLiftStore.instance().atIndex(info.position);
        if (item.getItemId() == UP) {
            JFTOLift above = (JFTOLift) JFTOLiftStore.instance().atIndex(info.position - 1);
            int order = toMove.order;
            toMove.order = above.order;
            above.order = order;
        } else if (item.getItemId() == DOWN) {
            JFTOLift below = (JFTOLift) JFTOLiftStore.instance().atIndex(info.position + 1);
            int order = toMove.order;
            toMove.order = below.order;
            below.order = order;
        } else if (item.getItemId() == CHANGE_NAME) {
            this.presentChangeNameDialog();
        } else {
            JFTOLiftStore.instance().removeAtIndex(info.position);
        }

        SimpleListAdapter adapter = (SimpleListAdapter) this.getListAdapter();
        adapter.reload();
        return true;
    }

    private void presentChangeNameDialog() {
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.single_text_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        builder.setView(layout);
        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });
        this.dialog = builder.create();
    }

    @Override
    protected ListAdapter getListAdapterForControls() {
        return new FTOEditLiftsViewListAdapter(this.getActivity());
    }

    protected void removeListSelection() {
    }
}
