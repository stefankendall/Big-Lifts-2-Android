package com.stefankendall.BigLifts.views.fto.edit;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.stefankendall.BigLifts.App;
import com.stefankendall.BigLifts.BLTestCase;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.data.models.fto.JFTOLift;
import com.stefankendall.BigLifts.data.models.fto.JFTOSettings;
import com.stefankendall.BigLifts.data.stores.fto.JFTOLiftStore;
import com.stefankendall.BigLifts.data.stores.fto.JFTOSettingsStore;
import junit.framework.Assert;

import java.math.BigDecimal;

public class FTOEditLiftCellTests extends BLTestCase {
    public void testSetsValues() {
        JFTOLift lift = (JFTOLift) JFTOLiftStore.instance().create();
        lift.name = "A";
        lift.weight = new BigDecimal("120");

        JFTOSettings settings = (JFTOSettings) JFTOSettingsStore.instance().first();
        settings.trainingMax = new BigDecimal("50");
        FTOEditLiftCell cell = new FTOEditLiftCell(lift);

        View view = cell.fillView(null, LayoutInflater.from(App.getContext()));

        TextView name = (TextView) view.findViewById(R.id.lift_name);
        EditText max = (EditText) view.findViewById(R.id.max);
        EditText trainingMax = (EditText) view.findViewById(R.id.training_max);

        Assert.assertNotNull(name);

        Assert.assertEquals(name.getText(), "A");
        Assert.assertEquals(max.getText().toString(), "120");
        Assert.assertEquals(trainingMax.getText().toString(), "60");
    }

    public void testHandlesEmpty() {
        JFTOLift lift = (JFTOLift) JFTOLiftStore.instance().create();
        FTOEditLiftCell cell = new FTOEditLiftCell(lift);
        cell.fillView(null, LayoutInflater.from(App.getContext()));
        cell.trainingMaxChanged("");
        cell.maxChanged("");
        Assert.assertEquals(lift.weight, BigDecimal.ZERO);
    }

    public void testDoesNotCrashWith0Weight() {
        JFTOLift lift = (JFTOLift) JFTOLiftStore.instance().create();
        lift.weight = new BigDecimal("0");
        FTOEditLiftCell cell = new FTOEditLiftCell(lift);

        View view = cell.fillView(null, LayoutInflater.from(App.getContext()));
        EditText trainingMax = (EditText) view.findViewById(R.id.training_max);
        Assert.assertEquals(trainingMax.getText().toString(), "");
    }

    public void testUpdatesMax() {
        JFTOLift lift = (JFTOLift) JFTOLiftStore.instance().create();
        lift.weight = new BigDecimal("0");
        FTOEditLiftCell cell = new FTOEditLiftCell(lift);

        View view = cell.fillView(null, LayoutInflater.from(App.getContext()));
        cell.maxChanged("100");
        Assert.assertEquals(lift.weight, new BigDecimal("100"));
        EditText trainingMaxField = (EditText) view.findViewById(R.id.training_max);
        Assert.assertEquals(trainingMaxField.getText().toString(), "90");
    }

    public void testEditTrainingMaxUpdatesMax() {
        JFTOLift lift = (JFTOLift) JFTOLiftStore.instance().create();
        FTOEditLiftCell cell = new FTOEditLiftCell(lift);
        cell.fillView(null, LayoutInflater.from(App.getContext()));
        cell.trainingMaxChanged("105");
        Assert.assertEquals(lift.weight, new BigDecimal("116.67"));
        Assert.assertEquals(cell.maxField.getText().toString(), "116.7");
    }
}
