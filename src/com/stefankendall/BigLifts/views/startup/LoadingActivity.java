package com.stefankendall.BigLifts.views.startup;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.stefankendall.BigLifts.SingleFragmentActivity;
import com.stefankendall.BigLifts.data.DataLoader;

import java.util.Timer;
import java.util.TimerTask;

public class LoadingActivity extends SingleFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActionBar().setDisplayShowHomeEnabled(false);
        getActionBar().setDisplayHomeAsUpEnabled(false);
        getActionBar().setHomeButtonEnabled(false);


        setTitle("Loading Data...");
        shouldSaveDataOnPause = false;

        final Timer loadingTimer = new Timer();
        loadingTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (DataLoader.loaded) {
                    loadingTimer.cancel();

                    Handler mainHandler = new Handler(Looper.getMainLooper());
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(LoadingActivity.this, StartupActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            LoadingActivity.this.finish();
                        }
                    });
                }
            }
        }, 500, 500);
    }

    @Override
    protected Fragment createFragment() {
        return LoadingFragment.newInstance();
    }
}
