package modelo;


import com.example.reexamen.Temperatura;

public interface Persitencia {
    public void openDataBase();
    public void closeDataBase();
    public long insetTemperatura(Temperatura temperatura);
    public long updateTemperatura(Temperatura temperatura);
    public void deleteTemperatura(int id);
}
