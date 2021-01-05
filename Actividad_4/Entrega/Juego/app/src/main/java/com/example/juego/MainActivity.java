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

//    public Juego juego;
//    private Handler handler = new Handler();
//    private Random random = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        juego = new Juego(this);
//        setContentView(juego);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        androidx.appcompat.widget.Toolbar barra = (androidx.appcompat.widget.Toolbar)
                findViewById(R.id.barra);
        setSupportActionBar(barra);
//        juego = (Juego) findViewById(R.id.Pantalla);
//        ViewTreeObserver obs = juego.getViewTreeObserver();
//        obs.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                // Sólo se puede averiguar el ancho y alto una vez ya se ha pintado el
//
//               juego.ancho = juego.getWidth();
//                juego.alto = juego.getHeight();
//                juego.posX=juego.ancho/2;
//                juego.posY=juego.alto-75;
//
//                juego.radio=75;
//
//                juego.posMonedaY1=250;
//                juego.posMonedaX1=190;
//
//                juego.posMonedaY2=250;
//                juego.posMonedaX2= 190;
//
//                juego.posMonedaY3=250;
//                juego.posMonedaX3= 190;
//
//                juego.posMonedaY4=250;
//                juego.posMonedaX4= 190;
//             //   juego.posMonedaY=550;
//            }
//        });
//        //Ejecutamos cada 20 milisegundos
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                handler.post(new Runnable() {
//                    public void run() {
//                        //Cada x segundos movemos la moneda 10dp
//                        juego.posMonedaY1+=10;
//                        juego.posMonedaY2+=10;
//                        juego.posMonedaY3+=10;
//                        juego.posMonedaY4+=10;
//                     //   juego.posMonedaX+=random.nextInt(15);
//                     //   juego.bmpMoneda2= BitmapFactory.decodeResource(getResources(),
//                      //          R.drawable.moneda1);
//                   //     juego.posMonedaX+=random.nextInt(15);
//                        //refreca la pantalla y llama al draw
//                        juego.invalidate();
//                    }
//                });
//            }
//        }, 0, 20);
    }
    public void onClickIniciar (View view) {
        Intent intent = new Intent(this, ActivityPantalla.class);
      //  intent.putExtra("id",idEmpresa);
        Spinner sp=(Spinner) findViewById(R.id.spinner);
        intent.putExtra("dificultad",sp.getSelectedItem().toString());
        startActivity(intent);
    }
    public void onClickIniciar_M () {
        Intent intent = new Intent(this, ActivityPantalla.class);
        //  intent.putExtra("id",idEmpresa);
        Spinner sp=(Spinner) findViewById(R.id.spinner);
        intent.putExtra("dificultad",sp.getSelectedItem().toString());
        startActivity(intent);
    }
    public void onClickSalir(View view) {


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuprincipal,menu);
        return true;
    }
    // Al realizar click en el elemento visualizamos un Toast
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