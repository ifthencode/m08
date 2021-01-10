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
        //Inicio la vista de juego
        juego = new Juego(this);
        setContentView(juego);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla);
        //defino la vista juego sobre el visor
        juego = (Juego) findViewById(R.id.Pantalla);
        ViewTreeObserver obs = juego.getViewTreeObserver();

        //Obtengo los parametros de dificultad
        Bundle parametros = this.getIntent().getExtras();
        dificultad=parametros.getString("dificultad");

        //Declaro e inicio las variables para contar el tiempo discurrido
        final long[] fin = new long[1];
        long inicio = System.currentTimeMillis();
        Intent intent = new Intent(this,MainActivity.class);
        obs.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // Establezco las posiciones y valores iniciales

                juego.ancho = juego.getWidth();
                juego.alto = juego.getHeight();
                juego.posX=juego.ancho/2;
                juego.posY=juego.alto-75;

                juego.radio=75;
//
                juego.posMonedaY1=250;
                juego.posMonedaX1=190;

                juego.posMonedaY2=250;
                juego.posMonedaX2= 340;

                juego.posMonedaY3=250;
                juego.posMonedaX3= 490;

                juego.posMonedaY4=250;
                juego.posMonedaX4= 640;

            }
        });
        //Ejecutamos cada 20 milisegundos
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        //Cada x segundos movemos la moneda Xdp en función de la dificultad
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
                        //Calculo el tiempo transcurrido
                        fin[0] = System.currentTimeMillis();

                        juego.tiempo=(int)((fin[0] -inicio)/1000);

                        //Una vez se cumplen 10 segundo se para el hilo, aparece un toast con un mensaje y se resetean las posiciones de los elementos
                        //Se para también la reproducción del sonido
                        if(fin[0] -inicio>=10000){
                           cancel();
                           //Mensaje
                            Toast toast1 =
                                    Toast.makeText(getApplicationContext(),
                                            "El juego ha terminado. Tu puntuación es :"+juego.puntuacion, Toast.LENGTH_SHORT);

                            toast1.show();

                            //Se detiene la reproducción del sonido
                            juego.gameloop.stop();

                            //Se recolocan los elementos al finalizar
                            juego.posMonedaY1+=juego.alto;
                            juego.posMonedaY2+=juego.alto;
                            juego.posMonedaY3+=juego.alto;
                            juego.posMonedaY4+=juego.alto;

                        }
                        juego.invalidate();
                    }
                });
            }
        }, 0, 20);

    }


}

