package com.stefankendall.BigLifts.views.fto.nav;

import android.app.Activity;
import android.content.Intent;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.data.stores.JCurrentProgramStore;
import com.stefankendall.BigLifts.views.fto.barloading.BarLoadingActivity;
import com.stefankendall.BigLifts.views.fto.edit.FTOEditViewActivity;
import com.stefankendall.BigLifts.views.fto.lift.FTOWorkoutListActivity;
import com.stefankendall.BigLifts.views.fto.onerep.OneRepMaxActivity;
import com.stefankendall.BigLifts.views.fto.settings.SettingsActivity;
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
    public List<? extends CustomListItem> buildItems() {
        return Lists.newArrayList(
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
    }

    private void switchTo(Class nextActivity) {
        if (activity.getClass() != nextActivity) {
            Intent intent = new Intent(this.activity, nextActivity);
            this.activity.startActivity(intent);
            this.activity.finish();
        }
    }
}
