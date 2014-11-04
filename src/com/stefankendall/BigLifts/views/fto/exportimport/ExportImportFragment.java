package com.stefankendall.BigLifts.views.fto.exportimport;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.view.*;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.Drive;
import com.stefankendall.BigLifts.R;

public class ExportImportFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private GoogleApiClient googleApiClient;
    final private static int RESOLVE_CONNECTION_REQUEST_CODE = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        // AIzaSyAs7ITqBghr-3rU22ZFUSDuMUnC3lCGhvY
        this.googleApiClient = new GoogleApiClient.Builder(getActivity())
                .addApi(Drive.API)
                .addScope(Drive.SCOPE_FILE)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.export_import_view, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.export_import_menu, menu);
        menu.findItem(R.id.export_button).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                ExportImportFragment.this.export();
                return false;
            }
        });

        menu.findItem(R.id.import_button).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                ExportImportFragment.this.importLog();
                return false;
            }
        });
    }

    private void importLog() {
        this.googleApiClient.connect();

    }

    private void export() {
        this.googleApiClient.connect();

    }

    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(getActivity(), RESOLVE_CONNECTION_REQUEST_CODE);
            } catch (IntentSender.SendIntentException e) {
                // Unable to resolve, message user appropriately
            }
        } else {
            GooglePlayServicesUtil.getErrorDialog(connectionResult.getErrorCode(), getActivity(), 0).show();
        }
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        switch (requestCode) {
            case RESOLVE_CONNECTION_REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    this.googleApiClient.connect();
                }
                break;
        }
    }
}
