package com.stefankendall.BigLiftsPro.views.nav;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import com.google.common.collect.Lists;
import com.stefankendall.BigLiftsPro.App;
import com.stefankendall.BigLiftsPro.R;
import com.stefankendall.BigLiftsPro.views.lists.CustomListItem;
import com.stefankendall.BigLiftsPro.views.lists.SimpleListAdapter;

import java.util.List;

public class NavListAdapter extends SimpleListAdapter {

    public NavListAdapter(FragmentActivity context) {
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
    public List<CustomListItem> buildItems() {
        return Lists.<CustomListItem>newArrayList(new NavListItem("Nothing", android.R.drawable.ic_menu_search, new NavAction() {
            @Override
            public void run(Activity context) {
            }
        }));
    }

    public void sendFeedback(Context fromContext) {
        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

        emailIntent.setType("plain/text");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"biglifts@stefankendall.com"});
        PackageInfo info = null;
        try {
            info = App.getContext().getPackageManager().getPackageInfo(App.getContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Big Lifts 2 Android Feedback - v" + info.versionName);
        fromContext.startActivity(Intent.createChooser(emailIntent, "Send Feedback"));
    }
}