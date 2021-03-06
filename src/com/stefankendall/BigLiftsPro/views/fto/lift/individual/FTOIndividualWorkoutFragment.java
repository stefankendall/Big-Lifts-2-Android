package com.stefankendall.BigLiftsPro.views.fto.lift.individual;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import com.crashlytics.android.Crashlytics;
import com.stefankendall.BigLiftsPro.App;
import com.stefankendall.BigLiftsPro.BLActivity;
import com.stefankendall.BigLiftsPro.R;
import com.stefankendall.BigLiftsPro.data.models.JSet;
import com.stefankendall.BigLiftsPro.data.models.JSetLog;
import com.stefankendall.BigLiftsPro.data.models.JWorkoutLog;
import com.stefankendall.BigLiftsPro.data.models.fto.JFTOWorkout;
import com.stefankendall.BigLiftsPro.data.stores.JSetLogStore;
import com.stefankendall.BigLiftsPro.data.stores.JWorkoutLogStore;
import com.stefankendall.BigLiftsPro.data.stores.fto.JFTOWorkoutStore;
import com.stefankendall.BigLiftsPro.views.BLListFragment;
import com.stefankendall.BigLiftsPro.views.fto.lift.individual.change.FTOSetChangeFormActivity;
import com.stefankendall.BigLiftsPro.views.fto.lift.individual.repsToBeat.FTORepsToBeatActivity;
import com.stefankendall.BigLiftsPro.views.fto.lift.individual.timer.RestCountdownCell;
import com.stefankendall.BigLiftsPro.views.fto.lift.individual.timer.RestTimer;
import com.stefankendall.BigLiftsPro.views.fto.lift.individual.timer.TickObserver;
import com.stefankendall.BigLiftsPro.views.fto.track.FTOTrackViewActivity;
import com.stefankendall.BigLiftsPro.views.lists.CustomListItem;
import com.stefankendall.BigLiftsPro.views.lists.SimpleListAdapter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class FTOIndividualWorkoutFragment extends BLListFragment implements TickObserver {
    protected JFTOWorkout ftoWorkout;
    private Integer tappedSetRow = null;
    public static int SET_CHANGE_REQUEST_CODE = 0;
    private boolean stoppingTimer = false;

    public static FTOIndividualWorkoutFragment newInstance(JFTOWorkout ftoWorkout) {
        FTOIndividualWorkoutFragment fragment = new FTOIndividualWorkoutFragment();
        fragment.ftoWorkout = ftoWorkout;
        return fragment;
    }

    @Override
    protected void save(Bundle outState) {
        outState.putString("uuid", this.ftoWorkout.uuid);
        if (tappedSetRow != null) {
            outState.putInt("tappedSetRow", tappedSetRow);
        }
    }

    @Override
    protected void restore(Bundle savedInstanceState) {
        String uuid = savedInstanceState.getString("uuid");
        this.ftoWorkout = (JFTOWorkout) JFTOWorkoutStore.instance().find("uuid", uuid);
        this.tappedSetRow = savedInstanceState.getInt("tappedSetRow", -1);
        this.tappedSetRow = this.tappedSetRow < 0 ? null : this.tappedSetRow;
        ((NotificationManager) App.getContext().getSystemService(Context.NOTIFICATION_SERVICE)).cancelAll();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        FTOWorkoutChangeCache.instance().clearCompletedSets();
        this.setListAdapter(new FTOIndividualWorkoutListAdapter(this.getActivity(), this.ftoWorkout));
        this.setHasOptionsMenu(true);
        ((NotificationManager) App.getContext().getSystemService(Context.NOTIFICATION_SERVICE)).cancelAll();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                FTOIndividualWorkoutListAdapter adapter = (FTOIndividualWorkoutListAdapter) adapterView.getAdapter();
                int setNumber = adapter.setNumberForPosition(position);
                if (setNumber >= 0) {
                    FTOIndividualWorkoutFragment.this.markSetComplete(setNumber);
                } else if (adapter.getItem(position) instanceof RestCountdownCell) {
                    FTOIndividualWorkoutFragment.this.stoppingTimer = true;
                    RestTimer.instance().stop();
                }
                return true;
            }
        });
    }

    private void markSetComplete(int setIndex) {
        FTOWorkoutChangeCache.instance().toggleComplete(setIndex);
        ((SimpleListAdapter) this.getListAdapter()).reload();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, final MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fto_individual_workout_menu, menu);
        MenuItem doneButton = menu.findItem(R.id.done);
        if (this.ftoWorkout.done) {
            doneButton.setTitle("Not Done");
            doneButton.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    ftoWorkout.done = false;
                    getActivity().invalidateOptionsMenu();
                    return false;
                }
            });
        } else {
            doneButton.setTitle("Done");
            doneButton.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    FTOIndividualWorkoutFragment.this.onDoneTapped();
                    return false;
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int secondsToRest = 0;
        if (item.getItemId() == R.id.rest_1_min) {
            secondsToRest = 60;
        } else if (item.getItemId() == R.id.rest_2_min) {
            secondsToRest = 120;
        } else if (item.getItemId() == R.id.rest_custom_min) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            final EditText restTimeInSeconds = new EditText(getActivity());
            restTimeInSeconds.setInputType(InputType.TYPE_CLASS_NUMBER);
            restTimeInSeconds.setText(RestTimer.instance().getCustomSeconds() + "");
            builder.setTitle("Rest time in seconds?").setView(restTimeInSeconds).
                    setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (!restTimeInSeconds.getText().toString().equals("")) {
                                int secondsToRest = Integer.parseInt(restTimeInSeconds.getText().toString());
                                RestTimer.instance().setCustomSeconds(secondsToRest);
                                FTOIndividualWorkoutFragment.this.startRest(secondsToRest);
                            }
                        }
                    }).create().show();
        }

        if (secondsToRest > 0) {
            startRest(secondsToRest);
        }

        return true;
    }

    private void startRest(int secondsToRest) {
        ((NotificationManager) App.getContext().getSystemService(Context.NOTIFICATION_SERVICE)).cancelAll();
        RestTimer.instance().setTime(secondsToRest);
        RestTimer.instance().addTickObserver(this);
        this.setListAdapter(new FTOIndividualWorkoutListAdapter(this.getActivity(), this.ftoWorkout));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        FTOIndividualWorkoutListAdapter adapter = (FTOIndividualWorkoutListAdapter) this.getListAdapter();
        CustomListItem item = (CustomListItem) adapter.getItem(position);
        if (item instanceof FTOLiftWorkoutToolbar) {
            Intent intent = new Intent(this.getActivity(), FTORepsToBeatActivity.class);
            intent.putExtra(FTOIndividualWorkoutActivity.FTO_WORKOUT_UUID, this.ftoWorkout.uuid);
            startActivity(intent);
        } else {
            int setNumber = adapter.setNumberForPosition(position);
            if (setNumber >= 0) {
                this.tappedSetRow = setNumber;
                SetChange.reset();
                SetChange existingChange = FTOWorkoutChangeCache.instance().changeForWorkout(this.ftoWorkout, this.tappedSetRow);
                SetChange.instance().weight = existingChange.weight;
                SetChange.instance().reps = existingChange.reps;
                SetChange.instance().modifyingSet = this.ftoWorkout.workout.sets.get(setNumber);
                Intent intent = new Intent(this.getActivity(), FTOSetChangeFormActivity.class);
                startActivityForResult(intent, SET_CHANGE_REQUEST_CODE);
            }
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == SET_CHANGE_REQUEST_CODE) {
            Crashlytics.log("Set change for row: " + this.tappedSetRow + " workout uuid: " + this.ftoWorkout.uuid);
            SetChange setChange = FTOWorkoutChangeCache.instance().changeForWorkout(this.ftoWorkout, this.tappedSetRow);
            if (SetChange.instance().weight != null) {
                setChange.weight = SetChange.instance().weight;
            }
            if (SetChange.instance().reps != null) {
                setChange.reps = SetChange.instance().reps;
            }

            SimpleListAdapter adapter = (SimpleListAdapter) this.getListAdapter();
            adapter.reload();
        }
    }

    public void onDoneTapped() {
        this.ftoWorkout.done = true;
        this.logWorkout();

        FTOCycleAdjustor cycleAdjustor = new FTOCycleAdjustor();
        boolean willIncrement = cycleAdjustor.shouldIncrementLifts();
        cycleAdjustor.checkForCycleChange();

        this.getActivity().setResult(BLActivity.RESULT_CLOSE_ALL);
        this.getActivity().finish();
        Intent i = new Intent(this.getActivity(), FTOTrackViewActivity.class);
        startActivity(i);
    }

    protected void logWorkout() {
        JWorkoutLog workoutLog = JWorkoutLogStore.instance().create("5/3/1", new Date());

        workoutLog.deload = this.ftoWorkout.deload;
        List<JSet> sets = this.ftoWorkout.workout.sets;
        for (int i = 0; i < sets.size(); i++) {
            JSet set = sets.get(i);
            SetChange setChange = FTOWorkoutChangeCache.instance().changeForWorkout(this.ftoWorkout, i);
            Integer reps = setChange.reps;
            if (reps != null && reps == 0) {
                continue;
            }
            BigDecimal weight = setChange.weight;

            if (set == null) {
                continue;
            }

            JSetLog setLog = JSetLogStore.instance().createFromSet(set);
            if (reps != null) {
                setLog.reps = reps;
            }
            if (weight != null) {
                setLog.weight = weight;
            }

            workoutLog.addSet(setLog);
        }
        FTOWorkoutChangeCache.instance().clear();
    }

    @Override
    public void onTick(long secondsRemaining) {
        if (secondsRemaining == 0) {
            if (this.isAdded()) {
                this.setListAdapter(new FTOIndividualWorkoutListAdapter(this.getActivity(), this.ftoWorkout));
            }

            if (stoppingTimer) {
                stoppingTimer = false;
                return;
            }

            Intent notificationIntent = new Intent(App.getContext(), FTOIndividualWorkoutActivity.class);
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            notificationIntent.putExtra(FTOIndividualWorkoutActivity.FTO_WORKOUT_UUID, this.ftoWorkout.uuid);
            PendingIntent intent = PendingIntent.getActivity(App.getContext(), 0, notificationIntent, 0);

            Notification notification =
                    new Notification.Builder(App.getContext())
                            .setSmallIcon(R.drawable._78_stopwatch_light)
                            .setContentTitle(App.getContext().getString(R.string.app_name))
                            .setContentText("Rest Done")
                            .setContentIntent(intent)
                            .setDefaults(Notification.DEFAULT_SOUND)
                            .setAutoCancel(true)
                            .build();

            NotificationManager notificationManager =
                    (NotificationManager) App.getContext().getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, notification);

            if (this.isAdded()) {
                this.setListAdapter(new FTOIndividualWorkoutListAdapter(this.getActivity(), this.ftoWorkout));
            }
        }
    }
}
