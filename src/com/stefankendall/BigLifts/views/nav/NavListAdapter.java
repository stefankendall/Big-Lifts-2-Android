package com.stefankendall.BigLifts.views.nav;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v4.widget.DrawerLayout;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.App;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.views.lists.CustomListItem;
import com.stefankendall.BigLifts.views.lists.SimpleListAdapter;

import java.util.List;

public class NavListAdapter extends SimpleListAdapter {

    public NavListAdapter(Activity context) {
        super(context);
    }

    private void start(Activity context, Class activity) {
        if (!activity.isInstance(context)) {
            Intent intent = new Intent(context, activity);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);
        } else {
            DrawerLayout drawerLayout = (DrawerLayout) context.findViewById(R.id.drawer_layout);
            drawerLayout.closeDrawers();
        }
    }

    @Override
    public List<? extends CustomListItem> buildItems() {
        return Lists.newArrayList(new NavListItem("Nothing", android.R.drawable.ic_menu_search, new NavAction() {
            @Override
            public void run(Activity context) {
            }
        }));
    }

    public void sendFeedback(Context fromContext) {
        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

        emailIntent.setType("plain/text");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"pokemon@stefankendall.com"});
        PackageInfo info = null;
        try {
            info = App.getContext().getPackageManager().getPackageInfo(App.getContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Big Lifts 2 Android Feedback - v" + info.versionName);
        fromContext.startActivity(Intent.createChooser(emailIntent, "Send Feedback"));
    }
}