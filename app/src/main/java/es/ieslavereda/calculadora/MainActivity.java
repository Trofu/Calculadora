package es.ieslavereda.calculadora;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CheckBox opciones;
    private RadioGroup opcionesDisponibles;
    private TextView numeros;
    private Button clear;
    private Button delete;
    private Button decimal;
    private Button suma;
    private Button resta;
    private Button division;
    private Button por;
    private Button resto;
    private Button igual;
    private Operaciones operacion;
    private String carreo;
    private RadioButton omas;
    private RadioButton omenos;
    private RadioButton opor;
    private RadioButton odivi;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        carreo= String.valueOf(0);
        operacion=null;
        opciones = findViewById(R.id.Mopciones);
        opcionesDisponibles = findViewById(R.id.Opciones);
        numeros = findViewById(R.id.texto);
        clear = findViewById(R.id.buttonAC);
        delete = findViewById(R.id.buttondelete);
        decimal = findViewById(R.id.buttonDecimal);
        suma = findViewById(R.id.buttonMas);
        resta = findViewById(R.id.buttonMenos);
        division = findViewById(R.id.buttonDivi);
        por = findViewById(R.id.buttonPor);
        resto = findViewById(R.id.buttonPorcentaje);
        igual = findViewById(R.id.buttonIgual);
        omas = findViewById(R.id.OutMas);
        omenos = findViewById(R.id.OutMenos);
        opor = findViewById(R.id.OutMulti);
        odivi = findViewById(R.id.OutDivi);
        opcionesDisponibles.setVisibility(View.INVISIBLE);

        if(savedInstanceState!=null){
            numeros.setText((CharSequence) savedInstanceState.getSerializable("barra"));
            carreo = (String) savedInstanceState.getSerializable("oculto");
            operacion = (Operaciones) savedInstanceState.getSerializable("operacion");
        }

        opciones.setOnClickListener(View -> {

            if (!opciones.isChecked()){
                opcionesDisponibles.setVisibility(android.view.View.INVISIBLE);

                resta.setVisibility(View.VISIBLE);
                por.setVisibility(View.VISIBLE);
                division.setVisibility(View.VISIBLE);
                suma.setVisibility(View.VISIBLE);

                omas.setChecked(false);
                omenos.setChecked(false);
                opor.setChecked(false);
                odivi.setChecked(false);
            }else {
                opcionesDisponibles.setVisibility(android.view.View.VISIBLE);
            }

        });

        clear.setOnClickListener(View -> {
            numeros.setText("0");
            carreo="";
        });

        delete.setOnClickListener(View -> {
            String cadena = numeros.getText().toString();
            cadena = cadena.substring(0,cadena.length()-1);
            if (cadena.length() == 0)cadena = "0";
            numeros.setText(cadena);
        });

        decimal.setOnClickListener(View -> {
            String cadena = numeros.getText().toString();
            if (!cadena.contains("."))cadena = cadena+decimal.getText().toString();
            numeros.setText(cadena);
        });

        suma.setOnClickListener(View -> {
            operacion = Operaciones.SUMA;
            carreo = numeros.getText().toString();
            numeros.setText("0");
        });
        resta.setOnClickListener(View -> {
            operacion = Operaciones.RESTA;
            carreo = numeros.getText().toString();
            numeros.setText("0");
        });
        por.setOnClickListener(View -> {
            operacion = Operaciones.POR;
            carreo = numeros.getText().toString();
            numeros.setText("0");
        });
        division.setOnClickListener(View -> {
            operacion = Operaciones.DIVISION;
            carreo = numeros.getText().toString();
            numeros.setText("0");
        });
        resto.setOnClickListener(View -> {
            operacion = Operaciones.RESTO;
            carreo = numeros.getText().toString();
            numeros.setText("0");
        });

        igual.setOnClickListener(View->{
            String cadena="";
            if (operacion == Operaciones.SUMA){
                if (carreo.contains(".") || numeros.getText().toString().contains(".")){
                    cadena = String.valueOf(Double.parseDouble(carreo)+Double.parseDouble(numeros.getText().toString()));
                    numeros.setText(cadena);
                }else {
                    cadena = String.valueOf(Integer.parseInt(carreo)+Integer.parseInt(numeros.getText().toString()));
                    numeros.setText(cadena);
                }
            } else if (operacion == Operaciones.RESTA){
                if (carreo.contains(".") || numeros.getText().toString().contains(".")){
                    cadena = String.valueOf(Double.parseDouble(carreo)-Double.parseDouble(numeros.getText().toString()));
                    numeros.setText(cadena);
                }else {
                    cadena = String.valueOf(Integer.parseInt(carreo)-Integer.parseInt(numeros.getText().toString()));
                    numeros.setText(cadena);
                }
            } else if (operacion == Operaciones.POR){
                if (carreo.contains(".") || numeros.getText().toString().contains(".")){
                    cadena = String.valueOf(Double.parseDouble(carreo)*Double.parseDouble(numeros.getText().toString()));
                    numeros.setText(cadena);
                }else {
                    cadena = String.valueOf(Integer.parseInt(carreo)*Integer.parseInt(numeros.getText().toString()));
                    numeros.setText(cadena);
                }
            } else if (operacion == Operaciones.DIVISION){
                    cadena = String.valueOf(Double.parseDouble(carreo)/Double.parseDouble(numeros.getText().toString()));
                    numeros.setText(cadena);
            }else if (operacion == Operaciones.RESTO){
                    cadena = String.valueOf(Integer.parseInt(carreo)%Integer.parseInt(numeros.getText().toString()));
                    numeros.setText(cadena);
            }
        });



        omas.setOnClickListener(view -> {
            resta.setVisibility(View.VISIBLE);
            por.setVisibility(View.VISIBLE);
            division.setVisibility(View.VISIBLE);
            if (!omas.isChecked()){
                suma.setVisibility(View.VISIBLE);
            }else {
                suma.setVisibility(View.INVISIBLE);
            }
        });
        omenos.setOnClickListener(view -> {
            suma.setVisibility(View.VISIBLE);
            por.setVisibility(View.VISIBLE);
            division.setVisibility(View.VISIBLE);
            if (!omenos.isChecked()){
                resta.setVisibility(View.VISIBLE);
            }else {
                resta.setVisibility(View.INVISIBLE);
            }
        });
        opor.setOnClickListener(view -> {
            suma.setVisibility(View.VISIBLE);
            resta.setVisibility(View.VISIBLE);
            division.setVisibility(View.VISIBLE);
            if (!opor.isChecked()){
                por.setVisibility(View.VISIBLE);
            }else {
                por.setVisibility(View.INVISIBLE);
            }
        });
        odivi.setOnClickListener(view -> {
            suma.setVisibility(View.VISIBLE);
            resta.setVisibility(View.VISIBLE);
            por.setVisibility(View.VISIBLE);
            if (!odivi.isChecked()){
                division.setVisibility(View.VISIBLE);
            }else {
                division.setVisibility(View.INVISIBLE);
            }
        });
    }
    @Override
    public void onClick(View view) {
        if(numeros.getText().toString().equals("0")){
            numeros.setText(((Button)view).getText());
        }
        else{
            numeros.setText(String.valueOf(numeros.getText())+((Button)view).getText());
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("oculto", carreo);
        outState.putSerializable("barra", numeros.getText().toString());
        outState.putSerializable("operacion", operacion);
    }

    private enum Operaciones{
        SUMA,
        RESTA,
        DIVISION,
        POR,
        RESTO;
    }


}