package com.stefankendall.BigLifts.views.fto.track.graph;

import android.app.Fragment;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.stefankendall.BigLifts.App;
import com.stefankendall.BigLifts.R;

import java.util.Map;

public class FTOGraphFragment extends Fragment {
    private static boolean isSetup = false;
    private WebView webView;
    private ViewTreeObserver.OnGlobalLayoutListener layoutListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fto_graph, container, false);
        this.webView = (WebView) v.findViewById(R.id.webview);
        this.webView.setClickable(true);
        WebSettings webSettings = this.webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        this.webView.loadUrl("file:///android_asset/html/graph.html");
        this.webView.addJavascriptInterface(this, "Android");

        v.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (!FTOGraphFragment.isSetup) {
                    FTOGraphFragment.this.webView.evaluateJavascript("window.setupTestData()", null);
                }
                FTOGraphFragment.isSetup = true;

                String setGraphSizeScript = "window.setGraphSize()";
                FTOGraphFragment.this.webView.evaluateJavascript(setGraphSizeScript, null);
            }
        });

        return v;
    }
}
