package com.stefankendall.BigLifts.views.fto.plan.assistance.simplecustom.editlifts;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.data.models.fto.JFTOCustomAssistanceLift;
import com.stefankendall.BigLifts.views.lists.CustomListItem;

public class CustomLiftNameCell implements CustomListItem {
    private final JFTOCustomAssistanceLift customAssistanceLift;

    public CustomLiftNameCell(JFTOCustomAssistanceLift customAssistanceLift) {
        this.customAssistanceLift = customAssistanceLift;
    }

    @Override
    public View fillView(View view, LayoutInflater inflater) {
        if (view == null) {
            view = inflater.inflate(R.layout.text_cell, null);
        }

        final TextView liftName = (TextView) view.findViewById(R.id.lift_name);
        if (liftName != null) {
            liftName.setText(customAssistanceLift.name);
            liftName.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    CustomLiftNameCell.this.nameChanged(liftName.getText().toString());
                }
            });
        }
        return view;
    }

    private void nameChanged(String name) {
        this.customAssistanceLift.name = name;
    }
}
