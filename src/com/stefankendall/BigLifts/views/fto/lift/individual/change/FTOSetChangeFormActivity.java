package com.stefankendall.BigLifts.views.fto.lift.individual.change;

import android.app.Fragment;
import com.stefankendall.BigLifts.views.fto.FTOSingleFragmentActivity;

public class FTOSetChangeFormActivity extends FTOSingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new FTOSetChangeFormFragment();
    }
}
