package com.example.juego;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        androidx.appcompat.widget.Toolbar barra = (androidx.appcompat.widget.Toolbar)
                findViewById(R.id.barra);
        setSupportActionBar(barra);

    }

    //Acción a ejecutar desde el botón
    public void onClickIniciar (View view) {
        Intent intent = new Intent(this, ActivityPantalla.class);
        Spinner sp=(Spinner) findViewById(R.id.spinner);
        intent.putExtra("dificultad",sp.getSelectedItem().toString());
        startActivity(intent);
    }
    //Metodo para el botón del menú
    public void onClickIniciar_M () {
        Intent intent = new Intent(this, ActivityPantalla.class);
        Spinner sp=(Spinner) findViewById(R.id.spinner);
        intent.putExtra("dificultad",sp.getSelectedItem().toString());
        startActivity(intent);
    }

    //Generación del menú y elementos
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuprincipal,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.idSalir){
            finish();
        }
        else if(item.getItemId()==R.id.idIniciar){
            onClickIniciar_M();
        }
        return true;
    }


}