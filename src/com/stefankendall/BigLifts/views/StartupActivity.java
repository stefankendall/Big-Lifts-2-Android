package com.stefankendall.BigLifts.views;

import android.app.Fragment;
import com.stefankendall.BigLifts.SingleFragmentActivity;

public class StartupActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new StartupFragment();
    }
}
