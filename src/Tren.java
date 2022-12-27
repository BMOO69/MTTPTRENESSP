import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Comparator;

public class Tren extends Thread implements Comparable<Tren> {

    public static final String DIRRIELES = "Rieles.csv";
    public String codTren;
    public int numPasajeros;
    public int numVagones;
    public int colorSemaforo;
    public JLabel etiquetaTren;
    public Frame frame;
    public Riel rl;
    public String nombreEstacionSalida;
    public String nombreEstacionLLegada;
    public static JPanel map;
    public Estadistica est;
    public Tren(){}
    public Tren(String codTren,int numVagones, int numPasajeros,int colorSemaforo ) {
        this.codTren = codTren;
        this.numVagones = numVagones;
        this.numPasajeros = numPasajeros;
        this.colorSemaforo = colorSemaforo;
    }
    @Override
    public void run() {
        RecorrerTren recoT = new RecorrerTren(DIRRIELES);
        boolean direccion = recoT.trenSaleDeEsatacion(this.getRl(),this.nombreEstacionSalida);
        double pendiente =pendienteRecta(rl.getEstacionA().getPosX(),rl.getEstacionA().getPosY(),rl.getEstacionB().getPosX(),rl.getEstacionB().getPosY());
        est = new Estadistica();
        est.agregarRecorrido(getRl(),this,this.nombreEstacionSalida,this.nombreEstacionLLegada);
        if( pendiente == 0){
            recoT.salirTrenEstaccion(rl,nombreEstacionSalida);
            pendienteCero(direccion);
        } else {
            recoT.salirTrenEstaccion(rl,nombreEstacionSalida);
            pendienteInfinito(direccion);
        }
        map.remove(etiquetaTren);
    }
    public void raton(JLabel lv,Estacion salida,Estacion llegada) {
        lv.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Semaforo sema = new Semaforo();

                ImageIcon var = sema.funcionarSemaforo(colorSemaforo);
                JOptionPane.showMessageDialog(frame,"Estacion de Salida: "+salida.getDirEstacion()+"\n"+"Estacion a la llegada: "+ llegada.getDirEstacion()+"\n"
                        +"El codigo del tren: "+codTren+"\n"+"Nemero de vagones tren: "+numVagones+"\n"+"Numero de pasajeros tren: "+numPasajeros
                        ,"Informacion de la estacion",
                        JOptionPane.INFORMATION_MESSAGE,var);
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    private void pendienteInfinito(boolean dir) {
        Semaforo sema = new Semaforo();
        if (dir == true) {
            int xx = rl.getEstacionA().getPosX();
            int yy = rl.getEstacionA().getPosY();
            raton(etiquetaTren, rl.getEstacionA(), rl.getEstacionB());
            while (yy <= rl.getEstacionB().getPosY()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                yy = yy + 40;
                etiquetaTren.setBounds(xx, yy, 50, 50);
            }
        }else {
            int xx = rl.getEstacionB().getPosX();
            int yy = rl.getEstacionB().getPosY();
            raton(etiquetaTren,rl.getEstacionB(),rl.getEstacionA());
            while (yy >= rl.getEstacionA().getPosY()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                yy = yy - 40;
                etiquetaTren.setBounds(xx, yy, 50, 50);
            }

        }
    }
    private double pendienteRecta (int x,int y,int x1,int y1) {

        double res1;
        res1 = ((double) y1 - (double) y) / ((double) x1 - (double) x);
        return res1;
    }
    private void pendienteCero(boolean dir) {
        if (dir == true) {
            int xx = rl.getEstacionA().getPosX();
            int yy = rl.getEstacionA().getPosY();
            raton(etiquetaTren,rl.getEstacionA(),rl.getEstacionB());
            while (xx <= rl.getEstacionB().getPosX()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                xx = xx + 40;
                etiquetaTren.setBounds(xx, yy, 50, 50);
            }
        } else {
            int xx = rl.getEstacionB().getPosX();
            int yy = rl.getEstacionB().getPosY();
            raton(etiquetaTren,rl.getEstacionB(),rl.getEstacionA());
            while (xx >= rl.getEstacionA().getPosX()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                xx = xx - 40;
                etiquetaTren.setBounds(xx, yy, 50, 50);
            }
        }
    }

    public String toString() {
        String res;
        res = codTren + "#" + numVagones + "#" + numPasajeros+ "#" + colorSemaforo;
        return  res;
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
    public static void setMap(JPanel map) {
        Tren.map = map;
    }
    public void setNombreEstacionSalida(String nombreEstacion) {
        this.nombreEstacionSalida = nombreEstacion;
    }
    public void setNombreEstacionLLegada(String nombreEstacionLLegada) {
        this.nombreEstacionLLegada = nombreEstacionLLegada;
    }
    public String getCodTren() {
        return codTren;
    }
    public int getNumPasajeros() {return numPasajeros; }
    public int getColorSemaforo() {
        return colorSemaforo;
    }
    public void setColorSemaforo(int colorSemaforo) {
        this.colorSemaforo = colorSemaforo;
    }
    @Override
    public int compareTo(Tren o) {
        return o.colorSemaforo < this.colorSemaforo ? 1: -1;
    }
}
