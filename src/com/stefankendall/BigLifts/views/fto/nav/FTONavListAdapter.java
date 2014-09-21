package com.stefankendall.BigLifts.views.fto.nav;

import android.app.Activity;
import android.content.Intent;
import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.billing.util.IabService;
import com.stefankendall.BigLifts.billing.util.Inventory;
import com.stefankendall.BigLifts.billing.util.SkuDetails;
import com.stefankendall.BigLifts.data.stores.JCurrentProgramStore;
import com.stefankendall.BigLifts.data.stores.JPurchaseStore;
import com.stefankendall.BigLifts.views.fto.barloading.BarLoadingActivity;
import com.stefankendall.BigLifts.views.fto.edit.FTOEditViewActivity;
import com.stefankendall.BigLifts.views.fto.lift.FTOWorkoutListActivity;
import com.stefankendall.BigLifts.views.fto.onerep.OneRepMaxActivity;
import com.stefankendall.BigLifts.views.fto.plan.FTOPlanActivity;
import com.stefankendall.BigLifts.views.fto.settings.SettingsActivity;
import com.stefankendall.BigLifts.views.fto.track.FTOTrackViewActivity;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.nav.NavAction;
import com.stefankendall.BigLifts.views.nav.NavListAdapter;
import com.stefankendall.BigLifts.views.nav.NavListItem;
import com.stefankendall.BigLifts.views.startup.StartupActivity;

import java.util.List;

public class FTONavListAdapter extends NavListAdapter {
    public FTONavListAdapter(Activity context) {
        super(context);
    }

    @Override
    public List<CustomListItem> buildItems() {
        List<CustomListItem> navItems = Lists.<CustomListItem>newArrayList(
                new NavListItem("Lift", R.drawable._89_dumbells, new NavAction() {
                    @Override
                    public void run(Activity context) {
                        FTONavListAdapter.this.switchTo(FTOWorkoutListActivity.class);
                    }
                }),
                new NavListItem("Edit Lifts", R.drawable._20_gear_2, new NavAction() {
                    @Override
                    public void run(Activity context) {
                        FTONavListAdapter.this.switchTo(FTOEditViewActivity.class);
                    }
                }),
                new NavListItem("Plan Workout", R.drawable._101_gameplan, new NavAction() {
                    @Override
                    public void run(Activity context) {
                        FTONavListAdapter.this.switchTo(FTOPlanActivity.class);
                    }
                }),
                new NavListItem("Bar Loading", R.drawable._95_equalizer, new NavAction() {
                    @Override
                    public void run(Activity context) {
                        FTONavListAdapter.this.switchTo(BarLoadingActivity.class);
                    }
                }),
                new NavListItem("Track", R.drawable._16_line_chart, new NavAction() {
                    @Override
                    public void run(Activity context) {
                        FTONavListAdapter.this.switchTo(FTOTrackViewActivity.class);
                    }
                }),
                new NavListItem("Estimate 1RM", R.drawable._161_calculator, new NavAction() {
                    @Override
                    public void run(Activity context) {
                        FTONavListAdapter.this.switchTo(OneRepMaxActivity.class);
                    }
                }),
                new NavListItem("Settings", R.drawable._157_wrench, new NavAction() {
                    @Override
                    public void run(Activity context) {
                        FTONavListAdapter.this.switchTo(SettingsActivity.class);
                    }
                }),
                buildPurchaseListItem(),
                new NavListItem("Feedback", R.drawable._09_chat_2, new NavAction() {
                    @Override
                    public void run(Activity context) {
                        FTONavListAdapter.this.sendFeedback(context);
                    }
                }),
                new NavListItem("Program", R.drawable._213_reply, new NavAction() {
                    @Override
                    public void run(Activity context) {
                        JCurrentProgramStore.instance().clearProgram();
                        FTONavListAdapter.this.switchTo(StartupActivity.class);
                    }
                })
        );
        return Lists.newArrayList(FluentIterable.from(navItems)
                .filter(Predicates.notNull())
                .toList());
    }

    private NavListItem buildPurchaseListItem() {
        if (JPurchaseStore.instance().hasPurchasedEverything()) {
            return null;
        }

        String title = "Unlock Everything";
        Inventory inventory = IabService.getInstance().getInventory();
        if (inventory == null) {
            this.findPurchasePrice();
        } else {
            SkuDetails sku = inventory.getSkuDetails(IabService.EVERYTHING_SKU);
            title += " (" + sku.getPrice() + ")";
        }

        return new NavListItem(title, R.drawable._269_happyface_2, new NavAction() {
            @Override
            public void run(Activity context) {
                IabService.getInstance().purchaseEverything(FTONavListAdapter.this.activity, new Function<Void, Void>() {
                    public Void apply(Void aVoid) {
                        FTONavListAdapter.this.reload();
                        return null;
                    }
                });
            }
        });
    }

    private void findPurchasePrice() {
        IabService.getInstance().getInventory(new Function<Inventory, Void>() {
            @Override
            public Void apply(Inventory inventory) {
                FTONavListAdapter.this.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        FTONavListAdapter.this.reload();
                    }
                });
                return null;
            }
        });
    }

    private void switchTo(Class nextActivity) {
        if (activity.getClass() != nextActivity) {
            Intent intent = new Intent(this.activity, nextActivity);
            this.activity.startActivity(intent);
            this.activity.finish();
        }
    }
}
