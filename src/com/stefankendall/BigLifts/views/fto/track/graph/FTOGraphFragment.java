package com.stefankendall.BigLifts.views.fto.track.graph;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.*;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.stefankendall.BigLifts.R;
import com.stefankendall.BigLifts.data.stores.JPurchaseStore;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class FTOGraphFragment extends Fragment {
    private WebView webView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.single_button, menu);
        MenuItem button = menu.findItem(R.id.button);
        button.setTitle("Export");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String csv = FTOLogExporter.csv();
        try {
            exportLogToEmail(csv);
        } catch (IOException e) {
        }
        return true;
    }

    private void exportLogToEmail(String csv) throws IOException {
        Intent exportIntent = new Intent(android.content.Intent.ACTION_SEND);

        exportIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Big Lifts 2 Log Export");

        File file = new File(Environment.getExternalStorageDirectory(), "log.csv");
        Files.write(csv, file, Charset.defaultCharset());

        exportIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
        exportIntent.setType("text/csv");

        getActivity().startActivity(Intent.createChooser(exportIntent, "Export Log to CSV"));
    }
}
