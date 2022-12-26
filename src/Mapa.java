import sun.security.jgss.GSSHeader;

import javax.smartcardio.ATR;
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

public class Mapa extends JFrame implements ActionListener {
    
    CSVManager csv = CSVManager.getInstance();
    static final String path = "Rieles.csv";
    ArrayList<String[]> ccssvv;
    ArrayList<Riel> rieel;
    //JCheckBox jCheckBox;
    JPanel panel;
    Microfono micro;
    JTextField cajaEsSalida;
    JTextField cajaEsLlegada;
    JButton JBtn_SalirTren;
    String salida="";
    String llegada="";
    ImageIcon im;
    Icon icono;
    Frame frame;
    RecorrerTren recoT;
    AgregarTren agr;
    Micro mr;
    Microfono microfono;
    JRadioButton radioB;
    String textEstacion;
    JButton esta;
    public Mapa() {
        ccssvv = csv.read(path);
        rieel = csv.rielesAlmacen(ccssvv);
        setSize(1000,800);
        setLocation(100,100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Control de operaciones de trenes");
        frame = this;
        ImageIcon im = new ImageIcon("imagenes/trenn.jpg");
        icono = new ImageIcon(im.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH));
        agr = new AgregarTren();
        mr = new Micro();
        microfono = new Microfono();
        //this.getContentPane().setBackground(Color.green);

        iniciarComponentes();
    }

    private void iniciarComponentes() {
        micro = new Microfono();
        //micro.inicializarMicro();
        recoT = new RecorrerTren(path);
        panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);

