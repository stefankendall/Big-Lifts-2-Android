package com.stefankendall.BigLiftsPro.views.cells;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.stefankendall.BigLiftsPro.R;
import com.stefankendall.BigLiftsPro.views.lists.CustomListItem;

public abstract class ReadOnlyDecimalCell implements CustomListItem {
    protected TextView input;

    @Override
    public View fillView(View view, LayoutInflater inflater) {
        if (view == null) {
            view = inflater.inflate(getLayoutResource(), null);
        }

        TextView labelView = (TextView) view.findViewById(R.id.label);
        if (labelView != null) {
            labelView.setText(this.label());
            this.input = (TextView) view.findViewById(R.id.input);
        }

        return view;
    }

    protected abstract int getLayoutResource();

    protected abstract String label();

    public void setValue(String value) {
        this.input.setText(value);
    }

    public String getValue() {
        return this.input.getText().toString();
    }
}
