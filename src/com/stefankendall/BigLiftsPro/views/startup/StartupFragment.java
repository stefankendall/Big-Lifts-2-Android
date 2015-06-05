package com.stefankendall.BigLiftsPro.views.startup;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.stefankendall.BigLiftsPro.App;
import com.stefankendall.BigLiftsPro.data.models.JCurrentProgram;
import com.stefankendall.BigLiftsPro.data.stores.JCurrentProgramStore;
import com.stefankendall.BigLiftsPro.views.BLListFragment;
import com.stefankendall.BigLiftsPro.views.fto.edit.FTOEditViewActivity;
import com.stefankendall.BigLiftsPro.views.fto.lift.FTOWorkoutListActivity;

public class StartupFragment extends BLListFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        JCurrentProgram program = (JCurrentProgram) JCurrentProgramStore.instance().first();
        if (program.name != null) {
            this.start(FTOWorkoutListActivity.class);
        } else {
            setRetainInstance(true);
            getActivity().setTitle("Choose a program!");
            this.setListAdapter(new StartupListAdapter(getActivity()));
        }
    }

    @Override
    protected void restore(Bundle savedInstanceState) {
    }

    @Override
    protected void save(Bundle outState) {
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        StartupListAdapter adapter = (StartupListAdapter) this.getListAdapter();
        if (position == adapter.getWhatsNextPosition()) {
            this.showWhatsNextEmail();
        } else if (position == adapter.get531Position()) {
            JCurrentProgram program = (JCurrentProgram) JCurrentProgramStore.instance().first();
            program.name = JCurrentProgram.PROGRAM_531;
            this.start(FTOEditViewActivity.class);
        }
    }

    private void start(Class context) {
        Intent intent = new Intent(getActivity(), context);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        getActivity().finish();
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
