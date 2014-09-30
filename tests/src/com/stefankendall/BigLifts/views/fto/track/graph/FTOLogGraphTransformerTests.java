package com.stefankendall.BigLifts.views.fto.track.graph;

import com.stefankendall.BigLifts.BLTestCase;
import com.stefankendall.BigLifts.data.models.JSetLog;
import com.stefankendall.BigLifts.data.models.JWorkoutLog;
import com.stefankendall.BigLifts.data.stores.JSetLogStore;
import com.stefankendall.BigLifts.data.stores.JWorkoutLogStore;

import java.math.BigDecimal;

public class FTOLogGraphTransformerTests extends BLTestCase {
    public void testLogToChartEntry(){
        JSetLog set = (JSetLog) JSetLogStore.instance().create();
        set.weight = new BigDecimal(200);
        set.reps = 3;
        JWorkoutLog workoutLog = (JWorkoutLog) JWorkoutLogStore.instance().create();
        workoutLog.sets.add(set);

        NSDateFormatter *df = [[NSDateFormatter alloc] init];
        [df setDateFormat:@"yyyy-MM-dd"];
        workoutLog.date = [df dateFromString:@"2013-01-12"];

        NSDictionary *chartEntry = [[FTOLogGraphTransformer new] logToChartEntry:workoutLog withSet:set];
        NSDictionary *expected = @{
            @"date" : @{
                @"year" : @2013,
                @"month" : @1,
                @"day" : @12
            },
            @"weight" : N(220)
        };
        STAssertEqualObjects(chartEntry, expected, @"");
    }

}
