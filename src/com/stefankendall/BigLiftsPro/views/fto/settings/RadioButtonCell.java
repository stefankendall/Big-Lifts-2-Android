package com.stefankendall.BigLiftsPro.views.fto.settings;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.google.common.collect.Lists;
import com.stefankendall.BigLiftsPro.App;
import com.stefankendall.BigLiftsPro.R;
import com.stefankendall.BigLiftsPro.views.fto.barloading.FieldWatcher;
import com.stefankendall.BigLiftsPro.views.lists.CustomListItem;

import java.util.List;

public abstract class RadioButtonCell implements CustomListItem {
    protected final List<String> options;
    private FieldWatcher fieldWatcher;

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
        for (String option : options) {
            RadioButton button = new RadioButton(App.getContext());
            button.setText(option);
            button.setTextColor(Color.BLACK);
            button.setId(App.generateViewId());
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
                if (RadioButtonCell.this.fieldWatcher != null) {
                    RadioButtonCell.this.fieldWatcher.fieldChanged();
                }
            }
        });
        return view;
    }

    public void setFieldWatcher(FieldWatcher fieldWatcher) {
        this.fieldWatcher = fieldWatcher;
    }

    abstract protected void valueChanged(int selection);

    abstract protected int defaultSelectedIndex();
}
