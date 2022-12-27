import java.util.ArrayList;

public class RecorrerTren {
    public ArrayList<String> estaciones;
    CSVManager csv = CSVManager.getInstance();
    ArrayList<String[]> cssvv;
    ArrayList<Riel> rieles;
    String dir = "Rieles.csv";

    public RecorrerTren(String path){
        cssvv = csv.read(path);
        rieles = csv.rielesAlmacen(cssvv);
    }
    public void cargarCSV(String path) {
        cssvv = csv.read(path);
        rieles = csv.rielesAlmacen(cssvv);
    }
    public void salirTrenEstaccion(Riel rl, String nombresEst) {

        Semaforo colorTren = new Semaforo();

        Estacion estA = rl.getEstacionA();
        Estacion estB = rl.getEstacionB();
        if (estA.getDirEstacion().equals(nombresEst)) {
            Tren salido = estA.parqueo.poll();
            salido.setColorSemaforo(colorTren.cambioSemaforo(salido.getColorSemaforo()));
            estB.almacenarTren(salido);
            Riel over = new Riel(rl.numRiel,estA,estB);
            csv.overWrite(rl.numRiel,dir,over.toString());
            modificarLosDemasRieles(rl,estA,estB);
        }else{
            Tren var = estB.parqueo.poll();
            var.setColorSemaforo(colorTren.cambioSemaforo(var.getColorSemaforo()));
            estA.almacenarTren(var);
            Riel over = new Riel(rl.numRiel,estA,estB);

            csv.overWrite(rl.numRiel,dir,over.toString());
            modificarLosDemasRieles(rl,estB,estA);

        }
    }
    public boolean trenSaleDeEsatacion(Riel rl,String nombreEstacionSalida) {
        boolean res;
        Estacion estA = rl.getEstacionA();
        Estacion estB = rl.getEstacionB();
        if (estA.getDirEstacion().equals(nombreEstacionSalida)) {
            res = true;
        }else {
            res = false;
        }
        return res;
    }
    public void modificarLosDemasRieles(Riel rl,Estacion estA,Estacion estB) {/// pare aqui resolver mañana
        if (!rieles.isEmpty()) {
            for (Riel i : rieles) {
                if (i.getNumRiel() != rl.getNumRiel()) {

                    if (estA.getDirEstacion().equals(i.getEstacionA().getDirEstacion())) {
                        Riel over = new Riel(i.getNumRiel(),estA,i.getEstacionB());
                        csv.overWrite(i.getNumRiel(),dir,over.toString());
                    }
                    if (estA.getDirEstacion().equals(i.getEstacionB().dirEstacion)) {
                        Riel over = new Riel(i.getNumRiel(),i.getEstacionA(),estA);
                        csv.overWrite(i.getNumRiel(),dir,over.toString());
                    }
                    if (estB.getDirEstacion().equals(i.getEstacionA().getDirEstacion())) {
                        Riel over = new Riel(i.getNumRiel(),estB,i.getEstacionB());
                        csv.overWrite(i.getNumRiel(),dir,over.toString());
                    }
                    if (estB.getDirEstacion().equals(i.getEstacionB().getDirEstacion())) {
                        Riel over = new Riel(i.getNumRiel(),i.getEstacionA(),estB);
                        csv.overWrite(i.getNumRiel(),dir,over.toString());
                    }

                }
            }
        }
    }
    public Riel encontrarRiel(String nombreEsSalida,String nombreEsLlegada) {
        //Riel res = new Riel(0,new Estacion(),new Estacion());
        Riel res=new Riel(0);;
        if (!rieles.isEmpty()) {
            for (Riel i: rieles) {
                if ((i.getEstacionA().getDirEstacion().equals(nombreEsSalida) && i.getEstacionB().getDirEstacion().equals(nombreEsLlegada)) ||
                        (i.getEstacionB().getDirEstacion().equals(nombreEsSalida) && i.getEstacionA().getDirEstacion().equals(nombreEsLlegada))) {

                    res = i;
                        break;

                }
            }
        }
        return  res;
    }
}
