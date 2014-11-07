package com.stefankendall.BigLifts.views.cells;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.stefankendall.BigLifts.App;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.allprograms.colors.BLColors;
import com.stefankendall.BigLifts.data.models.JSet;
import com.stefankendall.BigLifts.data.models.JSettings;
import com.stefankendall.BigLifts.data.numbers.BigDecimals;
import com.stefankendall.BigLifts.data.stores.JSettingsStore;
import com.stefankendall.BigLifts.views.fto.lift.individual.SetChange;
import com.stefankendall.BigLifts.views.lists.CustomListItem;

import java.math.BigDecimal;

public class SetCell implements CustomListItem {
    protected final JSet set;
    protected final SetChange setChange;
    private boolean complete;

    public SetCell(JSet set, SetChange setChange) {
        this.set = set;
        this.setChange = setChange;
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

        Integer repsDone = set.reps == null ? 0 : set.reps;
        if (setChange.reps != null) {
            repsDone = setChange.reps;
        }

        if (set.amrap && setChange.reps == null) {
            reps.setText(repsDone + "+");
            reps.setTextColor(BLColors.amrapColor());
        } else {
            reps.setText(repsDone + "x");
            reps.setTextColor(App.getContext().getResources().getColor(android.R.color.black));
        }
        percentage.setText(set.percentage + "%");
        JSettings settings = (JSettings) JSettingsStore.instance().first();
        units.setText(settings.units);

        this.setWeightLabelText(view);

        if (complete) {
            view.setBackgroundColor(Color.rgb(179, 255, 178));
        } else {
            view.setBackgroundColor(App.getContext().getResources().getColor(android.R.color.transparent));
        }
    }

    private void setWeightLabelText(View view) {
        TextView weight = (TextView) view.findViewById(R.id.weight);

        if (this.setChange.weight != null) {
            weight.setText(BigDecimals.print(this.setChange.weight));
        } else if (!this.set.lift.usesBar && set.roundedEffectiveWeight().equals(BigDecimal.ZERO)) {
            weight.setText("");
        } else {
            weight.setText(BigDecimals.print(this.set.roundedEffectiveWeight()));
        }
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
