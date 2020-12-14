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

import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

// Extensión de una View. Totalmente necesario para dibujar
public class Juego extends View {
    public int ancho,alto;
    public float escala;
    public int posX,posY,radio,posMonedaX1,posMonedaY1;
    public int posMonedaX2,posMonedaY2;
    public int posMonedaX3,posMonedaY3;
    public int posMonedaX4,posMonedaY4;
    private GestureDetector gestos;
    private RectF rectCesta;
    private RectF rectMoneda1;
    private RectF rectMoneda2;
    private RectF rectMoneda3;
    private RectF rectMoneda4;
    private Integer puntuacion=0;
    private Random random = new Random();
    Bitmap bmpMoneda2;
    Bitmap bmpMoneda1;
    Bitmap bmpMoneda3;
    //Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/fuente.ttf");
    public Juego(Context context) {
        super(context);
    }
    public Juego(Context context, AttributeSet attrs) {
        super(context, attrs);
// TextView puntuacionID = findViewById(R.id.puntuacion);
// puntuacionID.setText("0");
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
                radio=75;
                //Elimino el movimiento vertical
                //posY=(int)event.getY();
                posX=(int)event.getX();
              //  posX=(int)event.getX();


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
        Paint cesta = new Paint();
      // Paint cesta2 = new Paint();

     //  Paint moneda = new Paint();
        Paint puntos = new Paint();
        //Definimos los colores de los objetos a pintar
        Bitmap bmpRegadera = BitmapFactory.decodeResource(getResources(),
                R.drawable.regadera);
        Bitmap bmpHucha = BitmapFactory.decodeResource(getResources(),
                R.drawable.pig);
      //  bmpMoneda2 = BitmapFactory.decodeResource(getResources(),
       //         R.drawable.moneda2);

        fondo.setColor(Color.WHITE);
        fondo.setStyle(Paint.Style.FILL_AND_STROKE);
      //  cesta.setColor(Color.YELLOW);
       // cesta.setStyle(Paint.Style.FILL_AND_STROKE);
     //   moneda.setColor(Color.RED);
      //  moneda.setStyle(Paint.Style.FILL_AND_STROKE);
       //puntos.setTextAlign(Paint.Align.LEFT);
       puntos.setTextSize(100);
       puntos.setColor(Color.YELLOW);


// puntos.setTypeface(typeface);
        //Pinto rectángulo con un ancho y alto de 1000 o de menos si la pantalla es
        canvas.drawRect(new Rect(0,0,(ancho),(alto)),fondo);
        // Pinto la pelota
          rectCesta= new RectF((posX-50),(posY-50),(posX+50),(posY+50));
          canvas.drawBitmap(bmpRegadera,0,0,null);
         canvas.drawBitmap(bmpHucha,null,rectCesta,null);

       // canvas.drawOval(rectCesta,cesta);
        //Pintamos moneda
        if (posMonedaY1>alto) {
            posMonedaY1=250;
            posMonedaX1= 190;
        }
        if (posMonedaY2>alto) {
            posMonedaY2=250;
            posMonedaX2= 190;
        }
        if (posMonedaY3>alto) {
            posMonedaY3=250;
            posMonedaX3= 190;
        }

        if (posMonedaY4>alto) {
            posMonedaY4=250;
            posMonedaX4= 190;
        }
        bmpMoneda1= BitmapFactory.decodeResource(getResources(),
                    R.drawable.moneda1png);
        rectMoneda1= new RectF((posMonedaX1-radio+15), (posMonedaY1-radio+15), (posMonedaX1+radio+75), (posMonedaY1+radio+65));
        canvas.drawBitmap(bmpMoneda1,null,rectMoneda1,null);

        bmpMoneda2= BitmapFactory.decodeResource(getResources(),
                R.drawable.moneda2png);

        bmpMoneda3= BitmapFactory.decodeResource(getResources(),
                R.drawable.black);
        rectMoneda2= new RectF((posMonedaX2-radio+315),(posMonedaY2-radio+315),(posMonedaX2+radio+335),(posMonedaY2+radio+365));
        canvas.drawBitmap(bmpMoneda2,null,rectMoneda2,null);

        rectMoneda3= new RectF((posMonedaX3-radio+800),(posMonedaY3-radio+5),(posMonedaX3+radio+825),(posMonedaY3+radio+70));
        canvas.drawBitmap(bmpMoneda2,null,rectMoneda3,null);
        rectMoneda4= new RectF((posMonedaX4-radio+500),(posMonedaY4-radio+5),(posMonedaX4+radio+555),(posMonedaY4+radio+60));
        canvas.drawBitmap(bmpMoneda3,null,rectMoneda4,null);
     //   canvas.drawOval(rectMoneda2,moneda);
     //   Calculo intersección
        if (RectF.intersects(rectCesta,rectMoneda1)) {
            int ram=random.nextInt(ancho);

            puntuacion += 1;
            posMonedaY1=250;
            posMonedaX1=ram;

        }
        if (RectF.intersects(rectCesta,rectMoneda2)) {
            int ram=random.nextInt(ancho);

            puntuacion += 2;
            posMonedaY2=250;
            posMonedaX2=ram;

        }
        if (RectF.intersects(rectCesta,rectMoneda3)){
            int ram=random.nextInt(ancho);

            puntuacion += 2;
            posMonedaY3=250;
            posMonedaX3=ram;

        }
        if (RectF.intersects(rectCesta,rectMoneda4)) {
            int ram=random.nextInt(ancho);

            puntuacion -= 10;
            posMonedaY3=250;
            posMonedaX3=ram;

        }
        canvas.drawText(puntuacion.toString(), 900,150,puntos);
    }
}