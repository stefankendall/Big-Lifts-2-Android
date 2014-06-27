package com.stefankendall.BigLifts.views.fto.edit;

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

public class FTOEditLiftCell implements CustomListItem {
    private final JFTOLift jftoLift;
    private EditText trainingMaxField;

    public FTOEditLiftCell(JFTOLift jftoLift) {
        this.jftoLift = jftoLift;
    }

    @Override
    public View fillView(View view, LayoutInflater inflater) {
        if (view == null) {
            view = inflater.inflate(R.layout.fto_edit_lift_cell, null);
        }

        TextView liftName = (TextView) view.findViewById(R.id.lift_name);
        EditText max = (EditText) view.findViewById(R.id.max);
        this.trainingMaxField = (EditText) view.findViewById(R.id.training_max);
        if (liftName != null) {
            liftName.setText(jftoLift.name);
            max.setHint("0");
            this.trainingMaxField.setHint("0");
            if (jftoLift.weight.compareTo(BigDecimal.ZERO) > 0) {
                max.setText(jftoLift.weight.toPlainString());
                this.updateTrainingMax(jftoLift.weight);
            } else {
                max.setText("");
                this.trainingMaxField.setText("");
            }
        }
        return view;
    }

    public void updateTrainingMax(BigDecimal weight) {
        JFTOSettings settings = (JFTOSettings) JFTOSettingsStore.instance().first();
        BigDecimal trainingWeight = weight.multiply(settings.trainingMax).divide(new BigDecimal("100"));
        this.trainingMaxField.setText(trainingWeight.setScale(1).stripTrailingZeros().toPlainString());
    }
}
