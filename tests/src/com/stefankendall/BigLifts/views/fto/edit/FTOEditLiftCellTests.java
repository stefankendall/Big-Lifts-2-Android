package com.stefankendall.BigLifts.views.fto.edit;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.stefankendall.BigLifts.App;
import com.stefankendall.BigLifts.BLTestCase;
import com.stefankendall.BigLifts.data.models.fto.JFTOLift;
import com.stefankendall.BigLifts.data.models.fto.JFTOSettings;
import com.stefankendall.BigLifts.data.stores.fto.JFTOLiftStore;
import com.stefankendall.BigLifts.data.stores.fto.JFTOSettingsStore;
import com.stefankendall.BigLifts.tests.R;
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

        View view = new View(App.getContext());
        cell.fillView(view, LayoutInflater.from(App.getContext()));

        TextView name = (TextView) view.findViewById(R.id.lift_name);
        EditText max = (EditText) view.findViewById(R.id.max);
        EditText trainingMax = (EditText) view.findViewById(R.id.training_max);

        Assert.assertEquals(name.getText(), "A");
        Assert.assertEquals(max.getText().toString(), "120");
        Assert.assertEquals(trainingMax.getText().toString(), "60");
    }
}
