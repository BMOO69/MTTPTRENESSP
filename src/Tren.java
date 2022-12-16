import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Tren extends Thread{

    public String codTren;
    public int numPasajeros;
    public int numVagones;
    public static ImageIcon imagenOK;
    JLabel etiquetaTren;
    Frame frame;
    Riel rl;

    String nombreEstacionSalida;
    String nombreEstacionLLegada;
    public static JPanel map;
    //RecorrerTren reco  = new RecorrerTren("Rieles.csv");
    public Tren(String codTren, int numVagones, int numPasajeros) {
        this.codTren = codTren;
        this.numVagones = numVagones;
        this.numPasajeros = numPasajeros;
    }

    @Override
    public void run() {
        RecorrerTren recoT = new RecorrerTren();
        recoT.cargarCSV("Rieles.csv");
        boolean direccion = recoT.salirTrenEstaccion(this.getRl(), this.nombreEstacionSalida);
        double pendiente =pendienteRecta(rl.getEstacionA().getPosX(),rl.getEstacionA().getPosY(),rl.getEstacionB().getPosX(),rl.getEstacionB().getPosY());
        if( pendiente == 0){
            System.out.println(pendiente);
            pendienteCero(direccion);
        } else {
            System.out.println(direccion);
            pendienteInfinito(direccion);
        }

        System.out.println(direccion);
        //map.remove(etiquetaTren);
    }
    public void raton(JLabel lv,Estacion salida,Estacion llegada) {
        lv.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(frame,"Estacion de Salida: "+salida.getDirEstacion()+"\n"+"Estacion a la llegada: "+ llegada.getDirEstacion(),
                        "Informacion de la estacion",
                        JOptionPane.INFORMATION_MESSAGE,imagenOK);
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

    public String getNombreEstacionSalida() {
        return nombreEstacionSalida;
    }

    public void setNombreEstacionSalida(String nombreEstacion) {
        this.nombreEstacionSalida = nombreEstacion;
    }

    public String getNombreEstacionLLegada() {
        return nombreEstacionLLegada;
    }

    public void setNombreEstacionLLegada(String nombreEstacionLLegada) {
        this.nombreEstacionLLegada = nombreEstacionLLegada;
    }

    public Frame getFrame() {
        return frame;
    }

    public void setFrame(Frame frame) {
        this.frame = frame;
    }
}
