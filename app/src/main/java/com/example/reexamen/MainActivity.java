package com.example.reexamen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import modelo.TemperaturaDB;
import modelo.TemperaturaDBHelper;

public class MainActivity extends AppCompatActivity{
    private EditText txtFecha,
                     txtTemperatura;
    private TextView lblFahrenheit;
    private Button btnConvertir,
                   btnGuardar,
                   btnLimpiar,
                   btnVer,
                   btnSalir;
    private TemperaturaDBHelper helper;
    private TemperaturaDB database;
    private  int fahrenheit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtFecha = findViewById(R.id.txtFecha);
        txtTemperatura = findViewById(R.id.txtTemperatura);
        lblFahrenheit = findViewById(R.id.lblFahrenheit);
        btnConvertir = findViewById(R.id.btnConvertir);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnLimpiar = findViewById(R.id.btnLimpiar);
        btnVer = findViewById(R.id.btnVer);
        btnSalir = findViewById(R.id.btnSalir);
        Context context = this;
        fahrenheit = 0;
        helper = new TemperaturaDBHelper(this);
        database = new TemperaturaDB(context,helper);
        btnConvertir.setOnClickListener(v -> {
            if (txtTemperatura.getText().toString().matches("") ||
                    txtFecha.getText().toString().matches("")) {
                Toast.makeText(context, "Favor de ingresar los datos faltantes", Toast.LENGTH_SHORT).show();
            } else {
                fahrenheit = 32 + Integer.parseInt(txtTemperatura.getText().toString()) * 9 / 5;
                lblFahrenheit.setText("fahrenheit : " + fahrenheit);
            }
        });

        btnGuardar.setOnClickListener(v -> {
            if (txtTemperatura.getText().toString().matches("") ||
                    txtFecha.getText().toString().matches("")||
                    fahrenheit==0) {
                Toast.makeText(context, "Favor de ingresar los datos faltantes", Toast.LENGTH_SHORT).show();
            } else {
                database.insetTemperatura(new Temperatura(txtFecha.getText().toString(),txtTemperatura.getText().toString(),String.valueOf(fahrenheit)));
                Toast.makeText(context,"Guardado",Toast.LENGTH_SHORT).show();
            }
        });

        btnVer.setOnClickListener(v -> startActivity(new Intent(context, TemperaturaActivity.class)));

        btnLimpiar.setOnClickListener(v -> {
            txtFecha.setText("");
            txtTemperatura.setText("");
            lblFahrenheit.setText("Fahrenheit: ");
        });

        btnSalir.setOnClickListener(v -> new AlertDialog
                                             .Builder(context)
                                             .setTitle("Calculadora")
                                             .setMessage("¿Desea Salir?")
                                             .setPositiveButton("Confirmar", (dialogInterface, i) -> finish())
                                             .setNegativeButton("Cancelar", (dialogInterface, i) -> dialogInterface.cancel())
                                             .show());
    }
}