package com.example.conversor;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Context context;

    private TextView textviewResultado;
    private Spinner spinnerConvertir;
    private Spinner spinnerConvertido;
    private EditText recibido;
    int lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Declaramos e inicializamos las variables
        textviewResultado=findViewById(R.id.textViewResultado);
        spinnerConvertir=findViewById(R.id.spinnerUnitoConvert);
        spinnerConvertido=findViewById(R.id.spinnerUnitConverted);
        recibido=findViewById(R.id.editTextNumberDecimal);
        final Editable textScrito=recibido.getText() ;
        Button botonCalcular=findViewById(R.id.button);
        botonCalcular.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //
                //Obtenemos los valores introducidos por el usuario
                long itemaconvertir = spinnerConvertir.getSelectedItemId();
                long itemconvertido = spinnerConvertido.getSelectedItemId();
                //Declaramos e inicializamoss las variables necesarias para los cálculos
                BigDecimal bigDecimalResultado = null;
                BigDecimal bigDecimalRecibido;
                //Calculamos la diferencia de posición en la escala de unidades
                long calculo = itemaconvertir - itemconvertido;
                int calculoInt;
                Toast toast1;

                    //Hacemos que salga un mensaje si el usuario no introduce la cantidad a convertir
                    if (recibido.getText().toString().isEmpty()) {

                        toast1 =
                                Toast.makeText(getApplicationContext(),
                                        "Introduce una cantidad a convertir", Toast.LENGTH_SHORT);

                        toast1.show();
                    } else {
                         //Si se  ha seleccionado como origen o destino bit
                        if (itemconvertido == 0 | itemaconvertir == 0) {
                            //Se diferencia entre una conversion mayor-menor menor-mayor o igual
                            if (calculo > 0) {


                               calculoInt=(int) calculo;
                                //Para realizar el cálculo de la potencia se llama al método calcular()
                             //Se calculan los bytes primero y si el destino de la conversion es otra se multiplica /divide por 1024
                                bigDecimalRecibido = BigDecimal.valueOf(Double.parseDouble(textScrito.toString()));
                                bigDecimalResultado = bigDecimalRecibido;
                                bigDecimalResultado = bigDecimalResultado.multiply(BigDecimal.valueOf(8));
                                if(itemaconvertir>1){
                                    calculoInt=(int) (calculo-1);
                                    BigDecimal temp;
                                    temp=calcular(calculoInt,1024);

                                    bigDecimalResultado = bigDecimalResultado.multiply(temp);


                                }
                            } else if (calculo == 0) {
                                bigDecimalRecibido = BigDecimal.valueOf(Double.parseDouble(textScrito.toString()));
                                bigDecimalResultado = bigDecimalRecibido.multiply(BigDecimal.valueOf(1));
                            } else if (calculo < 0) {


                                calculoInt=(int) (calculo*-1);
                                bigDecimalRecibido = BigDecimal.valueOf(Double.parseDouble(textScrito.toString()));


                                bigDecimalResultado = bigDecimalRecibido;
                                bigDecimalResultado = bigDecimalResultado.divide(BigDecimal.valueOf(8));

                                 if(itemconvertido>1){
                                     calculoInt=(int) (calculo*-1)-1;
                                     BigDecimal temp;
                                     temp=calcular(calculoInt,1024);

                                     bigDecimalResultado = bigDecimalResultado.divide(temp);

                                 }

                            }
                            //Se diferencia entre una conversion mayor-menor menor-mayor o igual

                            //si no se ha seleccionado como origen o destino bit
                        } else if (itemconvertido > 0) {
                            if (calculo > 0) {


                                 calculoInt=(int) calculo;
                                bigDecimalResultado=calcular(calculoInt,1024);
                                bigDecimalRecibido = BigDecimal.valueOf(Double.parseDouble(textScrito.toString()));
                                bigDecimalResultado = bigDecimalResultado.multiply(bigDecimalRecibido);

                            } else if (calculo == 0) {
                                bigDecimalRecibido = BigDecimal.valueOf(Double.parseDouble(textScrito.toString()));
                                bigDecimalResultado = bigDecimalRecibido.multiply(BigDecimal.valueOf(1));
                            } else if (calculo < 0) {

                               // bigDecimalResultado = BigDecimal.valueOf((long) Math.pow(1024, (calculo * (-1))));
                               calculoInt=(int) (calculo*-1);
                                bigDecimalResultado=calcular(calculoInt,1024);
                                bigDecimalRecibido = BigDecimal.valueOf(Double.parseDouble(textScrito.toString()));
                                bigDecimalResultado = bigDecimalRecibido.divide(bigDecimalResultado);

                            }


                        }


                        Log.d("Escribir resultado", bigDecimalResultado.toString());
                        textviewResultado.setText(bigDecimalResultado.toEngineeringString()+" "+spinnerConvertido.getSelectedItem()+"´s");
                    }
                }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public BigDecimal calcular(int calculo, int base){
        BigDecimal num=BigDecimal.valueOf(1);
        for(int i=0;i<calculo;i++){

            num=num.multiply(BigDecimal.valueOf(base));

        }
        return num;
    }
}