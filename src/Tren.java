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
    public static ImageIcon imagenOK;
    JLabel etiquetaTren;
    Frame frame;
    Riel rl;

    String nombreEstacionSalida;
    String nombreEstacionLLegada;
    public static JPanel map;
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
        //recoT.cargarCSV("Rieles.csv");
        //boolean direccion = recoT.salirTrenEstaccion(this.getRl(), this.nombreEstacionSalida);
        boolean direccion = recoT.trenSaleDeEsatacion(this.getRl(),this.nombreEstacionSalida);
        double pendiente =pendienteRecta(rl.getEstacionA().getPosX(),rl.getEstacionA().getPosY(),rl.getEstacionB().getPosX(),rl.getEstacionB().getPosY());
        if( pendiente == 0){
            recoT.salirTrenEstaccion(rl,nombreEstacionSalida);
            pendienteCero(direccion);
            System.out.println(this.toString());

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
        double y22 = y1;
        double y11 = y;
        double x22 = x1;
        double x11 = x;

        double res1;
        res1 = (y22 - y11) / (x22 - x11);
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

    private void cambiarEstadoTren(){

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

    public Frame getFrame() {
        return frame;
    }
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
