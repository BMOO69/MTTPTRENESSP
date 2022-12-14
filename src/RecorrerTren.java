import java.util.ArrayList;

public class RecorrerTren {
    public ArrayList<String> estaciones;
    CSVManager csv = CSVManager.getInstance();
    ArrayList<String[]> cssvv;
    ArrayList<Riel> rieles;
    String dir = "Rieles.csv";

    public RecorrerTren(){
    }
    public void cargarCSV(String path) {
        cssvv = csv.read(path);
        rieles = csv.rielesAlmacen(cssvv);
    }
    public boolean salirTrenEstaccion(Riel rl, String nombresEst) {

        boolean res;

        Estacion estA = rl.getEstacionA();
        Estacion estB = rl.getEstacionB();
        if (estA.getDirEstacion().equals(nombresEst)) {
            Tren salido = estA.parqueo.poll();
            estB.almacenarTren(salido);
            Riel over = new Riel(rl.numRiel,estA,estB);

            csv.overWrite(rl.numRiel,dir,over.toString());
            res = true;
        }else{
            Tren var = estB.parqueo.poll();
            estA.almacenarTren(var);
            Riel over = new Riel(rl.numRiel,estA,estB);

            csv.overWrite(rl.numRiel,dir,over.toString());
            res = false;
        }
        return res;
    }

    public Riel encontrarRiel(String nombreEs) {
        Riel res = new Riel();
        if (!rieles.isEmpty()) {
            for (Riel i: rieles) {
                if (i.getEstacionA().getDirEstacion().equals(nombreEs) || i.getEstacionB().getDirEstacion().equals(nombreEs)) {
                    res = i;
                    break;
                }
            }
        }
        return  res;
    }
}
