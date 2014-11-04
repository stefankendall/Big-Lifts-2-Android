package com.stefankendall.BigLifts.views.fto.exportimport;

import android.app.Fragment;
import android.os.Bundle;
import android.view.*;
import com.stefankendall.BigLifts.R;

public class ExportImportFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.export_import_view, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.export_import_menu, menu);
        menu.findItem(R.id.export_button).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                ExportImportFragment.this.export();
                return false;
            }
        });

        menu.findItem(R.id.import_button).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                ExportImportFragment.this.importLog();
                return false;
            }
        });
    }

    private void importLog() {

    }

    private void export() {

    }
}
