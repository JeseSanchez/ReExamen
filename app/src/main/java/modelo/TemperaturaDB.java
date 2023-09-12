package modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.reexamen.Temperatura;

import java.util.ArrayList;

public class TemperaturaDB implements Persitencia, Proyeccion{
    private Context context;
    private TemperaturaDBHelper helper;
    private SQLiteDatabase db;

    public TemperaturaDB(Context context, TemperaturaDBHelper helper){
        this.context = context;
        this.helper =helper;
    }

    public TemperaturaDB(Context context){
        this.context = context;
        this.helper =new TemperaturaDBHelper(this.context);
    }

    @Override
    public void openDataBase() {
        db = helper.getWritableDatabase();
    }

    @Override
    public void closeDataBase() {
        helper.close();
    }

    @Override
    public long insetTemperatura(Temperatura temperatura) {
        ContentValues values = new ContentValues();
        values.put(DefineTabla.Temperaturas.COLUM_NAME_FECHA,temperatura.getFecha());
        values.put(DefineTabla.Temperaturas.COLUM_NAME_CELCIUS,temperatura.getCelcius());
        values.put(DefineTabla.Temperaturas.COLUM_NAME_FAHRENHEIT,temperatura.getFahrenheit());

        this.openDataBase();
        long num = db.insert(DefineTabla.Temperaturas.TABLE_NAME,null,values);
        this.closeDataBase();
        return num;
    }

    @Override
    public long updateTemperatura(Temperatura temperatura) {
        ContentValues values = new ContentValues();
        values.put(DefineTabla.Temperaturas.COLUM_NAME_FECHA,temperatura.getFecha());
        values.put(DefineTabla.Temperaturas.COLUM_NAME_CELCIUS,temperatura.getCelcius());
        values.put(DefineTabla.Temperaturas.COLUM_NAME_FAHRENHEIT,temperatura.getFahrenheit());

        this.openDataBase();
        long num = db.update(DefineTabla.Temperaturas.TABLE_NAME,values,DefineTabla.Temperaturas.COLUM_NAME_ID + temperatura.getId(),null);
        this.closeDataBase();
        return num;
    }

    @Override
    public void deleteTemperatura(int id) {
        this.openDataBase();
        db.delete(DefineTabla.Temperaturas.TABLE_NAME,DefineTabla.Temperaturas.COLUM_NAME_ID+"=?",new String[]{String.valueOf(id)});
        this.closeDataBase();
    }

    public void delete() {
        this.openDataBase();
        db.delete(DefineTabla.Temperaturas.TABLE_NAME,null,null);
        this.closeDataBase();
    }

    @Override
    public Temperatura getTemperatura(String FECHA) {
        db = helper.getWritableDatabase();
        Cursor cursor = db.query(DefineTabla.Temperaturas.TABLE_NAME,DefineTabla.Temperaturas.REGISTRO,DefineTabla.Temperaturas.COLUM_NAME_FECHA+"=?",new String[]{FECHA},null,null,null);
        cursor.moveToFirst();
        Temperatura alumno = readTemperatura(cursor);
        return alumno;
    }

    @Override
    public ArrayList<Temperatura> AllTemperaturas() {
        db = helper.getWritableDatabase();
        Cursor cursor = db.query(DefineTabla.Temperaturas.TABLE_NAME,DefineTabla.Temperaturas.REGISTRO,null,null,null,null,null);
        ArrayList<Temperatura> temperaturas = new ArrayList<>();
        cursor.moveToFirst();
        while(cursor.isAfterLast()){
            Temperatura temperatura = readTemperatura(cursor);
            temperaturas.add(temperatura);
            cursor.moveToNext();
        }
        cursor.close();
        return temperaturas;
    }

    @Override
    public Temperatura readTemperatura(Cursor cursor) {
        Temperatura temperatura = new Temperatura();
        temperatura.setId(cursor.getInt(0));
        temperatura.setFecha(cursor.getString(1));
        temperatura.setCelcius(cursor.getString(2));
        temperatura.setFahrenheit(cursor.getString(3));
        return temperatura;
    }

    public Cursor getAllData() {
        db = helper.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + DefineTabla.Temperaturas.TABLE_NAME, null);
    }
}
