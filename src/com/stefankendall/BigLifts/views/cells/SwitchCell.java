package com.stefankendall.BigLifts.views.cells;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.views.lists.CustomListItem;

public abstract class SwitchCell implements CustomListItem {
    @Override
    public View fillView(View view, LayoutInflater inflater) {
        if (view == null) {
            view = inflater.inflate(R.layout.switch_cell, null);
        }

        TextView labelView = (TextView) view.findViewById(R.id.label);
        if (labelView != null) {
            labelView.setText(this.label());
            setToggleValues(view);
        }

        return view;
    }

    protected void setToggleValues(View view) {
        Switch toggle = (Switch) view.findViewById(R.id.toggle_switch);
        toggle.setTextOn(this.switchOnText());
        toggle.setTextOff(this.switchOffText());
        toggle.setChecked(this.defaultState());
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SwitchCell.this.valueChanged(b);
            }
        });
    }

    protected String switchOnText() {
        return "ON";
    }

    protected String switchOffText() {
        return "OFF";
    }

    protected abstract String label();

    protected abstract boolean defaultState();

    abstract protected void valueChanged(boolean state);
}
