package com.stefankendall.BigLiftsPro.views.fto.lift.individual.timer;

import android.os.CountDownTimer;
import com.google.common.collect.Lists;
import com.stefankendall.BigLiftsPro.App;

import java.util.List;

public class RestTimer {
    private static RestTimer instance;
    private CountDownTimer countdownTimer;
    private long secondsRemaining = 0;
    private List<TickObserver> observers;
    private static final String CUSTOM_REST_TIME_IN_SECONDS_KEY = "customRestTime";

    public synchronized static RestTimer instance() {
        if (instance == null) {
            instance = new RestTimer();
            instance.observers = Lists.newArrayList();
        }

        return instance;
    }

    public synchronized void setTime(int secondsRemaining) {
        this.observers.clear();
        this.secondsRemaining = secondsRemaining;

        if (this.countdownTimer != null) {
            this.countdownTimer.cancel();
        }

        this.countdownTimer = new CountDownTimer(secondsRemaining * 1000, 1000) {
            @Override
            public void onTick(long milliSecondsLeft) {
                RestTimer.this.secondsRemaining = milliSecondsLeft / 1000;
                RestTimer.this.observeTick();
            }

            @Override
            public void onFinish() {
                RestTimer.this.secondsRemaining = 0;
                RestTimer.this.observeTick();
            }
        };
        this.countdownTimer.start();
    }

    private void observeTick() {
        for (TickObserver observer : observers) {
            observer.onTick(RestTimer.this.secondsRemaining);
        }
    }

    public long secondsLeft() {
        return this.secondsRemaining;
    }

    public synchronized void addTickObserver(TickObserver tickObserver) {
        this.observers.add(tickObserver);
    }

    public void stop() {
        this.secondsRemaining = 0;
        this.observeTick();
        if (this.countdownTimer != null) {
            this.countdownTimer.cancel();
        }
        this.observers.clear();
    }

    public void setCustomSeconds(int secondsToRest) {
        App.getSharedPreferencesEditor().putInt("customRestTime", secondsToRest);
        App.getSharedPreferencesEditor().commit();
    }

    public int getCustomSeconds() {
        return App.getSharedPreferences().getInt(CUSTOM_REST_TIME_IN_SECONDS_KEY, 30);
    }
}
