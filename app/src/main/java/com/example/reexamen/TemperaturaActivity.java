package com.example.reexamen;

import android.app.AlertDialog;

import android.database.Cursor;

import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import modelo.DefineTabla;
import modelo.TemperaturaDB;

public class TemperaturaActivity extends AppCompatActivity {

    private TemperaturaDB database;
    private float promedio;
    private ListView lst;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;
    private Button btnRegresar,
                   btnLimpiar;
    private TextView lblPromedio;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperatura);
        btnLimpiar = findViewById(R.id.btnLimpiarT);
        btnRegresar = findViewById(R.id.btnRegresar);
        lblPromedio = findViewById(R.id.lblPromedio);
        database = new TemperaturaDB(this);
        lst = findViewById(R.id.lstNombres);
        arrayList = new ArrayList<>();
        promedio = 0;

        Cursor cursor = database.getAllData();
        if (cursor != null) {
            int i=0;
            while (cursor.moveToNext()) {
                String concatenada = cursor.getString(cursor.getColumnIndexOrThrow(DefineTabla.Temperaturas.COLUM_NAME_FECHA)) + ", " +
                        cursor.getString(cursor.getColumnIndexOrThrow(DefineTabla.Temperaturas.COLUM_NAME_CELCIUS)) + ", " +
                        cursor.getString(cursor.getColumnIndexOrThrow(DefineTabla.Temperaturas.COLUM_NAME_FAHRENHEIT));
                promedio += Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(DefineTabla.Temperaturas.COLUM_NAME_FAHRENHEIT)));
                i++;
                arrayList.add(concatenada);
            }
            if(i>0)
                promedio = promedio/i;
            cursor.close();
        }
        lblPromedio.setText("Promedio temperatura F : "+promedio);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, arrayList);
        lst.setAdapter(adapter);

        btnRegresar.setOnClickListener(v -> new AlertDialog
                                                .Builder(this).setTitle("Calculadora")
                                                .setMessage("¿Desea Salir?")
                                                .setPositiveButton("Confirmar", (dialogInterface, i) -> finish())
                                                .setNegativeButton("Cancelar", (dialogInterface, i) -> dialogInterface.cancel())
                                                .show());

        btnLimpiar.setOnClickListener(v -> {
            database.delete();
            finish();
        });
    }
}