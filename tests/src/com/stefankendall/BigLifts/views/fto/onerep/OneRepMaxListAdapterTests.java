package com.stefankendall.BigLifts.views.fto.onerep;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import com.stefankendall.BigLifts.TestDataLoader;
import com.stefankendall.BigLifts.views.fto.barloading.AddPlateActivity;
import junit.framework.Assert;

public class OneRepMaxListAdapterTests extends ActivityInstrumentationTestCase2<OneRepMaxActivity> {
    private OneRepMaxFragment fragment;

    public OneRepMaxListAdapterTests() {
        super(OneRepMaxActivity.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        new TestDataLoader().reset();
        Intent intent = new Intent(getInstrumentation().getTargetContext(), AddPlateActivity.class);
        setActivityIntent(intent);
        this.fragment = (OneRepMaxFragment) getActivity().fragment;
    }

    public void testOneRepMaxEstimateBlankWhenFieldsBlank() {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                final OneRepMaxListAdapter adapter = (OneRepMaxListAdapter) OneRepMaxListAdapterTests.this.fragment.getListAdapter();
                adapter.oneRepValuesChanged();
                Assert.assertEquals(adapter.oneRepMaxEstimate.getValue(), "");
            }
        });
        getInstrumentation().waitForIdleSync();
    }

    public void testOneRepMaxEstimateBlankWhenRepsBlank() {
        final OneRepMaxListAdapter adapter = (OneRepMaxListAdapter) this.fragment.getListAdapter();
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                adapter.weight.setValue("100");
                adapter.oneRepValuesChanged();
            }
        });
        getInstrumentation().waitForIdleSync();

        Assert.assertEquals(adapter.oneRepMaxEstimate.getValue(), "");
    }

    public void testOneRepMaxEstimateBlankWhenWeightBlank() {
        final OneRepMaxListAdapter adapter = (OneRepMaxListAdapter) this.fragment.getListAdapter();
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                adapter.reps.setValue("100");
                adapter.oneRepValuesChanged();
            }
        });
        getInstrumentation().waitForIdleSync();

        Assert.assertEquals(adapter.oneRepMaxEstimate.getValue(), "");
    }

    public void testOneRepMaxEstimateChangesWithValues() {
        final OneRepMaxListAdapter adapter = (OneRepMaxListAdapter) this.fragment.getListAdapter();
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                adapter.weight.setValue("100");
                adapter.reps.setValue("3");
                adapter.oneRepValuesChanged();
            }
        });
        getInstrumentation().waitForIdleSync();
        Assert.assertEquals(adapter.oneRepMaxEstimate.getValue(), "110");

        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                adapter.weight.setValue("100");
                adapter.reps.setValue("4");
                adapter.oneRepValuesChanged();
            }
        });
        getInstrumentation().waitForIdleSync();
        Assert.assertEquals(adapter.oneRepMaxEstimate.getValue(), "113.3");
    }
}
