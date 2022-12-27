import java.awt.image.AreaAveragingScaleFilter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class CSVManager {

    private  final String SEPARADOR;
    private static CSVManager csv;
    public CSVManager() {
        SEPARADOR = "\\-";
    }
    public static  CSVManager getInstance() {
        if (csv == null) {
            csv = new CSVManager();
        }
        return  csv;
    }

    public void write(String path, String newLine) {
        try {
            FileWriter fileWriter = new FileWriter(path,true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(newLine);
            bufferedWriter.flush();

        }catch (Exception exception) {
            System.out.println("Error while write: " + exception);
        }
    }
    public ArrayList<String[]> read(String path) {
        ArrayList<String[]> result = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                result.add(line.split(SEPARADOR));
                line = bufferedReader.readLine();
            }

        }catch (Exception exception){
            System.out.println("Error while read: "+exception);
        }
        return result;
    }

    public void overWrite(int id, String path, String newLine) {
        try {
            List<String[]> result = read(path);
            FileWriter fileWriter = new FileWriter(path, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (String[] line : result) {
                int idLine = Integer.parseInt(line[0]);
                if (idLine == id) {
                    bufferedWriter.write(newLine);
                } else {

                    String condensedLine = Arrays.toString(line).replace(", ", "-").
                            replaceAll("[\\[\\]]", "") + "\n";
                    bufferedWriter.write(condensedLine);
                }
            }
            bufferedWriter.flush();
        } catch (Exception exception) {
            System.out.println("Error while overWrite: " + path);
        }
    }

    public ArrayList<Riel> rielesAlmacen(ArrayList<String[]> arr){
        ArrayList<Riel> res = new ArrayList<>();
        if (!arr.isEmpty()){
            for (String[] strings : arr) {
                int nRiel = Integer.parseInt(strings[0]);
                Estacion estacionA = recupEstation(strings[1]);
                Estacion estacionB = recupEstation(strings[2]);
                res.add(new Riel(nRiel, estacionA, estacionB));
            }
        }
        return res;
    }
    private Estacion recupEstation(String cad) {
        String[] componentes = cad.split("\\|");
        Estacion es = new Estacion();
        es.setDirEstacion(componentes[0]);
        es.setNumTrenes(Integer.parseInt(componentes[1]));
        es.setOperando(Boolean.parseBoolean(componentes[2]));
        es.setMaxTrenes(Integer.parseInt(componentes[3]));
        es.setPosX(Integer.parseInt(componentes[4]));
        es.setPosY(Integer.parseInt(componentes[5]));

        Queue<Tren> trenes;
        try {
            trenes = obtenerTrenes(componentes[6]);
        }catch (ArrayIndexOutOfBoundsException e) {
            trenes = new PriorityQueue<>();
        }

        es.setParqueo(trenes);
        return es;
    }

    public Queue<Tren> obtenerTrenes(String cad){
        String[] array = cad.split(",");
        Queue<Tren> res = new PriorityQueue<>();
        if (array.length !=0){
            for (String s : array) {
                String[] var = s.split("#");
                String codT = var[0];
                int numV = Integer.parseInt(var[1]);
                int nump = Integer.parseInt(var[2]);
                int color = Integer.parseInt(var[3]);
                Tren nuevo = new Tren(codT, numV, nump, color);
                res.add(nuevo);
            }
        }
        return res;
    }
}
