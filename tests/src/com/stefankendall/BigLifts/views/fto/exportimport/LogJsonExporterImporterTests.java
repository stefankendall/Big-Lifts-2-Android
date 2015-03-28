package com.stefankendall.BigLifts.views.fto.exportimport;

import android.test.ActivityTestCase;
import com.google.common.io.CharStreams;
import com.stefankendall.BigLifts.data.stores.JWorkoutLogStore;
import junit.framework.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LogJsonExporterImporterTests extends ActivityTestCase {
    public void testImportsJsonFileFromUser() throws IOException {
        InputStream file = getInstrumentation().getContext().getResources().getAssets().open("crashing-log.json");
        final InputStreamReader reader = new InputStreamReader(file);
        String json = CharStreams.toString(reader);
        LogJsonExporterImporter.importLog(json);
        Assert.assertTrue(JWorkoutLogStore.instance().count() > 0);
    }
}