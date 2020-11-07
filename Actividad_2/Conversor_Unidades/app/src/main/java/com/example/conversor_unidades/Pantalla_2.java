package com.example.conversor_unidades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Pantalla_2 extends AppCompatActivity {
    int[] valores;
    Double valorintroducido;
    private TextView textviewResultado;
    EditText numerointroducido ;
    String calculo;
    Spinner origen;
    Spinner destino;
    Toast toast1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_2);
        Intent intent = getIntent();
        Resources res = getResources();


        final String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        int vista=0;
       //En función de la selección en la pantalla uno cargo los valores correspondientes en los spinners
        switch(message){
             case "Datos":
             vista=R.array.ListaUnidades_datos;
               valores = res.getIntArray(R.array.valorunidades_datos);
                calculo="General";
                break;
            case "Capacidad":
                vista=R.array.listaUnidades_capacidad;
                valores = res.getIntArray(R.array.valorUnidades_capacidad);
                calculo="General";
                break;
            case "Longitud":
                vista=R.array.listaUnidades_longitud;
                valores = res.getIntArray(R.array.valorUnidades_longitud);
                calculo="General";
                break;

            case "Peso":
                vista=R.array.listaUnidades_peso;
                valores = res.getIntArray(R.array.valorUnidades_peso);
                calculo="General";
                break;
            case "Temperatura":
                vista=R.array.listaUnidades_temperatura;
                calculo="Temperatura";

                break;
        }

        //Inicio las variables necesarias
         origen=findViewById(R.id.spinner_origen);
         destino=findViewById(R.id.spinner_destino);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,vista,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        origen.setAdapter(adapter);
        destino.setAdapter(adapter);
        Button botonConvertir=findViewById(R.id.bt_convertir);
        botonConvertir.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Al pulsar el boton compruebo si se ha introducido una cantidad, si se ha introduciod ejecuto la función calcular, si no se ha introducido lanzo un mensaje.
                numerointroducido =findViewById(R.id.editTextNumberDecimal);
                final Editable textScrito=numerointroducido.getText() ;
                if(textScrito.toString().equals("")){
                    toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "Introduce una cantidad a convertir...", Toast.LENGTH_SHORT);

                    toast1.show();

                }else {
                    valorintroducido = Double.parseDouble(textScrito.toString());
                    textviewResultado = findViewById(R.id.txv_resultado);
                    long position = origen.getSelectedItemPosition() - destino.getSelectedItemPosition();
                    Double resultado = Calcular(position, valorintroducido, calculo);
                    textviewResultado.setText(resultado.toString() + " " + destino.getSelectedItem() + "´s");
                }
            }
        });
    }
    /*La funcion calcular:se genera un caso generico para todos excepto para el caso temperatura*/
    public double Calcular(long position,Double dato_introducido,String message){
        double resultado = 0;
        switch (calculo) {
            case "General":
                resultado=valorintroducido*valores[origen.getSelectedItemPosition()]/valores[destino.getSelectedItemPosition()];
              break;
            case "Temperatura":
                switch (origen.getSelectedItem().toString()){

                    case "Celsius":
                        resultado=valorintroducido;
                        break;
                    case "Fahrenheit":
                        resultado=(valorintroducido-32)*5/9 ;
                        break;
                    case "Kelvin":
                        resultado=valorintroducido-273.15;

                        break;
                }
                switch (destino.getSelectedItem().toString()){

                    case "Celsius":
                        resultado=resultado;
                        break;
                    case  "Fahrenheit":
                        resultado=(resultado*9/5)+32;
                        break;
                    case "Kelvin":
                        resultado=resultado+273.15;
                }

                break;
        }
        return resultado;
    }
}