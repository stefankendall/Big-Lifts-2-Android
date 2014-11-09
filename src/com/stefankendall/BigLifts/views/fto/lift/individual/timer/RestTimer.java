package com.stefankendall.BigLifts.views.fto.lift.individual.timer;

import android.os.CountDownTimer;
import com.google.common.collect.Lists;

import java.util.List;

public class RestTimer {
    private static RestTimer instance;
    private CountDownTimer countdownTimer;
    private long secondsRemaining = 0;
    private List<TickObserver> observers;

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
}
