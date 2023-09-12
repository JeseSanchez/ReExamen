package modelo;

public class DefineTabla {
    public DefineTabla(){
    }
    public static abstract class Temperaturas{
        public static final String  TABLE_NAME = "temperaturas";
        public static final String  COLUM_NAME_ID = "id";
        public static final String  COLUM_NAME_FECHA = "fecha";
        public static final String  COLUM_NAME_CELCIUS = "celcius";
        public static final String  COLUM_NAME_FAHRENHEIT = "fahrenheit";

        public static  String[] REGISTRO = new String[]{
                Temperaturas.COLUM_NAME_ID,
                Temperaturas.COLUM_NAME_FECHA,
                Temperaturas.COLUM_NAME_CELCIUS,
                Temperaturas.COLUM_NAME_FAHRENHEIT
        };
    }
}