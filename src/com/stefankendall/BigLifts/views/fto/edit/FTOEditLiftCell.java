package com.stefankendall.BigLifts.views.fto.edit;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.data.models.fto.JFTOLift;
import com.stefankendall.BigLifts.views.lists.CustomListItem;

public class FTOEditLiftCell implements CustomListItem {
    private final JFTOLift jftoLift;

    public FTOEditLiftCell(JFTOLift jftoLift) {
        this.jftoLift = jftoLift;
    }

    @Override
    public View fillView(View view, LayoutInflater inflater) {
        if (view == null) {
            view = inflater.inflate(R.layout.fto_edit_lift_cell, null);
        }

        TextView textView = (TextView) view.findViewById(R.id.lift_name);
        if (textView != null) {
            textView.setText(jftoLift.name);
        }
        return view;
    }
}
