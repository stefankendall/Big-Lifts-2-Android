package com.stefankendall.BigLifts.views.fto.onerep;

import android.app.Activity;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import com.stefankendall.BigLifts.TestDataLoader;
import com.stefankendall.BigLifts.views.fto.barloading.AddPlateActivity;
import junit.framework.Assert;

public class OneRepMaxListAdapterTests extends ActivityInstrumentationTestCase2<OneRepMaxActivity> {
    public OneRepMaxListAdapterTests() {
        super(OneRepMaxActivity.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        new TestDataLoader().reset();
        Intent intent = new Intent(getInstrumentation().getTargetContext(), AddPlateActivity.class);
        setActivityIntent(intent);
    }

    public void testOneRepMaxEstimateBlankWhenFieldsBlank() {
        OneRepMaxListAdapter adapter = new OneRepMaxListAdapter(getActivity());
        adapter.oneRepValuesChanged();
        Assert.assertEquals(adapter.oneRepMaxEstimate.getValue(), "");
    }

    public void testOneRepMaxEstimateBlankWhenRepsBlank() {
        OneRepMaxListAdapter adapter = new OneRepMaxListAdapter(getActivity());
        adapter.weight.setValue("100");
        adapter.oneRepValuesChanged();
        Assert.assertEquals(adapter.oneRepMaxEstimate.getValue(), "");
    }

    public void testOneRepMaxEstimateBlankWhenWeightBlank() {
        OneRepMaxListAdapter adapter = new OneRepMaxListAdapter(getActivity());
        adapter.reps.setValue("100");
        adapter.oneRepValuesChanged();
        Assert.assertEquals(adapter.oneRepMaxEstimate.getValue(), "");
    }
}
