package com.stefankendall.BigLifts.views.fto.edit.change;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.google.common.base.Function;
import com.google.common.base.Functions;
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
        JFTOLift selected = (JFTOLift) JFTOLiftStore.instance().atIndex(info.position);
        if (item.getItemId() == UP) {
            JFTOLift above = (JFTOLift) JFTOLiftStore.instance().atIndex(info.position - 1);
            JFTOLiftStore.instance().swapOrder(selected, above);
        } else if (item.getItemId() == DOWN) {
            JFTOLift below = (JFTOLift) JFTOLiftStore.instance().atIndex(info.position + 1);
            JFTOLiftStore.instance().swapOrder(selected, below);
        } else if (item.getItemId() == CHANGE_NAME) {
            this.presentChangeNameDialog(selected, Functions.<Void>identity());
        } else {
            JFTOLiftStore.instance().removeAtIndex(info.position);
        }

        SimpleListAdapter adapter = (SimpleListAdapter) this.getListAdapter();
        adapter.reload();
        return true;
    }

    private void presentChangeNameDialog(final JFTOLift selected, final Function<Void, Void> cancelCallback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.single_text_dialog, null);
        final EditText editText = (EditText) view.findViewById(R.id.text);
        editText.setText(selected.name);
        builder.setView(view);
        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                selected.name = editText.getText().toString();
                dialogInterface.dismiss();
                FTOEditLiftsViewFragment.this.reload();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                cancelCallback.apply(null);
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if (position == l.getAdapter().getCount() - 1) {
            final JFTOLift lift = (JFTOLift) JFTOLiftStore.instance().create();
            lift.name = "New Lift";
            this.presentChangeNameDialog(lift, new Function<Void, Void>() {
                @Override
                public Void apply(Void aVoid) {
                    JFTOLiftStore.instance().remove(lift);
                    return null;
                }
            });
        }
    }

    @Override
    protected ListAdapter getListAdapterForControls() {
        return new FTOEditLiftsViewListAdapter(this.getActivity());
    }

    protected void removeListSelection() {
    }

    @Override
    protected void restore(Bundle savedInstanceState) {
    }

    @Override
    protected void save(Bundle outState) {
    }
}
