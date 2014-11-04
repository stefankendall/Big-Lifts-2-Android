package com.stefankendall.BigLifts.views.fto.exportimport;

import android.app.Fragment;
import android.os.Bundle;
import com.stefankendall.BigLifts.views.fto.FTOSingleFragmentActivity;

public class ExportImportActivity extends FTOSingleFragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupNavigation();
        setTitle("Export/Import");
    }

    @Override
    protected Fragment createFragment() {
        return new ExportImportFragment();
    }
}
