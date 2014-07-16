package com.stefankendall.BigLifts.views.fto.barloading;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import com.stefankendall.BigLifts.TestDataLoader;
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

    public void testSaveDisabledWhenOnlyOneFieldSet(){
        Assert.fail();

        AddPlateFragment fragment = (AddPlateFragment) getActivity().fragment;
        Assert.assertFalse(fragment.saveButton.isEnabled());
    }

    public void testSaveEnabledWhenBothFieldsSet() {
        Assert.fail();

        AddPlateFragment fragment = (AddPlateFragment) getActivity().fragment;
        Assert.assertTrue(fragment.saveButton.isEnabled());
    }
}
