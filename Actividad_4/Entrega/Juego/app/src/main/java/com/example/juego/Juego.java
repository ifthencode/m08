package com.example.juego;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

// Esta es la clase que utilizaremos para dibujar
public class Juego extends View {
    //Declaraciones

    //Tamaño pantalla
    public int ancho,alto;

    //Posicion de los objetos
    public int posX,posY,radio,posMonedaX1,posMonedaY1;
    public int posMonedaX2,posMonedaY2;
    public int posMonedaX3,posMonedaY3;
    public int posMonedaX4,posMonedaY4;

    //Rectangulos en los que incrustamos las imagenes y detectamos los impactos
    private RectF rectCesta;
    private RectF rectMoneda1;
    private RectF rectMoneda2_1;
    private RectF rectMoneda2_2;
    private RectF rectTarjeta;

    //Puntuación y tiempo
    Integer puntuacion=0;
    Integer tiempo;

    //Generación de numeros aleatorios
    private Random random = new Random();

    //Bitmaps
    Bitmap bmpMoneda2;
    Bitmap bmpMoneda1;
    Bitmap bmpTarjeta;


  MediaPlayer gameloop = new MediaPlayer();


    public Juego(Context context) {
        super(context);
    }

    public Juego(Context context, AttributeSet attrs) {
        super(context, attrs);
        gameloop = MediaPlayer.create(context,R.raw.mario);
        gameloop.start();
        //mantiene el loop del soundtrack
        gameloop.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                gameloop.start();
            }
        });
    }

    //Sección que capta los eventos del usuario
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // you may need the x/y location
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:

                //Elimino el movimiento vertical
                //posY=(int)event.getY();
                posX=(int)event.getX();

                //Encuadramos dentro de los limites el movimiento del cerdito
                if(posX<50){
                    posX=50;
                }else if(posX>1030){
                    posX=1030;
                }


                // invalidate llama al onDraw y vuelve a pintar la bola
                this.invalidate();
        }
        return true;
    }
    public Juego(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //Definimos los objetos a pintar
        Paint fondo = new Paint();
        Paint puntos = new Paint();
        Paint tiempopaint = new Paint();

        //Bitmaps
        Bitmap bmpRegadera = BitmapFactory.decodeResource(getResources(),
                R.drawable.regadera);
        Bitmap bmpHucha = BitmapFactory.decodeResource(getResources(),
                R.drawable.pig);

        //definimos colores , estilos y tamaño letra
        fondo.setColor(Color.WHITE);
        fondo.setStyle(Paint.Style.FILL_AND_STROKE);
        puntos.setTextSize(100);
        puntos.setColor(Color.YELLOW);
        tiempopaint.setTextSize(100);
        tiempopaint.setColor(Color.RED);
        //Defino el rectangulo de la hucha
        rectCesta= new RectF((posX-50),(posY-50),(posX+75),(posY+75));
        //Pinto rectángulo fondo
        canvas.drawRect(new Rect(0,0,(ancho),(alto)),fondo);

        //Pinto la regadera
        canvas.drawBitmap(bmpRegadera,0,0,null);
        // Pinto el rectangulo de la hucha
         canvas.drawBitmap(bmpHucha,null,rectCesta,null);


         //Defino la posicion de las monedas de forma aleatoria a cada ejecución , dentro de
        // los limites de la pantalla
        if (posMonedaY1>alto) {
            int ram=random.nextInt(ancho-radio*2);
            posMonedaY1=250;
            posMonedaX1=ram;
            //Verifico que queda todo el objeto dentro de la pantalla
            if(ram<radio*2){

                posMonedaX1=radio*2;
            }
        }
        if (posMonedaY2>alto) {
            int ram=random.nextInt(ancho-radio*2);
            posMonedaY2=250;
            posMonedaX2=ram;
            if(ram<radio*2){
                posMonedaX2=radio*2;
            }
        }
        if (posMonedaY3>alto) {
            int ram=random.nextInt(ancho-radio*2);
            posMonedaY3=250;
            posMonedaX3=ram;
            if(ram<radio*2){

                posMonedaX3=radio*2;
            }
        }
        if (posMonedaY4>alto) {
            int ram=random.nextInt(ancho-350);
            posMonedaY4=250;
            posMonedaX4=ram;
            if(ram<350){
               posMonedaX4=350;
            }

        }
        //Inicio los bitmaps con la imagen correspondiente
        bmpMoneda1= BitmapFactory.decodeResource(getResources(),
                    R.drawable.moneda1png);
        rectMoneda1= new RectF((posMonedaX1-radio), (posMonedaY1-radio), (posMonedaX1+radio), (posMonedaY1+radio));
        canvas.drawBitmap(bmpMoneda1,null,rectMoneda1,null);

        bmpMoneda2= BitmapFactory.decodeResource(getResources(),
                R.drawable.moneda2png);

        rectMoneda2_1= new RectF((posMonedaX2-radio),(posMonedaY2-radio),(posMonedaX2+radio),(posMonedaY2+radio));
        canvas.drawBitmap(bmpMoneda2,null,rectMoneda2_1,null);

        rectMoneda2_2= new RectF((posMonedaX3-radio),(posMonedaY3-radio),(posMonedaX3+radio),(posMonedaY3+radio));
        canvas.drawBitmap(bmpMoneda2,null,rectMoneda2_2,null);

        bmpTarjeta= BitmapFactory.decodeResource(getResources(),
                R.drawable.black);
        rectTarjeta= new RectF((posMonedaX4),(posMonedaY4+5),(posMonedaX4+350),(posMonedaY4+170));
        canvas.drawBitmap(bmpTarjeta,null,rectTarjeta,null);

     //   Calculo intersección y en función del valor sumo o resto valor
        if (RectF.intersects(rectCesta,rectMoneda1)) {
              puntuacion += 1;
              //fuerzo la desaparición de la moneda después de la interseccion
              posMonedaY1=alto+10;

        }
        if (RectF.intersects(rectCesta,rectMoneda2_1)) {
            int ram=random.nextInt(ancho);

            puntuacion += 2;
            posMonedaY2=alto+10;
;

        }
        if (RectF.intersects(rectCesta,rectMoneda2_2)){
            int ram=random.nextInt(ancho);

            puntuacion += 2;
            posMonedaY3=alto+10;


        }
        if (RectF.intersects(rectCesta,rectTarjeta)) {
            int ram=random.nextInt(ancho);

            puntuacion -= 10;
            posMonedaY4=alto+10;


        }
        //Pinto puntuación y tiempo
        canvas.drawText(puntuacion.toString(), 850,150,puntos);
        canvas.drawText(tiempo.toString(), 650,150,tiempopaint);
    }
}