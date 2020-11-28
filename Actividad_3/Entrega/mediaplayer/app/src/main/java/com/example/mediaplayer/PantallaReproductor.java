package com.example.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

public class PantallaReproductor extends AppCompatActivity  {
  //  private SurfaceView surfaceView;
    private VideoView mVideoView;
    String modo="Start";



    int sel=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pantalla_reproductor);

        Button buttonStart = findViewById(R.id.button4);
        buttonStart.setVisibility(View.INVISIBLE);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                mVideoView.start();
              //  mVideoView.;
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                "Reproduccion iniciada", Toast.LENGTH_SHORT);
                toast1.show();


            }
        });

       Button buttonStop = findViewById(R.id.button5);
        buttonStop.setVisibility(View.INVISIBLE);
        buttonStop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Al tocar stop paro la reproduccion y la pongo a 0
                mVideoView.stopPlayback();
                mVideoView.resume();
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                "Reproduccion parada", Toast.LENGTH_SHORT);

                toast1.show();
            }
        });
         Button buttonPause = findViewById(R.id.button6);
        buttonPause.setVisibility(View.INVISIBLE);
        buttonPause.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Al tocar pause si está en Start paro la reproduccion
                if(modo.equals("Start")){
                    mVideoView.pause();
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "Reproduccion en pause", Toast.LENGTH_SHORT);
                    toast1.show();
                    modo="pause";

                    //Si está en pause la inicio
                }else if(modo.equals("pause")) {
                    mVideoView.start();
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "Reproduccion iniciada", Toast.LENGTH_SHORT);
                    toast1.show();
                    modo="Star";

                }
            }
        });
        MediaController mediaController= new MediaController(this);
        mediaController.setAnchorView(mVideoView);
        mVideoView = (VideoView) findViewById(R.id.surface_view);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        //Comienzo la reproduccion al cargar la pantalla de forma automáica
        mVideoView.setVideoPath(String.valueOf(Uri.parse(message)));
        Toast toast1 =
                Toast.makeText(getApplicationContext(),
                        "Reproduccion iniciada", Toast.LENGTH_SHORT);
        toast1.show();
        mVideoView.start();
        /*
        * Al tocar la pantalla muestro u oculto los botones
        *
        *
        *
        *
        * */
        mVideoView.setOnTouchListener(new View.OnTouchListener()
        {



            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(sel==0){

                    buttonStart.setVisibility(View.VISIBLE);
                    buttonStop.setVisibility(View.VISIBLE);
                    buttonPause.setVisibility(View.VISIBLE);
                    sel=1;


                }else if(sel==1){
                    buttonStart.setVisibility(View.INVISIBLE);
                    buttonStop.setVisibility(View.INVISIBLE);
                    buttonPause.setVisibility(View.INVISIBLE);
                    sel=0;

                }
                return false;
            }
        });


    }




    }