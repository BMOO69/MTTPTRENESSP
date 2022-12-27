import java.util.ArrayList;

public class Estadistica {

    public static final String dirEsta = "Estadistica.txt";
    CSVManager csv = CSVManager.getInstance();

    public Estadistica() {}
    public void agregarRecorrido(Riel rl, Tren t,String sal,String lle) {
        String var = sal+"-"+lle+"-"+t.getCodTren()+"-"+t.getNumPasajeros()+"\n";
        csv.write(dirEsta,var);
    }



}
