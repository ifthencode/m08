package com.example.conversor_unidades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.conversor_unidades";
    private ListView listview;
    private String[] names;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        listview=findViewById(R.id.lvprincipal);
        names =res.getStringArray(R.array.ListaMedidas);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
       public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

            sendMessage(view,position);
        }
  });
    }
    public void sendMessage(View view,int position) {
        Intent intent = new Intent(this, Pantalla_2.class);

         String message = names[position];
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}