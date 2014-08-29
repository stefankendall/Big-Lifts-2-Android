package com.stefankendall.BigLifts.views.cells;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.allprograms.formulas.bar.BarCalculator;
import com.stefankendall.BigLifts.data.models.JBar;
import com.stefankendall.BigLifts.data.models.JSet;
import com.stefankendall.BigLifts.data.stores.JBarStore;
import com.stefankendall.BigLifts.data.stores.JPlateStore;

public class SetCellWithPlates extends SetCell {
    public SetCellWithPlates(JSet set) {
        super(set);
    }

    public View fillView(View view, LayoutInflater inflater) {
        if (view == null) {
            view = inflater.inflate(R.layout.set_cell_with_plates, null);
        }

        TextView liftName = (TextView) view.findViewById(R.id.lift_name);
        if (liftName != null) {
            setupSetData(view);
            TextView plates = (TextView) view.findViewById(R.id.plates);
            if (!this.set.lift.usesBar) {
                plates.setText("");
            }

            JBar bar = (JBar) JBarStore.instance().first();
            BarCalculator calculator = new BarCalculator(JPlateStore.instance().findAll(), bar.weight);
        }

        return view;
    }

}
