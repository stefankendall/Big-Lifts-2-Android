package com.stefankendall.BigLifts.views.cells;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.data.models.JModel;
import com.stefankendall.BigLifts.data.models.JSet;
import com.stefankendall.BigLifts.data.models.JSettings;
import com.stefankendall.BigLifts.data.numbers.BigDecimals;
import com.stefankendall.BigLifts.data.stores.JSettingsStore;
import com.stefankendall.BigLifts.views.lists.CustomListItem;

import java.math.BigDecimal;

public class SetCell implements CustomListItem {
    protected final JSet set;

    public SetCell(JSet set) {
        this.set = set;
    }

    @Override
    public View fillView(View view, LayoutInflater inflater) {
        if (view == null) {
            view = inflater.inflate(R.layout.set_cell, null);
        }

        TextView liftName = (TextView) view.findViewById(R.id.lift_name);
        if (liftName != null) {
            setupSetData(view);
        }

        return view;
    }

    protected void setupSetData(View view) {
        TextView liftName = (TextView) view.findViewById(R.id.lift_name);
        TextView reps = (TextView) view.findViewById(R.id.reps);
        TextView percentage = (TextView) view.findViewById(R.id.percentage);
        TextView units = (TextView) view.findViewById(R.id.units);
        liftName.setText(set.lift.name);
        reps.setText(set.reps + "x");
        percentage.setText(set.percentage + "%");
        JSettings settings = (JSettings) JSettingsStore.instance().first();
        units.setText(settings.units);

        this.setWeightLabelText(view);
    }

    private void setWeightLabelText(View view) {
        TextView weight = (TextView) view.findViewById(R.id.weight);

        if (!this.set.lift.usesBar && set.roundedEffectiveWeight().equals(BigDecimal.ZERO)) {
            weight.setText("");
        } else {
            weight.setText(BigDecimals.print(this.set.roundedEffectiveWeight()));
        }
    }
}
