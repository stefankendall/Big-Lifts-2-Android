package com.stefankendall.BigLifts.views.fto.edit;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.data.models.fto.JFTOLift;
import com.stefankendall.BigLifts.data.models.fto.JFTOSettings;
import com.stefankendall.BigLifts.data.stores.fto.JFTOSettingsStore;
import com.stefankendall.BigLifts.views.lists.CustomListItem;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FTOEditLiftIncrementCell implements CustomListItem {
    private final JFTOLift jftoLift;
    private EditText increment;

    public FTOEditLiftIncrementCell(JFTOLift jftoLift) {
        this.jftoLift = jftoLift;
    }

    @Override
    public View fillView(View view, LayoutInflater inflater) {
        if (view == null) {
            view = inflater.inflate(R.layout.fto_edit_lift_increment_cell, null);
        }

        TextView liftName = (TextView) view.findViewById(R.id.lift_name);
        this.increment = (EditText) view.findViewById(R.id.increment);
        if (this.increment != null) {
            liftName.setText(jftoLift.name);
            this.increment.setText(jftoLift.increment.toPlainString());
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
