import sun.security.jgss.GSSHeader;

import javax.speech.AudioException;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Mapa extends JFrame implements ChangeListener {
    
    CSVManager csv = CSVManager.getInstance();

    ArrayList<String[]> ccssvv;
    ArrayList<Riel> rieel;
    ArrayList<Tren> treenes;
    ArrayList<Estacion> stations;
    JCheckBox jCheckBox;
    JPanel panel;
    Microfono micro;
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
        micro = new Microfono();
        //micro.inicializarMicro();
        RecorrerTren recoT = new RecorrerTren();
        jCheckBox = new JCheckBox("Microfono");
        jCheckBox.setBounds(10,10,100,20);
        jCheckBox.addChangeListener(this);
        Frame frame = this;
        panel = new JPanel() {
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
        panel.add(jCheckBox);

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
                ratonEstacion(etiA,frame,i.getEstacionA());


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
        String estaci = "Quillacollo";
        String estaci1 = "Tarata";

        JLabel nn = new JLabel();
        JLabel nn1 = new JLabel();

        //String prueba = micro.getPalabra();


        nn.setIcon(icono);
        nn1.setIcon(icono);
        recoT.cargarCSV("Rieles.csv");
        Riel rrll=recoT.encontrarRiel(estaci,estaci1);
        //Riel rrll1 = recoT.encontrarRiel(estaci,estaci1);
        //System.out.println(rrll1.toString());
        mandarASalir(rrll,estaci,estaci1,nn,panel);
      //mandarASalir(rrll1,estaci1,nn1,panel);
        /*
        if (prueba.equals("Cercado")) {
            mandarASalir(rrll,estaci,nn,panel);
        }
        if (prueba.equals("Tarata")) {
            mandarASalir(rrll1,estaci1,nn1,panel);
        }*/
        //
        //


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
    public void mandarASalir(Riel rl,String nombreESA,String nombreESLle,JLabel lavel,JPanel pannell) {
        Tren t = new Tren("adas",3,90);
        lavel.setBounds(250,350,50,50);

        pannell.add(lavel);

        t.setNombreEstacionSalida(nombreESA);
        t.setNombreEstacionLLegada(nombreESLle);
        t.setEtiquetaTren(lavel);
        t.setRl(rl);
        t.start();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (jCheckBox.isSelected() == true) {
            /*try {
                micro.encerderMicrofono();
            } catch (AudioException ex) {
                throw new RuntimeException(ex);
            }
            */
        }
        else {
            micro.apagarMicrofono();
        }
    }
    public void ratonEstacion(JLabel labEstacion,Frame frame,Estacion estacion){
        labEstacion.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(frame,"Nombre de la estacion: "+estacion.getDirEstacion(),"Prueba",JOptionPane.INFORMATION_MESSAGE);
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
}
