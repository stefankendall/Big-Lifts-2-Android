package com.stefankendall.BigLifts.views.cells;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.views.lists.CustomListItem;

public abstract class NumberInputCell implements CustomListItem {
    protected EditText input;

    @Override
    public View fillView(View view, LayoutInflater inflater) {
        if (view == null) {
            view = inflater.inflate(getLayoutResource(), null);
        }

        TextView labelView = (TextView) view.findViewById(R.id.label);
        if (labelView != null) {
            labelView.setText(this.label());
            this.input = (EditText) view.findViewById(R.id.input);
            this.input.setText(this.defaultValue());
            this.input.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    NumberInputCell.this.stringValueChanged(charSequence.toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {
                }
            });
        }

        return view;
    }

    protected abstract void stringValueChanged(String s);

    abstract protected int getLayoutResource();

    protected abstract String label();

    protected abstract String defaultValue();
}
