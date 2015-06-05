package com.stefankendall.BigLiftsPro.views.fto.exportimport;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import com.stefankendall.BigLiftsPro.views.fto.FTOSingleFragmentActivity;

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
