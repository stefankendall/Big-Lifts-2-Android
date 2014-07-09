package com.stefankendall.BigLifts.views.fto.lift;

import android.opengl.Visibility;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import com.stefankendall.BigLifts.App;
import com.stefankendall.BigLifts.BLTestCase;
import com.stefankendall.BigLifts.data.models.JWorkout;
import com.stefankendall.BigLifts.data.models.fto.JFTOWorkout;
import com.stefankendall.BigLifts.data.stores.JWorkoutStore;
import com.stefankendall.BigLifts.data.stores.fto.JFTOWorkoutStore;
import com.stefankendall.BigLifts.R;
import junit.framework.Assert;

public class FTOWorkoutSummaryCellTests extends BLTestCase {
    public void testDoesNotCheckDoneWorkouts() {
        JFTOWorkout ftoWorkout = (JFTOWorkout) JFTOWorkoutStore.instance().create();
        ftoWorkout.workout = (JWorkout) JWorkoutStore.instance().create();
        FTOWorkoutSummaryCell cell = new FTOWorkoutSummaryCell(ftoWorkout);
        View v = cell.fillView(null, LayoutInflater.from(App.getContext()));
        ImageView checkmark = (ImageView) v.findViewById(R.id.checkmark);
        Assert.assertEquals(checkmark.getVisibility(), View.GONE);
    }

    public void testChecksDoneWorkouts() {
        JFTOWorkout ftoWorkout = (JFTOWorkout) JFTOWorkoutStore.instance().create();
        ftoWorkout.workout = (JWorkout) JWorkoutStore.instance().create();
        ftoWorkout.done = true;
        FTOWorkoutSummaryCell cell = new FTOWorkoutSummaryCell(ftoWorkout);
        View v = cell.fillView(null, LayoutInflater.from(App.getContext()));
        ImageView checkmark = (ImageView) v.findViewById(R.id.checkmark);
        Assert.assertEquals(checkmark.getVisibility(), View.VISIBLE);
    }
}
