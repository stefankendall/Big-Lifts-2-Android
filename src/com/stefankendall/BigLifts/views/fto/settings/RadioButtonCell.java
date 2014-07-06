package com.stefankendall.BigLifts.views.fto.settings;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.google.common.collect.Lists;
import com.stefankendall.BigLifts.App;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.views.lists.CustomListItem;

import java.util.List;

public abstract class RadioButtonCell implements CustomListItem {
    protected final List<String> options;

    public RadioButtonCell(List<String> options) {
        this.options = options;
    }

    @Override
    public View fillView(View view, LayoutInflater inflater) {
        if (view == null) {
            view = inflater.inflate(R.layout.radio_button_cell, null);
        }
        final RadioGroup group = (RadioGroup) view.findViewById(R.id.options);
        group.removeAllViews();

        List<Integer> ids = Lists.newArrayList();
        for (int i = 0; i < options.size(); i++) {
            String option = options.get(i);
            RadioButton button = new RadioButton(App.getContext());
            button.setText(option);
            button.setTextColor(Color.BLACK);
            button.setId(View.generateViewId());
            ids.add(button.getId());
            group.addView(button);
        }

        group.check(ids.get(defaultSelectedIndex()));
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int selectedId) {
                RadioButton button = (RadioButton) group.findViewById(selectedId);
                int selection = RadioButtonCell.this.options.indexOf(button.getText().toString());
                RadioButtonCell.this.valueChanged(selection);
            }
        });
        return view;
    }

    abstract protected void valueChanged(int selection);

    abstract protected int defaultSelectedIndex();
}
