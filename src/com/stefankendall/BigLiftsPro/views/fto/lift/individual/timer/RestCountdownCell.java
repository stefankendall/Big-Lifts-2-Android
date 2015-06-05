package com.stefankendall.BigLiftsPro.views.fto.lift.individual.timer;

import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.stefankendall.BigLiftsPro.R;
import com.stefankendall.BigLiftsPro.views.lists.CustomListItem;


public class RestCountdownCell implements CustomListItem, TickObserver {
    private TextView timeLeftTextView;

    public RestCountdownCell() {
        RestTimer.instance().addTickObserver(this);
    }

    @Override
    public View fillView(View view, LayoutInflater inflater) {
        if (view == null) {
            view = inflater.inflate(R.layout.rest_countdown, null);
        }

        this.timeLeftTextView = (TextView) view.findViewById(R.id.timeLeftTextView);
        if (this.timeLeftTextView != null) {
            this.timeLeftTextView.setText(DateUtils.formatElapsedTime(RestTimer.instance().secondsLeft()));
        }
        return view;
    }

    @Override
    public void onTick(long secondsRemaining) {
        if (timeLeftTextView != null) {
            this.timeLeftTextView.setText(DateUtils.formatElapsedTime(secondsRemaining));
        }
    }
}