                Graphics2D g2 =(Graphics2D) g;
                g2.setStroke(new BasicStroke(10));
                if(!rieel.isEmpty()) {
                    for (Riel riel : rieel) {
                        Estacion estacionA = riel.getEstacionA();
                        Estacion estacionB = riel.getEstacionB();

                        g.drawLine(estacionA.getPosX(), estacionA.getPosY(), estacionB.getPosX(), estacionB.getPosY());
                    }
                }
            }
        };
        colocarCajasDeTexto();
        colocarElementosDeIngresoTren();

        Tren.setMap(panel);
        panel.setBackground(Color.GRAY);
        panel.setLayout(null);
        this.getContentPane().add(panel);

        ImageIcon imagen = new ImageIcon("imagenes/trainStation.png");
        if (!rieel.isEmpty()){
            for (Riel i : rieel) {

                JLabel etiA = new JLabel();
                etiA.setBounds(i.getEstacionA().getPosX()-50,i.getEstacionA().getPosY()-50,100,100);
                etiA.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH)));
                JLabel nombreEtiA = new JLabel(i.getEstacionA().getDirEstacion());
                nombreEtiA.setBounds(i.getEstacionA().getPosX()-50,i.getEstacionA().getPosY()-60,100,10);
                panel.add(nombreEtiA);
                panel.add(etiA);
                ratonEstacion(etiA,frame,i.getEstacionA().getDirEstacion());

                JLabel etiB = new JLabel();

                etiB.setBounds(i.getEstacionB().getPosX()-50,i.getEstacionB().getPosY()-50,100,100);
                etiB.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH)));
                JLabel nombreEtiB = new JLabel(i.getEstacionB().getDirEstacion());
                nombreEtiB.setBounds(i.getEstacionB().getPosX()-50,i.getEstacionB().getPosY()-60,100,10);
                panel.add(nombreEtiB);
                panel.add(etiB);
                ratonEstacion(etiB,frame,i.getEstacionB().getDirEstacion());
            }
        }
    }
    public void mandarASalirMicro(String nameA,String nameB) {
        System.out.println(salida+ "   "+llegada);
        Riel rl = recoT.encontrarRiel(nameA,nameB);
        if  (rl.getNumRiel() != 0) {
            JLabel lavel = new JLabel();
            lavel.setIcon(icono);
            mandarASalir(rl,salida,llegada,lavel,panel);
        }
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
    private void colocarCajasDeTexto() {
        JLabel letSa = new JLabel("Salida!!");
        letSa.setBounds(200,20,100,20);
        panel.add(letSa);
        cajaEsSalida = new JTextField("SALIDA");
        cajaEsSalida.setBounds(200,40,100,30);
        panel.add(cajaEsSalida);
        JLabel letLle = new JLabel("Llegada!!");
        letLle.setBounds(325,20,100,20);
        panel.add(letLle);
        cajaEsLlegada = new JTextField("LLEGADA");
        cajaEsLlegada.setBounds(325,40,100,30);
        panel.add(cajaEsLlegada);
        JBtn_SalirTren = new JButton("SALIR!!!");
        JBtn_SalirTren.setBounds(450,40,100,30);
        panel.add(JBtn_SalirTren);
        //panel.add(jCheckBox);
        radioB =new JRadioButton("Microfono");
        radioB.setBounds(0,100,60,30);
        panel.add(radioB);
        radioB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (radioB.isSelected()){
                    mr.iniciar();
                }
                if (!radioB.isSelected()) {
                    mr.apagar();
                }
            }
        });
        JBtn_SalirTren.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salida = cajaEsSalida.getText();
                llegada = cajaEsLlegada.getText();
                System.out.println(salida+ "   "+llegada);

                Riel rl = recoT.encontrarRiel(salida,llegada);
                if  (rl.getNumRiel() != 0) {
                    JLabel lavel = new JLabel();
                    lavel.setIcon(icono);
                    mandarASalir(rl,salida,llegada,lavel,panel);
                }
            }
        });
        esta = new JButton("Estadistica");
        esta.setBounds(450,10,100,30);
        panel.add(esta);
        esta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("preciono boton estadistica");
            }
        });
    }
    private void colocarElementosDeIngresoTren(){
        JButton agregar = new JButton("Agregar Tren");
        agregar.setBounds(10,10,150,30);
        panel.add(agregar);
        agregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agr.setVisible(true);
            }
        });
    }
    /*@Override
    /*public void stateChanged(ChangeEvent e) {
        if (radioB.isSelected() == true) {
            try {
                microfono.inicializarMicro();
                //mr.iniciar();
            } catch (Exception ex) {
                System.out.println("error al encender el microfono frame: "+ex.toString());
            }
        }
        else {
            mr.apagar();
        }
    }*/

    public void ratonEstacion(JLabel labEstacion,Frame frame,String estacion){
        labEstacion.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Estacion est = new Estacion();
                ArrayList<String[]> al = csv.read(path);
                ArrayList<Riel> alT = csv.rielesAlmacen(al);
                for (Riel i:alT){
                    if (i.getEstacionA().getDirEstacion().equals(estacion)) {
                        est = i.getEstacionA();
                        break;
                    }else if (i.getEstacionB().getDirEstacion().equals(estacion)) {
                        est = i.getEstacionB();
                        break;
                    }
                }
                int rojo= 0;
                int amarillo = 0;
                int verde = 0;
                ArrayList<Tren> array = new ArrayList<>(est.getParqueo());
                for(int i = 0;i<array.size();i++) {
                    if(array.get(i).getColorSemaforo() ==1) {
                        verde = verde+1;
                    } else if (array.get(i).getColorSemaforo() == 2) {
                        amarillo = amarillo+1;
                    }else {
                        rojo = rojo+1;
                    }
                }
                JOptionPane.showMessageDialog(frame,"Nombre de la estacion: "+estacion+"\n"+
                                                            "Trenes en alamcen: "+est.getParqueo().size()+"\n"+
                                                            "Numero de trenes Rojos: "+rojo+"\n"+
                                                            "Numero de trenes Amarillos: "+amarillo+"\n"+
                                                            "Numero de trenes Verder: "+verde+"\n"
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (radioB.isSelected()){
            JOptionPane.showMessageDialog(this,"asdasdads");
        }
        if (!radioB.isSelected()) {
            System.out.println("me lapeas");
        }
    }
}
