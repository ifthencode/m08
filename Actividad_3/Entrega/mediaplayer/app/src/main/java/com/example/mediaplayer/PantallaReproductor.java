package com.example.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.VideoView;

public class PantallaReproductor extends AppCompatActivity {

    private VideoView mVideoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //Bloqueo la pantalla para que esté siempre en formato horizontal.
       setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_pantalla_reproductor);
        //Establezco relaciones de tamaño
        ConstraintLayout.LayoutParams params=new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);;
        mVideoView =(VideoView)findViewById(R.id.surface_view);
        params.width=1500;
        params.height=1000;
        //de forma alternativa si queremos un streaming usar
        //mVideoView.setVideoURI(Uri.parse(URLstring));
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        mVideoView.setVideoPath(String.valueOf(Uri.parse(message)));

        mVideoView.setLayoutParams(params);
     //   mVideoView.start();
        mVideoView.requestFocus();
        final Button buttonStart = findViewById(R.id.button4);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               mVideoView.start();
            }
        });
        final Button buttonStop = findViewById(R.id.button5);
        buttonStop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mVideoView.stopPlayback();
            }
        });
        final Button buttonPause = findViewById(R.id.button6);
        buttonStop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mVideoView.pause();
            }
        });
    }

}