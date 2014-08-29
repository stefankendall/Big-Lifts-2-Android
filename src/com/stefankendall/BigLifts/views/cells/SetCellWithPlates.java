package com.stefankendall.BigLifts.views.cells;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.data.models.JSet;

public class SetCellWithPlates extends SetCell {
    public SetCellWithPlates(JSet set) {
        super(set);
    }

    public View fillView(View view, LayoutInflater inflater) {
        if (view == null) {
            view = inflater.inflate(R.layout.set_cell, null);
        }

        TextView liftName = (TextView) view.findViewById(R.id.lift_name);
        if (liftName != null) {
            setupSetData(view);
            TextView plates = (TextView) view.findViewById(R.id.plates);
        }

        return view;
    }

}
