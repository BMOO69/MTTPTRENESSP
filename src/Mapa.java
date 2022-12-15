import sun.security.jgss.GSSHeader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
        RecorrerTren recoT = new RecorrerTren();

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
        ImageIcon imagenOK = new ImageIcon("check.png");
        ImageIcon im = new ImageIcon("trenn.jpg");
        Tren.imagenOK= imagenOK;
        Icon icono = new ImageIcon(im.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH));
        String estaci = "Cercado";
        String estaci1 = "Tarata";



        JLabel nn = new JLabel();
        JLabel nn1 = new JLabel();

        nn.setIcon(icono);
        nn1.setIcon(icono);
        recoT.cargarCSV("Rieles.csv");
        Riel rrll=recoT.encontrarRiel(estaci);
        Riel rrll1 = recoT.encontrarRiel(estaci1);
        mandarASalir(rrll,estaci,nn,panel);
        mandarASalir(rrll1,estaci1,nn1,panel);
        Frame frame = this;

       /* nn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(frame,"Estacion: "+estaci,
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
       */
    }
    public void mandarASalir(Riel rl,String nombreES,JLabel lavel,JPanel pannell) {
        Tren t = new Tren("adas",3,90);
        lavel.setBounds(250,350,50,50);

        pannell.add(lavel);

        t.setNombreEstacion(nombreES);
        t.setEtiquetaTren(lavel);
        t.setRl(rl);
        t.start();
    }

}
