package com.stefankendall.BigLifts.views.fto.track.graph;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.gson.Gson;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.data.stores.JPurchaseStore;

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
        this.webView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                if (!JPurchaseStore.instance().hasPurchasedEverything()) {
                    FTOGraphFragment.this.webView.evaluateJavascript("window.setupTestData()", null);
                } else {
                    Gson gson = new Gson();
                    FTOGraphFragment.this.webView.evaluateJavascript("window.loadData(" +
                            gson.toJson(new FTOLogGraphTransformer().buildDataFromLog()) +
                            ")", null);
                }
            }
        });

        return v;
    }
}
