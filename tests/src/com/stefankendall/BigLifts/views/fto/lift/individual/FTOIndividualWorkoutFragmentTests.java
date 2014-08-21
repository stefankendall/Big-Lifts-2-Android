package com.stefankendall.BigLifts.views.fto.lift.individual;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import com.stefankendall.BigLifts.TestDataLoader;
import com.stefankendall.BigLifts.views.fto.barloading.AddPlateActivity;
import junit.framework.Assert;

public class FTOIndividualWorkoutFragmentTests extends ActivityInstrumentationTestCase2<FTOIndividualWorkoutActivity> {
    private FTOIndividualWorkoutFragment fragment;

    public FTOIndividualWorkoutFragmentTests() {
        super(FTOIndividualWorkoutActivity.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        new TestDataLoader().reset();
        Intent intent = new Intent(getInstrumentation().getTargetContext(), FTOIndividualWorkoutActivity.class);
        setActivityIntent(intent);
        this.fragment = (FTOIndividualWorkoutFragment) getActivity().fragment;
    }

    public void testTappingDoneButtonSavesLog(){
//        this.fragment.onDoneTapped();
//        List<JWorkoutLog> logs = JWorkoutLogStore.instance().findAllWhere("name", "5/3/1");
//        Assert.assertEquals(logs.size(), 1);
//        JWorkoutLog workoutLog = JWorkoutLogStore.instance().find("name", "5/3/1");
//        Assert.assertNotNull(workoutLog.date);
//        Assert.assertTrue(this.ftoWorkout.done);
    }

    public void testDoesNotSave0RepsInLog(){
//        self.controller.tappedSetRow = @0;
//        [self.controller repsChanged:@0];
//        [self.controller doneButtonTapped:nil];
//        JWorkoutLog *workoutLog = [[JWorkoutLogStore instance] find:@"name" value:@"5/3/1"];
//        STAssertEquals((int) [workoutLog.sets count], 5, @"");
    }
}
