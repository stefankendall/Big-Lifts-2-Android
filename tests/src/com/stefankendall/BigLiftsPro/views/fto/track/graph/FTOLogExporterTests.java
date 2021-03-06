package com.stefankendall.BigLiftsPro.views.fto.track.graph;

import com.stefankendall.BigLiftsPro.BLTestCase;
import com.stefankendall.BigLiftsPro.data.models.JSetLog;
import com.stefankendall.BigLiftsPro.data.models.JWorkoutLog;
import com.stefankendall.BigLiftsPro.data.stores.JSetLogStore;
import com.stefankendall.BigLiftsPro.data.stores.JWorkoutLogStore;
import junit.framework.Assert;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FTOLogExporterTests extends BLTestCase {
    public void testConvertsWorkoutLogsIntoCsv() throws ParseException {
        JWorkoutLog workoutLog1 = (JWorkoutLog) JWorkoutLogStore.instance().create();
        workoutLog1.name = "5/3/1";
        workoutLog1.date = this.dateFor("2013-01-23");

        JSetLog setLog1 = (JSetLog) JSetLogStore.instance().create();
        setLog1.weight = new BigDecimal(200);
        setLog1.reps = 3;
        setLog1.name = "Deadlift";

        JSetLog setLog2 = (JSetLog) JSetLogStore.instance().create();
        setLog2.weight = new BigDecimal(210);
        setLog2.reps = 2;
        setLog2.name = "Deadlift";
        workoutLog1.sets.add(setLog1);
        workoutLog1.sets.add(setLog2);

        JWorkoutLog workoutLog2 = (JWorkoutLog) JWorkoutLogStore.instance().create();
        workoutLog2.name = "5/3/1";
        workoutLog2.date = this.dateFor("2013-02-01");

        JSetLog setLog2_1 = (JSetLog) JSetLogStore.instance().create();
        setLog2_1.weight = new BigDecimal(100);
        setLog2_1.reps = 5;
        setLog2_1.name = "Press";
        workoutLog2.addSet(setLog2_1);

        String expected = "name,date,weight,reps,estimated max\n";
        expected += "Press,2/1/2013,100,5,116.7\n";
        expected += "Deadlift,1/23/2013,210,2,224\n";

        Assert.assertEquals(FTOLogExporter.csv(), expected);
    }

    public void testDoesNotCrashForNullLiftName() {
        JWorkoutLog workoutLog1 = JWorkoutLogStore.instance().create("5/3/1", new Date());
        JSetLog setLog1 = (JSetLog) JSetLogStore.instance().create();
        workoutLog1.addSet(setLog1);
        FTOLogExporter.csv();
    }

    public Date dateFor(String dateString) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(dateString);
    }
}
