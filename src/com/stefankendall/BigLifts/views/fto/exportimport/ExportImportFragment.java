package com.stefankendall.BigLifts.views.fto.exportimport;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.os.Environment;
import android.view.*;
import android.widget.Toast;
import com.google.common.io.Files;
import com.stefankendall.BigLifts.R;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class ExportImportFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public boolean isExternalStorageWritable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.export_import_view, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.export_import_menu, menu);
        MenuItem exportButton = menu.findItem(R.id.export_button);
        exportButton.setEnabled(this.isExternalStorageWritable());
        exportButton.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                try {
                    ExportImportFragment.this.export();
                } catch (IOException e) {
                    Toast.makeText(getActivity(), "Could not write file", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

        MenuItem importButton = menu.findItem(R.id.import_button);
        importButton.setEnabled(this.isExternalStorageWritable());
        importButton.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                try {
                    ExportImportFragment.this.importLog();
                } catch (IOException e) {
                    Toast.makeText(getActivity(), "Could not read file", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }

    private void export() throws IOException {
        Files.write(LogJsonExporterImporter.export(), getFile(), Charset.forName("UTF-8"));
        Toast.makeText(getActivity(), "log.json saved", Toast.LENGTH_SHORT).show();
    }

    private File getFile() {
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(path, "BigLifts2");
        file.mkdirs();

        return new File(file, "log.json");
    }

    private void importLog() throws IOException {
        String contents = Files.toString(getFile(), Charset.forName("UTF-8"));
        LogJsonExporterImporter.importLog(contents);
        Toast.makeText(getActivity(), "Log imported!", Toast.LENGTH_SHORT).show();
    }
}
