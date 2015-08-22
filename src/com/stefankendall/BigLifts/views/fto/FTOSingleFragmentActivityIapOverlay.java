package com.stefankendall.BigLifts.views.fto;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.data.stores.JPurchaseStore;
import com.stefankendall.BigLifts.views.fto.barloading.IapOverlayFragment;

public abstract class FTOSingleFragmentActivityIapOverlay extends FTOSingleFragmentActivityWithOverlay {
    @Override
    protected Fragment createOverlay() {
        if (JPurchaseStore.instance().hasPurchasedEverything()) {
            return null;
        } else {
            return new IapOverlayFragment();
        }
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        JPurchaseStore.instance().whenPurchase(new Function<Void, Void>() {
            @Override
            public Void apply(Void aVoid) {
                FTOSingleFragmentActivityIapOverlay.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        FTOSingleFragmentActivityIapOverlay.this.findViewById(R.id.overlayContainer).setVisibility(View.GONE);
                    }
                });
                return null;
            }
        });

        return super.onCreateView(name, context, attrs);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        JPurchaseStore.instance().whenPurchase(Functions.<Void>identity());
    }
}
