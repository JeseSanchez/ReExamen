package modelo;

import android.database.Cursor;

import com.example.reexamen.Temperatura;

import java.util.ArrayList;

public interface Proyeccion {
    public Temperatura getTemperatura(String FECHA);
    public ArrayList<Temperatura> AllTemperaturas();
    public Temperatura readTemperatura(Cursor cursor);
}
