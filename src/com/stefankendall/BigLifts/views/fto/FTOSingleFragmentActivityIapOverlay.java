package com.stefankendall.BigLifts.views.fto;

import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ListView;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.SingleFragmentActivityWithOverlay;
import com.stefankendall.BigLifts.data.stores.JPurchaseStore;
import com.stefankendall.BigLifts.views.fto.barloading.IapOverlayFragment;
import com.stefankendall.BigLifts.views.fto.nav.FTONavListAdapter;
import com.stefankendall.BigLifts.views.nav.NavListItem;

public abstract class FTOSingleFragmentActivityIapOverlay extends FTOSingleFragmentActivityWithOverlay {
    @Override
    protected Fragment createOverlay() {
        if (JPurchaseStore.instance().hasPurchasedEverything()) {
            return null;
        } else {
            return new IapOverlayFragment();
        }
    }
}
