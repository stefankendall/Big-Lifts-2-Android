package com.stefankendall.BigLifts.views.fto.track;

import android.view.LayoutInflater;
import android.view.View;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.data.models.JWorkoutLog;
import com.stefankendall.BigLifts.views.lists.CustomListItem;

public class TrackListItem implements CustomListItem {
    private JWorkoutLog workoutLog;

    public TrackListItem(JWorkoutLog workoutLog) {
        this.workoutLog = workoutLog;
    }

    @Override
    public View fillView(View view, LayoutInflater inflater) {
        if (view == null) {
            view = inflater.inflate(R.layout.track_list_item, null);
        }

//        TextView textView = (TextView) view.findViewById(R.id.header);
//        if (textView != null) {
//            textView.setText(this.value);
//        }
        return view;
    }
}
