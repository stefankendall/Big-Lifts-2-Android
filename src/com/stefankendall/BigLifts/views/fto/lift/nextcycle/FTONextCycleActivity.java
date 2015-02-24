package com.stefankendall.BigLifts.views.fto.lift.nextcycle;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.stefankendall.BigLifts.BLActivity;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.views.fto.lift.individual.FTOCycleAdjustor;
import com.stefankendall.BigLifts.views.fto.lift.individual.FTOLiftIncrementer;

public class FTONextCycleActivity extends BLActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Next Cycle");
        setContentView(R.layout.next_cycle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.single_button, menu);
        MenuItem item = menu.findItem(R.id.button);
        item.setTitle("Done");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        new FTOCycleAdjustor().nextCycle();
        FTOLiftIncrementer.incrementLifts();
        finish();
        return true;
    }
}
