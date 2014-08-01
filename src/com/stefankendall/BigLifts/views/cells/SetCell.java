package com.stefankendall.BigLifts.views.cells;

import android.view.LayoutInflater;
import android.view.View;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.data.models.JSet;
import com.stefankendall.BigLifts.views.lists.CustomListItem;

public class SetCell implements CustomListItem {
    private final JSet set;

    public SetCell(JSet set) {
        this.set = set;
    }

    @Override
    public View fillView(View view, LayoutInflater inflater) {
        if (view == null) {
            view = inflater.inflate(R.layout.set_cell, null);
        }
        return view;
    }
}
