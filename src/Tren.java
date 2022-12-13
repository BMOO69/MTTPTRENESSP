import javax.swing.*;
import java.awt.*;

public class Tren extends Thread{

    public String codTren;
    public int numPasajeros;
    public int numVagones;
    JLabel etiquetaTren;
    Riel rl;
    public static JPanel map;
    RecorrerTren reco  = new RecorrerTren("Rieles.csv");
    public Tren(String codTren, int numVagones, int numPasajeros) {
        this.codTren = codTren;
        this.numVagones = numVagones;
        this.numPasajeros = numPasajeros;
    }

    @Override
    public void run(){

    }
    /*@Override
    public void run() {
        int xx = rl.getEstacionA().getPosX();
        int yy = rl.getEstacionA().getPosY();

        while (xx <= rl.getEstacionB().getPosX()) {
            try {
                Thread.sleep(700);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            xx = xx+40;
            yy = yy;
            etiquetaTren.setBounds(xx,yy,50,50);
            System.out.println(rl.getEstacionA().getPosX()+"    "+rl.estacionB.getPosX());
            System.out.println(xx+"   "+yy);
        }
        map.remove(etiquetaTren);
    }*/


    public String toString() {
        String res;
        res = codTren + "#" + numVagones + "#" + numPasajeros;
        return  res;
    }


    public String getCodTren() {
        return codTren;
    }
    public void setCodTren(String codTren) {
        this.codTren = codTren;
    }
    public int getNumPasajeros() {
        return numPasajeros;
    }
    public void setNumPasajeros(int numPasajeros) {
        this.numPasajeros = numPasajeros;
    }
    public int getNumVagones() {
        return numVagones;
    }
    public void setNumVagones(int numVagones) {
        this.numVagones = numVagones;
    }

    public JLabel getEtiquetaTren() {
        return etiquetaTren;
    }

    public void setEtiquetaTren(JLabel etiquetaTren) {
        this.etiquetaTren = etiquetaTren;
    }

    public Riel getRl() {
        return rl;
    }

    public void setRl(Riel rl) {
        this.rl = rl;
    }

    public static JPanel getMap() {
        return map;
    }

    public static void setMap(JPanel map) {
        Tren.map = map;
    }
}
