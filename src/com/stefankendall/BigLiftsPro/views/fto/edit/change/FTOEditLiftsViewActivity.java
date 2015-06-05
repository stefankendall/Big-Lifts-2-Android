package com.stefankendall.BigLiftsPro.views.fto.edit.change;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import com.stefankendall.BigLiftsPro.views.fto.FTOSingleFragmentActivity;

public class FTOEditLiftsViewActivity extends FTOSingleFragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Change Lifts");
    }

    @Override
    protected Fragment createFragment() {
        return new FTOEditLiftsViewFragment();
    }
}
