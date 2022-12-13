import sun.security.jgss.GSSHeader;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Mapa extends JFrame {
    
    CSVManager csv = CSVManager.getInstance();

    ArrayList<String[]> ccssvv;
    ArrayList<Riel> rieel;
    ArrayList<Tren> treenes;
    ArrayList<Estacion> stations;

    public Mapa() {
        setSize(1000,800);
        setLocation(100,100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Control de operaciones de trenes");
        //this.getContentPane().setBackground(Color.green);
        iniciarComponentes();
    }

    private void iniciarComponentes() {

        ccssvv = csv.read("Rieles.csv");
        rieel = csv.rielesAlmacen(ccssvv);
        //JPanel panel = new JPanel();


        JPanel panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);

                Graphics2D g2 =(Graphics2D) g;
                g2.setStroke(new BasicStroke(10));
                if(!rieel.isEmpty()) {
                    for (int i= 0; i<rieel.size(); i++) {
                        Estacion estacionA = rieel.get(i).getEstacionA();
                        Estacion estacionB = rieel.get(i).getEstacionB();

                        g.drawLine(estacionA.getPosX(),estacionA.getPosY(),estacionB.getPosX(),estacionB.getPosY());
                    }
                }
            }
        };
        Tren.setMap(panel);
        panel.setBackground(Color.green);
        panel.setLayout(null);
        this.getContentPane().add(panel);
        JLabel etiqueta = new JLabel();

        ImageIcon imagen = new ImageIcon("trainStation.png");
        if (!rieel.isEmpty()){
            for (Riel i : rieel) {

                JLabel etiA = new JLabel();
                etiA.setBounds(i.getEstacionA().getPosX()-50,i.getEstacionA().getPosY()-50,100,100);
                etiA.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH)));
                JLabel nombreEtiA = new JLabel(i.getEstacionA().getDirEstacion());
                nombreEtiA.setBounds(i.getEstacionA().getPosX()-50,i.getEstacionA().getPosY()-60,100,10);
                panel.add(nombreEtiA);
                panel.add(etiA);

                JLabel etiB = new JLabel();

                etiB.setBounds(i.getEstacionB().getPosX()-50,i.getEstacionB().getPosY()-50,100,100);
                etiB.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH)));
                JLabel nombreEtiB = new JLabel(i.getEstacionB().getDirEstacion());
                nombreEtiB.setBounds(i.getEstacionB().getPosX()-50,i.getEstacionB().getPosY()-60,100,10);
                panel.add(nombreEtiB);
                panel.add(etiB);
            }
        }

        salidaTrenEstacion("Cercado");
        Tren t = new Tren("adas",3,90);
        ImageIcon im = new ImageIcon("trenn.jpg");
        JLabel nn = new JLabel();
        nn.setBounds(250,350,50,50);
        nn.setIcon(new ImageIcon(im.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH)));
        panel.add(nn);

        t.setEtiquetaTren(nn);
        t.setRl(rieel.get(0));
        t.start();

    }
    public void salidaTrenEstacion(String nomb){}

}
