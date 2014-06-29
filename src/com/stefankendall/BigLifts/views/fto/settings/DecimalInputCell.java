package com.stefankendall.BigLifts.views.fto.settings;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.data.numbers.BigDecimals;
import com.stefankendall.BigLifts.views.lists.CustomListItem;

import java.math.BigDecimal;

public abstract class DecimalInputCell implements CustomListItem {
    @Override
    public View fillView(View view, LayoutInflater inflater) {
        if (view == null) {
            view = inflater.inflate(R.layout.decimal_input_cell, null);
        }

        TextView labelView = (TextView) view.findViewById(R.id.label);
        if (labelView != null) {
            labelView.setText(this.label());
            EditText input = (EditText) view.findViewById(R.id.decimal_input);
            input.setText(this.defaultValue());
            input.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    DecimalInputCell.this.valueChanged(BigDecimals.parse(charSequence.toString()));
                }

                @Override
                public void afterTextChanged(Editable editable) {
                }
            });
        }

        return view;
    }

    protected abstract String label();

    protected abstract String defaultValue();

    abstract protected void valueChanged(BigDecimal value);
}
