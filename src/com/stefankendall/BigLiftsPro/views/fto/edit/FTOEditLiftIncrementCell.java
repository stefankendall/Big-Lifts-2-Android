package com.stefankendall.BigLiftsPro.views.fto.edit;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.stefankendall.BigLiftsPro.R;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOLift;
import com.stefankendall.BigLiftsPro.data.numbers.BigDecimals;
import com.stefankendall.BigLiftsPro.views.lists.CustomListItem;

import java.math.BigDecimal;

public class FTOEditLiftIncrementCell implements CustomListItem {
    private final JFTOLift jftoLift;
    private EditText increment;

    public FTOEditLiftIncrementCell(JFTOLift jftoLift) {
        this.jftoLift = jftoLift;
    }

    @Override
    public View fillView(View view, LayoutInflater inflater) {
        if (view == null) {
            view = inflater.inflate(R.layout.decimal_input_cell, null);
        }

        TextView liftName = (TextView) view.findViewById(R.id.label);
        this.increment = (EditText) view.findViewById(R.id.input);
        if (this.increment != null) {
            liftName.setText(jftoLift.name);
            this.increment.setText(BigDecimals.print(jftoLift.increment));
            this.addListeners();
        }
        return view;
    }

    private void addListeners() {
        this.increment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence string, int i, int i2, int i3) {
                FTOEditLiftIncrementCell.this.updateIncrement(string.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    protected void updateIncrement(String string) {
        try {
            this.jftoLift.increment = new BigDecimal(string);
        } catch (Exception ignored) {
            this.jftoLift.increment = BigDecimal.ZERO;
        }
    }
}
