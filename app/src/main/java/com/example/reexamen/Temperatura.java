package com.example.reexamen;

public class Temperatura {
    private int id;
    private String fecha,
                   celcius,
                   fahrenheit;

    public Temperatura(){

    }
    public Temperatura(String fecha,String celcius,String fahrenheit){
        this.setFecha(fecha);
        this.setCelcius(celcius);
        this.setFahrenheit(fahrenheit);
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCelcius() {
        return celcius;
    }

    public void setCelcius(String celcius) {
        this.celcius = celcius;
    }

    public String getFahrenheit() {
        return fahrenheit;
    }

    public void setFahrenheit(String fahrenheit) {
        this.fahrenheit = fahrenheit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
