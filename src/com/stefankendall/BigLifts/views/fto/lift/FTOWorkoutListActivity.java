package com.stefankendall.BigLifts.views.fto.lift;

import android.app.Fragment;
import com.stefankendall.BigLifts.SingleFragmentActivity;

public class FTOWorkoutListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new FTOWorkoutListFragment();
    }
}
