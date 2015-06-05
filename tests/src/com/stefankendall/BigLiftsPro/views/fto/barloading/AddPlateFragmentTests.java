package com.stefankendall.BigLiftsPro.views.fto.barloading;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import com.stefankendall.BigLiftsPro.TestDataLoader;
import junit.framework.Assert;

public class AddPlateFragmentTests extends ActivityInstrumentationTestCase2<AddPlateActivity> {
    public AddPlateFragmentTests() {
        super(AddPlateActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        new TestDataLoader().reset();
        Intent intent = new Intent(getInstrumentation().getTargetContext(), AddPlateActivity.class);
        setActivityIntent(intent);
    }

    public void testSaveStartsDisabled() {
        AddPlateFragment fragment = (AddPlateFragment) getActivity().fragment;
        Assert.assertFalse(fragment.saveButton.isEnabled());
    }

    public void testSaveDisabledWhenOnlyOneFieldSet() {
        final AddPlateFragment fragment = (AddPlateFragment) getActivity().fragment;
        final AddPlateListAdapter listAdapter = (AddPlateListAdapter) fragment.getListAdapter();
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                listAdapter.weight.setValue("100");
                fragment.fieldChanged();
            }
        });
        getInstrumentation().waitForIdleSync();

        Assert.assertFalse(fragment.saveButton.isEnabled());
    }

    public void testSaveEnabledWhenBothFieldsSet() {
        final AddPlateFragment fragment = (AddPlateFragment) getActivity().fragment;
        final AddPlateListAdapter listAdapter = (AddPlateListAdapter) fragment.getListAdapter();
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                listAdapter.weight.setValue("100");
                listAdapter.count.setValue("2");
                fragment.fieldChanged();
            }
        });

        getInstrumentation().waitForIdleSync();
        Assert.assertTrue(fragment.saveButton.isEnabled());
    }
}
