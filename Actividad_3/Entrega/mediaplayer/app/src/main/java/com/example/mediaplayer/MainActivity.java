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
    //Iniciamos la variables necesarias
    public static final String EXTRA_MESSAGE = "com.example.mediaplayer.MESSAGE";
    private static final int FILE_SELECT_CODE = 0;

    //Sobrecargamos el metodo y llamos a showFileChooser(); para que inicie el selector de archivos del
    //frameworf SAF
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        showFileChooser();
    }


     //Se implementa el acceso a almacenamiento
    private void showFileChooser() {
        //Se implementa desde un Intent el acceso al almacenamiento
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
         //Filtro los contenidos que por tipo  video
        String[] mimetypes = { "video/*"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        //Se realiza una captura en caso de no exista un Gestor de archivos
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
    //Se sobrecarga el método para recibir el uri del archivo seleccionado
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case FILE_SELECT_CODE:
                if (resultCode == RESULT_OK) {
                    //Se obtiene el uri del archivo
                    Uri uri = data.getData();
                    //Se envía el uri a la pantalla de reproduccion con un intent
                   Intent intent = new Intent(this,PantallaReproductor.class);
                    intent.putExtra(EXTRA_MESSAGE,uri.toString());
                    //Se inicia la pantalla de reproduccion
                    startActivity(intent);

                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}