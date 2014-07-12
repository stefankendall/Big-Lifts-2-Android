package com.stefankendall.BigLifts.views.fto.barloading;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.google.common.base.Function;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.data.models.JSettings;
import com.stefankendall.BigLifts.data.stores.JSettingsStore;
import com.stefankendall.BigLifts.views.lists.CustomListItem;

public class AddCell implements CustomListItem {
    private final Function<Void, Void> callback;

    public AddCell(Function<Void, Void> callback) {
        this.callback = callback;
    }

    @Override
    public View fillView(View view, LayoutInflater inflater) {
        if (view == null) {
            view = inflater.inflate(R.layout.add_cell, null);
        }

        return view;
    }
}
