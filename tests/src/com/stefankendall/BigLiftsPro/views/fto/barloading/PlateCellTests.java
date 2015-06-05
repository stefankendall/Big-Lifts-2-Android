package com.stefankendall.BigLiftsPro.views.fto.barloading;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import com.stefankendall.BigLiftsPro.App;
import com.stefankendall.BigLiftsPro.BLTestCase;
import com.stefankendall.BigLiftsPro.R;
import com.stefankendall.BigLiftsPro.data.models.JPlate;
import com.stefankendall.BigLiftsPro.data.stores.JPlateStore;
import junit.framework.Assert;

import java.math.BigDecimal;

public class PlateCellTests extends BLTestCase {
    public void testShowsDeleteButtonWhenCountIs0() {
        JPlate p = (JPlate) JPlateStore.instance().create();
        p.count = 0;
        p.weight = new BigDecimal("45");
        PlateCell cell = getPlateCell(p);
        View v = cell.fillView(null, LayoutInflater.from(App.getContext()));
        Button deleteButton = (Button) v.findViewById(R.id.delete);
        View plateCount = v.findViewById(R.id.plate_count);
        Assert.assertEquals(deleteButton.getVisibility(), View.VISIBLE);
        Assert.assertEquals(plateCount.getVisibility(), View.GONE);
    }

    public void testHidesDeleteButtonWhenCountIsNot0() {
        JPlate p = (JPlate) JPlateStore.instance().create();
        p.count = 2;
        p.weight = new BigDecimal("45");
        PlateCell cell = getPlateCell(p);
        View v = cell.fillView(null, LayoutInflater.from(App.getContext()));
        Button deleteButton = (Button) v.findViewById(R.id.delete);
        View plateCount = v.findViewById(R.id.plate_count);
        Assert.assertEquals(deleteButton.getVisibility(), View.GONE);
        Assert.assertEquals(plateCount.getVisibility(), View.VISIBLE);
    }

    public void testTappingDeleteButtonRemovesPlate() {
        JPlate p = (JPlate) JPlateStore.instance().create();
        p.count = 2;
        p.weight = new BigDecimal("45");
        PlateCell cell = getPlateCell(p);
        View v = cell.fillView(null, LayoutInflater.from(App.getContext()));
        Button deleteButton = (Button) v.findViewById(R.id.delete);
        deleteButton.callOnClick();
        Assert.assertFalse(JPlateStore.instance().findAll().contains(p));
    }

    public void testDoesNotGoBelow0() {
        JPlate p = (JPlate) JPlateStore.instance().create();
        p.count = 0;
        p.weight = new BigDecimal("45");
        PlateCell cell = getPlateCell(p);
        cell.addPlates(-2);
        Assert.assertEquals(p.count, 0);
    }

    private PlateCell getPlateCell(JPlate p) {
        return new PlateCell(p, new RefreshingList() {
            @Override
            public void refresh() {
            }
        });
    }
}
