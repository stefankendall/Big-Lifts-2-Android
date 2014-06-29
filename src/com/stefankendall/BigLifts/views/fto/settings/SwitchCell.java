package com.stefankendall.BigLifts.views.fto.settings;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.App;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.views.lists.CustomListItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SwitchCell implements CustomListItem {
    protected final List<String> options;

    public SwitchCell(List<String> options) {
        this.options = options;
    }

    @Override
    public View fillView(View view, LayoutInflater inflater) {
        if (view == null) {
            view = inflater.inflate(R.layout.switch_cell, null);
        }
        RadioGroup group = (RadioGroup) view.findViewById(R.id.options);
        group.removeAllViews();
        for (String option : options) {
            RadioButton button = new RadioButton(App.getContext());
            button.setText(option);
            button.setTextColor(Color.BLACK);
            group.addView(button);
        }
        return view;
    }
}
