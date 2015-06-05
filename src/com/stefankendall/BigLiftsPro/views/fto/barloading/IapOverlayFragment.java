package com.stefankendall.BigLiftsPro.views.fto.barloading;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.stefankendall.BigLiftsPro.R;

public class IapOverlayFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View overlay = inflater.inflate(R.layout.iap_overlay, container, false);

        View view = overlay.findViewById(R.id.overlay);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        return overlay;
    }
}
