package com.example.juego;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.ViewTreeObserver;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ActivityPantalla extends AppCompatActivity {
    public Juego juego;
    private Handler handler = new Handler();
    private Random random = new Random();
    String dificultad;
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        juego = new Juego(this);
        setContentView(juego);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla);
        juego = (Juego) findViewById(R.id.Pantalla);
        ViewTreeObserver obs = juego.getViewTreeObserver();
        Bundle parametros = this.getIntent().getExtras();
        dificultad=parametros.getString("dificultad");
        final long[] fin = new long[1];
        long inicio = System.currentTimeMillis();
        Intent intent = new Intent(this,MainActivity.class);
        obs.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // Sólo se puede averiguar el ancho y alto una vez ya se ha pintado el

                juego.ancho = juego.getWidth();
                juego.alto = juego.getHeight();
                juego.posX=juego.ancho/2;
                juego.posY=juego.alto-75;

                juego.radio=75;
//
                juego.posMonedaY1=250;
                juego.posMonedaX1=190;

                juego.posMonedaY2=250;
                juego.posMonedaX2= 190;

                juego.posMonedaY3=250;
                juego.posMonedaX3= 190;

                juego.posMonedaY4=250;
                juego.posMonedaX4= 190;
                //   juego.posMonedaY=550;
            }
        });
        //Ejecutamos cada 20 milisegundos
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        //Cada x segundos movemos la moneda 10dp
                        switch(dificultad){
                            case "Facil":
                                juego.posMonedaY1+=10;
                                juego.posMonedaY2+=10;
                                juego.posMonedaY3+=10;
                                juego.posMonedaY4+=10;
                                break;
                            case "Dificil":
                                juego.posMonedaY1+=20;
                                juego.posMonedaY2+=20;
                                juego.posMonedaY3+=20;
                                juego.posMonedaY4+=20;
                                break;

                            case "Muy dificil":
                                juego.posMonedaY1+=30;
                                juego.posMonedaY2+=30;
                                juego.posMonedaY3+=30;
                                juego.posMonedaY4+=30;
                                break;

                        }
                        //   juego.posMonedaX+=random.nextInt(15);
                        //   juego.bmpMoneda2= BitmapFactory.decodeResource(getResources(),
                        //          R.drawable.moneda1);
                        //     juego.posMonedaX+=random.nextInt(15);
                        //refreca la pantalla y llama al draw
                        juego.tiempo=(int)((fin[0] -inicio)/1000);
                        fin[0] = System.currentTimeMillis();

                        if(fin[0] -inicio>=10000){
                           cancel();
                            Toast toast1 =
                                    Toast.makeText(getApplicationContext(),
                                            "El juego ha terminado. Tu puntuación es :"+juego.puntuacion, Toast.LENGTH_SHORT);

                            toast1.show();
                        }
                        juego.invalidate();
                    }
                });
            }
        }, 0, 20);

    }


}

