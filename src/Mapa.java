import sun.security.jgss.GSSHeader;

import javax.speech.AudioException;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Mapa extends JFrame implements ChangeListener {
    
    CSVManager csv = CSVManager.getInstance();
    static final String path = "Rieles.csv";
    ArrayList<String[]> ccssvv;
    ArrayList<Riel> rieel;
    JCheckBox jCheckBox;
    JPanel panel;
    Microfono micro;
    JTextField cajaEsSalida;
    JTextField cajaEsLlegada;
    JButton JBtn_SalirTren;
    String salida="";
    String llegada="";
    public Mapa() {
        ccssvv = csv.read(path);
        rieel = csv.rielesAlmacen(ccssvv);
        setSize(1000,800);
        setLocation(100,100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Control de operaciones de trenes");
        //this.getContentPane().setBackground(Color.green);
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        micro = new Microfono();
        //micro.inicializarMicro();
        RecorrerTren recoT = new RecorrerTren();

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

        colocarCajasDeTexto();


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
                ratonEstacion(etiB,frame,i.getEstacionB());
            }
        }
        ImageIcon imagenOK = new ImageIcon("check.png");
        ImageIcon im = new ImageIcon("trenn.jpg");
        Tren.imagenOK= imagenOK;
        Icon icono = new ImageIcon(im.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH));
        String estaci = "Sacaba";
        String estaci1 = "Quillacollo";

        JLabel nn = new JLabel();
        JLabel nn1 = new JLabel();

        nn.setIcon(icono);
        nn1.setIcon(icono);
        recoT.cargarCSV("Rieles.csv");
        Riel rrll=recoT.encontrarRiel(estaci,estaci1);
        Riel rrll1 = recoT.encontrarRiel(estaci1,estaci);
        //System.out.println(rrll1.toString());
        //mandarASalir(rrll,estaci,estaci1,nn,panel);
        //mandarASalir(rrll1,estaci1,estaci,nn1,panel);


    }

    private void colocarCajasDeTexto() {
        
        cajaEsSalida = new JTextField();
        cajaEsSalida.setBounds(200,40,100,30);
        panel.add(cajaEsSalida);
        cajaEsLlegada = new JTextField();
        cajaEsLlegada.setBounds(325,40,100,30);
        panel.add(cajaEsLlegada);
        JBtn_SalirTren = new JButton("SALIR!!!");
        JBtn_SalirTren.setBounds(450,40,100,30);
        panel.add(JBtn_SalirTren);
        JBtn_SalirTren.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //panel.setBackground(Color.BLACK);
                salida = cajaEsSalida.getText();
                llegada = cajaEsLlegada.getText();
                System.out.println(salida+ "   "+llegada);
            }
        });

    }
    public void mandarASalir(Riel rl,String nombreESA,String nombreESLle,JLabel lavel,JPanel pannell) {

        if (rl.getEstacionA().getDirEstacion().equals(nombreESA)){
            if (rl.getEstacionA().getParqueo().size()>=1){
                Tren tre ;
                if (rl.getEstacionA().getDirEstacion().equals(nombreESA)) {
                    tre = rl.getEstacionA().getParqueo().element();
                    if (tre.getColorSemaforo()== 1){
                        Tren tren = new Tren(tre.codTren,tre.numVagones,tre.numPasajeros,tre.colorSemaforo);
                        mandarASalrTrenHilo(tren,nombreESA,nombreESLle,lavel,pannell,rl);

                    }else if (tre.getColorSemaforo() == 2) {
                        Tren tren = new Tren(tre.codTren,tre.numVagones,tre.numPasajeros,tre.colorSemaforo);
                        mandarASalrTrenHilo(tren,nombreESA,nombreESLle,lavel,pannell,rl);
                    } else {
                        JOptionPane.showMessageDialog(null,"no hay trenes en condiciones de salida");
                    }
                }else {
                    tre = rl.getEstacionB().getParqueo().element();
                    if (tre.getColorSemaforo()== 1){
                        Tren tren = new Tren(tre.codTren,tre.numVagones,tre.numPasajeros,tre.colorSemaforo);
                        mandarASalrTrenHilo(tren,nombreESA,nombreESLle,lavel,pannell,rl);
                    }else if (tre.getColorSemaforo() == 2) {
                        Tren tren = new Tren(tre.codTren,tre.numVagones,tre.numPasajeros,tre.colorSemaforo);
                        mandarASalrTrenHilo(tren,nombreESA,nombreESLle,lavel,pannell,rl);
                    } else {
                        JOptionPane.showMessageDialog(null,"no hay trenes en condiciones de salida");
                    }

                }

            }else {
                JOptionPane.showMessageDialog(null,"No existe trenees en la estacion"+rl.getEstacionA().getDirEstacion());
            }
        }else if (rl.getEstacionB().getDirEstacion().equals(nombreESA)) {
            if (rl.getEstacionB().getParqueo().size() >= 1){
                Tren tre ;
                if (rl.getEstacionB().getDirEstacion().equals(nombreESA)) {
                    tre = rl.getEstacionB().getParqueo().element();
                    if (tre.getColorSemaforo()== 1){
                        Tren tren = new Tren(tre.codTren,tre.numVagones,tre.numPasajeros,tre.colorSemaforo);
                        mandarASalrTrenHilo(tren,nombreESA,nombreESLle,lavel,pannell,rl);
                    }else if (tre.getColorSemaforo() == 2) {
                        Tren tren = new Tren(tre.codTren,tre.numVagones,tre.numPasajeros,tre.colorSemaforo);
                        mandarASalrTrenHilo(tren,nombreESA,nombreESLle,lavel,pannell,rl);
                    } else {
                        JOptionPane.showMessageDialog(null,"no hay trenes en condiciones de salida");
                    }
                }else {
                    tre = rl.getEstacionB().getParqueo().element();
                    if (tre.getColorSemaforo()== 1){
                        Tren tren = new Tren(tre.codTren,tre.numVagones,tre.numPasajeros,tre.colorSemaforo);
                        mandarASalrTrenHilo(tren,nombreESA,nombreESLle,lavel,pannell,rl);
                    }else if (tre.getColorSemaforo() == 2) {
                        Tren tren = new Tren(tre.codTren,tre.numVagones,tre.numPasajeros,tre.colorSemaforo);
                        mandarASalrTrenHilo(tren,nombreESA,nombreESLle,lavel,pannell,rl);

                    } else {
                        JOptionPane.showMessageDialog(null,"no hay trenes en condiciones de salida");
                    }
                }
            }else {
                JOptionPane.showMessageDialog(null,"No existe trenees en la estacion"+rl.getEstacionB().getDirEstacion());
            }
        }
    }

    private void mandarASalrTrenHilo(Tren tren,String nombreESA,String nombreESLle,JLabel lavel,JPanel pannell,Riel rl) {
        pannell.add(lavel);
        tren.setNombreEstacionSalida(nombreESA);
        tren.setNombreEstacionLLegada(nombreESLle);
        tren.setEtiquetaTren(lavel);
        tren.setRl(rl);
        tren.start();
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
                JOptionPane.showMessageDialog(frame,"Nombre de la estacion: "+estacion.getDirEstacion()+"\n"+"Trenes en alamcen: "+estacion.getParqueo().size()

                        ,"Prueba",JOptionPane.INFORMATION_MESSAGE);
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
