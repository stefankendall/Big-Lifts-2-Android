package com.stefankendall.BigLifts.views.fto.barloading;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.data.models.JPlate;
import com.stefankendall.BigLifts.data.models.JSettings;
import com.stefankendall.BigLifts.data.stores.JPlateStore;
import com.stefankendall.BigLifts.data.stores.JSettingsStore;
import com.stefankendall.BigLifts.views.lists.CustomListItem;

public class PlateCell implements CustomListItem {
    private final JPlate plate;
    private final RefreshingList refreshingList;

    public PlateCell(JPlate plate, RefreshingList refreshingList) {
        this.plate = plate;
        this.refreshingList = refreshingList;
    }

    @Override
    public View fillView(View view, LayoutInflater inflater) {
        if (view == null) {
            view = inflater.inflate(R.layout.plate_cell, null);
        }

        TextView plateWeight = (TextView) view.findViewById(R.id.plate_weight);
        TextView plateCount = (TextView) view.findViewById(R.id.plate_count);
        Button plusButton = (Button) view.findViewById(R.id.plus);
        Button minusButton = (Button) view.findViewById(R.id.minus);
        Button deleteButton = (Button) view.findViewById(R.id.delete);
        if (plateWeight != null) {
            if (this.plate.count > 0) {
                plateCount.setVisibility(View.VISIBLE);
                deleteButton.setVisibility(View.GONE);
            } else {
                plateCount.setVisibility(View.GONE);
                deleteButton.setVisibility(View.VISIBLE);
            }

            JSettings settings = (JSettings) JSettingsStore.instance().first();
            plateWeight.setText(this.plate.weight + " " + settings.units);
            plateCount.setText(this.plate.count + " plates");

            plusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PlateCell.this.addPlates(2);
                }
            });

            minusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PlateCell.this.addPlates(-2);
                }
            });

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PlateCell.this.delete();
                }
            });
        }
        return view;
    }

    protected void delete() {
        JPlateStore.instance().remove(this.plate);
        this.refreshingList.refresh();
    }

    protected void addPlates(int plates) {
        this.plate.count += plates;
        if (this.plate.count < 0) {
            this.plate.count = 0;
        }
        this.refreshingList.refresh();
    }
}
