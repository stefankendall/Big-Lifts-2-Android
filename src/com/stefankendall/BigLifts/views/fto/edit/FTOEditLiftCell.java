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

public class FTOEditLiftCell implements CustomListItem {
    private final JFTOLift jftoLift;
    protected EditText trainingMaxField;
    protected EditText maxField;

    public FTOEditLiftCell(JFTOLift jftoLift) {
        this.jftoLift = jftoLift;
    }

    @Override
    public View fillView(View view, LayoutInflater inflater) {
        if (view == null) {
            view = inflater.inflate(R.layout.fto_edit_lift_cell, null);
        }

        TextView liftName = (TextView) view.findViewById(R.id.lift_name);
        this.maxField = (EditText) view.findViewById(R.id.max);
        this.trainingMaxField = (EditText) view.findViewById(R.id.training_max);
        if (liftName != null) {
            liftName.setText(jftoLift.name);
            this.maxField.setHint("0");
            this.trainingMaxField.setHint("0");
            if (jftoLift.weight.compareTo(BigDecimal.ZERO) > 0) {
                this.maxField.setText(jftoLift.weight.toPlainString());
                this.updateTrainingMaxForWeight(jftoLift.weight);
            } else {
                this.maxField.setText("");
                this.trainingMaxField.setText("");
            }

            this.addListeners();
        }
        return view;
    }

    private void addListeners() {
        this.maxField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence text, int i, int i2, int i3) {
                FTOEditLiftCell.this.updateMax(text);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        this.trainingMaxField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence text, int i, int i2, int i3) {
                FTOEditLiftCell.this.updateTrainingMax(text);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    protected void updateMax(CharSequence text) {
        try {
            this.jftoLift.weight = new BigDecimal(text.toString());
        } catch (Exception ignored) {
            this.jftoLift.weight = BigDecimal.ZERO;
        }
        this.updateTrainingMaxForWeight(this.jftoLift.weight);
    }

    protected void updateTrainingMax(CharSequence text) {
        JFTOSettings jftoSettings = (JFTOSettings) JFTOSettingsStore.instance().first();

        BigDecimal weight;
        try {
            weight = new BigDecimal(text.toString());
        } catch (Exception ignored) {
            weight = BigDecimal.ZERO;
        }

        BigDecimal trainingMaxFraction = new BigDecimal("100").divide(jftoSettings.trainingMax, 4, RoundingMode.HALF_UP);
        this.jftoLift.weight = weight.multiply(trainingMaxFraction).setScale(1, BigDecimal.ROUND_HALF_UP).stripTrailingZeros();

        String weightString = this.jftoLift.weight.toPlainString();
        if (!weightString.equals(this.maxField.getText().toString())) {
            this.maxField.setText(weightString);
        }
    }

    public void updateTrainingMaxForWeight(BigDecimal weight) {
        JFTOSettings settings = (JFTOSettings) JFTOSettingsStore.instance().first();
        BigDecimal trainingWeight = weight.multiply(settings.trainingMax).divide(new BigDecimal("100"));
        String trainingWeightText = trainingWeight.setScale(1, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString();
        if (!trainingWeightText.equals(this.trainingMaxField.getText().toString())) {
            this.trainingMaxField.setText(trainingWeightText);
        }
    }
}