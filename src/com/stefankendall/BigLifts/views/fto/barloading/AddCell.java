package com.stefankendall.BigLifts.views.fto.barloading;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.views.lists.CustomListItem;

public class AddCell implements CustomListItem {
    private final String text;

    public AddCell(String text) {
        this.text = text;
    }

    public AddCell() {
        this("Add...");
    }

    @Override
    public View fillView(View view, LayoutInflater inflater) {
        if (view == null) {
            view = inflater.inflate(R.layout.add_cell, null);
            TextView addTextView = (TextView) view.findViewById(R.id.add);
            if (addTextView != null) {
                addTextView.setText(this.text);
            }
        }
        return view;
    }
}
