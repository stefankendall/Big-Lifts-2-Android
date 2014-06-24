package com.stefankendall.BigLifts.views.startup;

import android.app.ListFragment;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.stefankendall.BigLifts.App;
import com.stefankendall.BigLifts.views.fto.edit.FTOEditViewActivity;

public class StartupFragment extends ListFragment {

    private StartupListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
        getActivity().setTitle("Choose a program!");
        adapter = new StartupListAdapter(getActivity());
        this.setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if (position == this.adapter.getWhatsNextPosition()) {
            this.showWhatsNextEmail();
        } else if (position == this.adapter.get531Position()) {
            this.start(FTOEditViewActivity.class);
        }
    }

    private void start(Class context) {
        Intent intent = new Intent(getActivity(), context);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void showWhatsNextEmail() {
        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

        emailIntent.setType("plain/text");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"biglifts@stefankendall.com"});
        PackageInfo info = null;
        try {
            info = App.getContext().getPackageManager().getPackageInfo(App.getContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Big Lifts 2 Android Feedback - v" + info.versionName);
        startActivity(Intent.createChooser(emailIntent, "What's next?"));
    }
}
