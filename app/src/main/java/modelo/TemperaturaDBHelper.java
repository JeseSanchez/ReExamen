package modelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TemperaturaDBHelper extends SQLiteOpenHelper {
    private static final String TEXT_TYPE = "TEXT";
    private static final  String SQL_CREATE_TEMPERATURA = "CREATE TABLE " +
            DefineTabla.Temperaturas.TABLE_NAME+"("+DefineTabla.Temperaturas.COLUM_NAME_ID+" INTEGER PRIMARY KEY, "+
            DefineTabla.Temperaturas.COLUM_NAME_FECHA+" "+TEXT_TYPE+","+
            DefineTabla.Temperaturas.COLUM_NAME_CELCIUS+" "+TEXT_TYPE+","+
            DefineTabla.Temperaturas.COLUM_NAME_FAHRENHEIT+" "+TEXT_TYPE +");";

    private static final String SQL_DELETE_TEMPERATURA =" DROP TABLE IF EXISTS "+ DefineTabla.Temperaturas.TABLE_NAME;

    private static final String DATABASE_NAME = "sistema.db";
    private static final int DATABASE_VERSION = 1;

    public TemperaturaDBHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TEMPERATURA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TEMPERATURA);
        onCreate(db);
    }
}
