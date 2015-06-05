package com.stefankendall.BigLiftsPro.views.fto.lift.individual;

import android.content.Intent;
import android.os.Bundle;
import android.test.ActivityInstrumentationTestCase2;
import com.stefankendall.BigLiftsPro.TestDataLoader;
import com.stefankendall.BigLiftsPro.data.models.JWorkoutLog;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOWorkout;
import com.stefankendall.BigLiftsPro.data.stores.JWorkoutLogStore;
import com.stefankendall.BigLiftsPro.data.stores.fto.JFTOWorkoutStore;
import junit.framework.Assert;

import java.util.List;

public class FTOIndividualWorkoutFragmentTests extends ActivityInstrumentationTestCase2<FTOIndividualWorkoutActivity> {
    private FTOIndividualWorkoutFragment fragment;
    private JFTOWorkout ftoWorkout;

    public FTOIndividualWorkoutFragmentTests() {
        super(FTOIndividualWorkoutActivity.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        new TestDataLoader().reset();

        this.ftoWorkout = (JFTOWorkout) JFTOWorkoutStore.instance().findAllWhere("week", 1).get(0);
        Assert.assertNotNull(JFTOWorkoutStore.instance().find("uuid", this.ftoWorkout.uuid));

        Intent intent = new Intent(getInstrumentation().getTargetContext(), FTOIndividualWorkoutActivity.class);
        intent.putExtra(FTOIndividualWorkoutActivity.FTO_WORKOUT_UUID, this.ftoWorkout.uuid);
        setActivityIntent(intent);
        this.fragment = (FTOIndividualWorkoutFragment) getActivity().fragment;
    }

    public void testTappingDoneButtonSavesLog() {
        this.fragment.onDoneTapped();
        List<JWorkoutLog> logs = (List<JWorkoutLog>) JWorkoutLogStore.instance().findAllWhere("name", "5/3/1");
        Assert.assertEquals(logs.size(), 1);
        JWorkoutLog workoutLog = (JWorkoutLog) JWorkoutLogStore.instance().find("name", "5/3/1");
        Assert.assertNotNull(workoutLog.date);
        Assert.assertTrue(this.ftoWorkout.done);
    }

    public void testRestoresWorkoutOnRestore() {
        Bundle bundle = new Bundle();
        this.fragment.save(bundle);
        this.fragment.ftoWorkout = null;
        this.fragment.restore(bundle);

        Assert.assertNotNull(this.fragment.ftoWorkout);
    }

//    public void testDoesNotSave0RepsInLog() {
//        self.controller.tappedSetRow = @0;
//        [self.controller repsChanged:@0];
//        [self.controller doneButtonTapped:nil];
//        JWorkoutLog *workoutLog = [[JWorkoutLogStore instance] find:@"name" value:@"5/3/1"];
//        STAssertEquals((int) [workoutLog.sets count], 5, @"");
//    }
}
