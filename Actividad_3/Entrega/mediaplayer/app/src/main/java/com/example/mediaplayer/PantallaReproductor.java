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
import android.widget.VideoView;

public class PantallaReproductor extends AppCompatActivity  {
  //  private SurfaceView surfaceView;
    private VideoView mVideoView;



    int sel=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //Bloqueo la pantalla para que esté siempre en formato horizontal.
     //   setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_pantalla_reproductor);

        Button buttonStart = findViewById(R.id.button4);
        buttonStart.setVisibility(View.INVISIBLE);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mVideoView.start();


            }
        });

       Button buttonStop = findViewById(R.id.button5);
        buttonStop.setVisibility(View.INVISIBLE);
        buttonStop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mVideoView.stopPlayback();
            }
        });
         Button buttonPause = findViewById(R.id.button6);
        buttonPause.setVisibility(View.INVISIBLE);
        buttonStop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mVideoView.pause();
            }
        });
        MediaController mediaController= new MediaController(this);
        mediaController.setAnchorView(mVideoView);
        mVideoView = (VideoView) findViewById(R.id.surface_view);
     //   params.width = 1000;
      //  params.height =1200;

        //de forma alternativa si queremos un streaming usar
        //mVideoView.setVideoURI(Uri.parse(URLstring));
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        mVideoView.setVideoPath(String.valueOf(Uri.parse(message)));
     //   mVideoView.setMediaController(mediaController);
      //  mVideoView.setLayoutParams(params);
        //   mVideoView.start();
       // mVideoView.requestFocus();

        ;
      //  progressBar.setVisibility(View.VISIBLE);
        mVideoView.start();
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