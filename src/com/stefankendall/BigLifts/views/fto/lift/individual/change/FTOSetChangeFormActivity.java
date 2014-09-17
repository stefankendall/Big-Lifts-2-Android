package com.stefankendall.BigLifts.views.fto.lift.individual.change;

import android.app.Fragment;
import android.os.Bundle;
import com.stefankendall.BigLifts.views.fto.FTOSingleFragmentActivity;

public class FTOSetChangeFormActivity extends FTOSingleFragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Set Change");
    }

    @Override
    protected Fragment createFragment() {
        return new FTOSetChangeFormFragment();
    }
}
