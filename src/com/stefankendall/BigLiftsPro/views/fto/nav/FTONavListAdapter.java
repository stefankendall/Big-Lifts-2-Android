package com.stefankendall.BigLiftsPro.views.fto.nav;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import com.stefankendall.BigLiftsPro.R;
import com.stefankendall.BigLiftsPro.billing.util.IabService;
import com.stefankendall.BigLiftsPro.billing.util.Inventory;
import com.stefankendall.BigLiftsPro.billing.util.SkuDetails;
import com.stefankendall.BigLiftsPro.data.stores.JCurrentProgramStore;
import com.stefankendall.BigLiftsPro.data.stores.JPurchaseStore;
import com.stefankendall.BigLiftsPro.views.fto.barloading.BarLoadingActivity;
import com.stefankendall.BigLiftsPro.views.fto.edit.FTOEditViewActivity;
import com.stefankendall.BigLiftsPro.views.fto.exportimport.ExportImportActivity;
import com.stefankendall.BigLiftsPro.views.fto.lift.FTOWorkoutListActivity;
import com.stefankendall.BigLiftsPro.views.fto.onerep.OneRepMaxActivity;
import com.stefankendall.BigLiftsPro.views.fto.plan.FTOPlanActivity;
import com.stefankendall.BigLiftsPro.views.fto.settings.SettingsActivity;
import com.stefankendall.BigLiftsPro.views.fto.track.FTOTrackViewActivity;
import com.stefankendall.BigLiftsPro.views.lists.CustomListItem;
import com.stefankendall.BigLiftsPro.views.nav.NavAction;
import com.stefankendall.BigLiftsPro.views.nav.NavListAdapter;
import com.stefankendall.BigLiftsPro.views.nav.NavListItem;
import com.stefankendall.BigLiftsPro.views.startup.StartupActivity;

import java.util.List;

public class FTONavListAdapter extends NavListAdapter {
    public FTONavListAdapter(FragmentActivity context) {
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
                new NavListItem("Export/Import", R.drawable._03_loopback, new NavAction() {
                    @Override
                    public void run(Activity context) {
                        FTONavListAdapter.this.switchTo(ExportImportActivity.class);
                    }
                }),
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

    private void switchTo(Class nextActivity) {
        if (activity.getClass() != nextActivity) {
            Intent intent = new Intent(this.activity, nextActivity);
            this.activity.startActivity(intent);
            this.activity.finish();
        }
    }
}
