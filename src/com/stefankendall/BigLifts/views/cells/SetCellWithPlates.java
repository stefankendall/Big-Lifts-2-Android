package com.stefankendall.BigLifts.views.cells;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.google.common.base.Joiner;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.allprograms.formulas.bar.BarCalculator;
import com.stefankendall.BigLifts.data.models.JBar;
import com.stefankendall.BigLifts.data.models.JSet;
import com.stefankendall.BigLifts.data.stores.JBarStore;
import com.stefankendall.BigLifts.data.stores.JPlateStore;
import com.stefankendall.BigLifts.views.fto.lift.individual.SetChange;

import java.math.BigDecimal;
import java.util.List;

public class SetCellWithPlates extends SetCell {
    public SetCellWithPlates(JSet set, SetChange setChange) {
        super(set, setChange);
    }

    public View fillView(View view, LayoutInflater inflater) {
        if (view == null) {
            view = inflater.inflate(R.layout.set_cell_with_plates, null);
        }

        TextView liftName = (TextView) view.findViewById(R.id.lift_name);
        if (liftName != null) {
            setupSetData(view);
            TextView platesText = (TextView) view.findViewById(R.id.plates);
            if (!this.set.lift.usesBar) {
                platesText.setVisibility(View.GONE);
                platesText.setText("");
            } else {
                platesText.setVisibility(View.VISIBLE);

                JBar bar = (JBar) JBarStore.instance().first();
                BarCalculator calculator = new BarCalculator(JPlateStore.instance().findAll(), bar.weight);
                BigDecimal weightToMake = this.set.roundedEffectiveWeight();
                if (this.setChange.weight != null) {
                    weightToMake = this.setChange.weight;
                }
                List<BigDecimal> plates = calculator.platesToMakeWeight(weightToMake);
                String platesString = "";
                if (plates.size() > 0) {
                    platesString = "[" + Joiner.on(", ").join(plates) + "]";
                }
                platesText.setText(platesString);
            }
        }

        return view;
    }

}
