package com.example.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.FileUtils;
import android.provider.DocumentsContract;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.mediaplayer.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      /**  Field[] fields = R.raw.class.getFields();
        Medidas[] medidas = new Medidas[fields.length];
        Integer integer=0;
        for (Field field : fields) {
            try {
                medidas[integer]=new Medidas (integer,"Byte", field.getInt(field));
                integer++;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        // Definimos el adaptador para crear la lista
        Adaptador adap=new Adaptador(medidas,this);
        ListView lista=(ListView) findViewById( R.id.listaDesplegable);
        lista.setAdapter(adap);**/
        showFileChooser();
    }
    public void openDirectory(Uri uriToLoad) {
        // Choose a directory using the system's file picker.
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);

        // Provide read access to files and sub-directories in the user-selected
        // directory.
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        // Optionally, specify a URI for the directory that should be opened in
        // the system file picker when it loads.
      //  intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, uriToLoad);

      //  startActivityForResult(intent, your-request-code);

    }
    private static final int FILE_SELECT_CODE = 0;

    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
         //Filtro los contenidos que por tipo  video
        String[] mimetypes = { "video/*"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select a File to Upload"),
                    FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(this, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case FILE_SELECT_CODE:
                if (resultCode == RESULT_OK) {
                    // Get the Uri of the selected file
                    Uri uri = data.getData();
                   Intent intent = new Intent(this,PantallaReproductor.class);

                    // Get the path
                   intent.putExtra(EXTRA_MESSAGE,uri.toString());
                    startActivity(intent);
                    // Get the file instance
                    // File file = new File(path);
                    // Initiate the upload
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}