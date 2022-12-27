import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VenEstadistica  extends JFrame {

    CSVManager csv = CSVManager.getInstance();
    private static final String dirEst = "Estadistica.txt";
    JPanel panel;

    JButton button;
    private JTextArea area;
    private JScrollPane scroll;
    ArrayList<String[]> variablesEstadisticos;
    public VenEstadistica() {
        setSize(500,500);
        setTitle("Estadistica de los trenes");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(null);
        variablesEstadisticos = csv.read(dirEst);
        initComponet();
    }
    public void initComponet() {
        panel = new JPanel();
        panel.setBackground(Color.YELLOW);
        panel.setLayout(null);
        setLocationRelativeTo(null);

        area = new JTextArea();
        scroll = new JScrollPane(area);

        scroll.setBounds(10,50,400,300);
        this.getContentPane().add(scroll);
        imprimir();

    }

    public ArrayList<String> estaciones(ArrayList<String[]> est) {
        ArrayList<String> res = new ArrayList<>();
        for (String[] i: est) {
            if (!res.contains(i[0])) {
                res.add(i[0]);
            }
        }
        return res;
    }

    public void imprimir() {
        ArrayList<String> vares = estaciones(variablesEstadisticos);

        for (String i: vares) {
            int cantTrenesSalido = 0;
            int cantTrenesLlegado = 0;
            int pasajerosLlegados = 0;
            int pasajerosSalidos= 0;
            ArrayList<String> codTrenesSalidos = new ArrayList<>();
            ArrayList<String> codTrenesLlegados = new ArrayList<>();

            for (int j = 0; j< variablesEstadisticos.size();j++) {
                if (variablesEstadisticos.get(j)[0].equals(i)) {
                    cantTrenesSalido = cantTrenesSalido +1;
                    pasajerosSalidos = pasajerosSalidos+ Integer.parseInt(variablesEstadisticos.get(j)[3]);
                    codTrenesSalidos.add(variablesEstadisticos.get(j)[2]);
                }else if (variablesEstadisticos.get(j)[1].equals(i)) {
                    cantTrenesLlegado = cantTrenesLlegado+1;
                    pasajerosLlegados = pasajerosLlegados+ Integer.parseInt(variablesEstadisticos.get(j)[3]);
                    codTrenesLlegados.add(variablesEstadisticos.get(j)[2]);
                }
            }
            area.append("----: "+i+"\n");
            area.append("cantidad de trenes salido: "+cantTrenesSalido+"\n");
            area.append("cantidad de trenes llegado: "+ cantTrenesLlegado+"\n");
            area.append("la cantidad de pasajeros llegados a la estacion: "+pasajerosLlegados+"\n");
            area.append("la cantidad de pasajeros salidos de la estacion: "+pasajerosSalidos+"\n");
            area.append("los codiogos de los trenes salidos de la estacion: "+"\n");
            for (String m: codTrenesSalidos) {
                area.append("   "+m+"\n");
            }
            area.append("los codigos de los trenes q llegaron a la estacion: "+"\n");
            for (String n: codTrenesLlegados) {
                area.append("   "+n+"\n");
            }
            area.append("\n");
            area.append("-------------------------------------\n");
            area.append("\n");
        }
    }
}
