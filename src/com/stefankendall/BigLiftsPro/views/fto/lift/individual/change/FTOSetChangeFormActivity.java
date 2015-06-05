package com.stefankendall.BigLiftsPro.views.fto.lift.individual.change;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import com.stefankendall.BigLiftsPro.views.fto.FTOSingleFragmentActivity;

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
