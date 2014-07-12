package com.stefankendall.BigLifts.views.fto.barloading;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.data.models.JPlate;
import com.stefankendall.BigLifts.data.models.JSettings;
import com.stefankendall.BigLifts.data.stores.JSettingsStore;
import com.stefankendall.BigLifts.views.lists.CustomListItem;

import java.math.BigDecimal;

public class PlateCell implements CustomListItem {
    private final JPlate plate;

    public PlateCell(JPlate plate) {
        this.plate = plate;
    }

    @Override
    public View fillView(View view, LayoutInflater inflater) {
        if (view == null) {
            view = inflater.inflate(R.layout.plate_cell, null);
        }

        TextView plateWeight = (TextView) view.findViewById(R.id.plate_weight);
        TextView plateCount = (TextView) view.findViewById(R.id.plate_count);
        if (plateWeight != null) {
            JSettings settings = (JSettings) JSettingsStore.instance().first();
            plateWeight.setText(plate.weight + " " + settings.units);
            plateCount.setText(plate.count + " plates");
        }
        return view;
    }
}
