package com.stefankendall.BigLifts.views.fto.track.edit;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import com.stefankendall.BigLifts.data.models.JWorkoutLog;

import java.util.Calendar;

public class FTOEditDateDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private JWorkoutLog workoutLog;
    private DialogInterface delegate;

    public static DialogFragment newInstance(JWorkoutLog workoutLog, DialogInterface delegate) {
        FTOEditDateDialogFragment fragment = new FTOEditDateDialogFragment();
        fragment.workoutLog = workoutLog;
        fragment.delegate = delegate;
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        c.setTime(this.workoutLog.date);
        return new DatePickerDialog(getActivity(), this, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        delegate.dismiss();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, monthOfYear);
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        this.workoutLog.date = cal.getTime();
    }
}
