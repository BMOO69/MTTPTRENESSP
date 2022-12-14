import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AgregarTren extends JFrame {

    CSVManager csv = CSVManager.getInstance();
    private static final String path = "Rieles.csv";
    ArrayList<String[]>  ccssvv;
    ArrayList<Riel> riel;
    JPanel panel;
    JButton agregar;
    JFrame frame;
    JLabel labelCodTren;
    JTextField codTren;
    JLabel labelNumVag;
    JTextField numVag;
    JLabel labelNumPas;
    JTextField numPas;
    JLabel labelEstacion;
    JTextField textEstacion;
    public AgregarTren(){
        ccssvv = csv.read(path);
        riel = csv.rielesAlmacen(ccssvv);
        setSize(400,500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        //setLayout(null);
        setTitle("agregar Tren");
        frame = this;
        initComponet();
    }
    private void initComponet(){
        panel = new JPanel();
        panel.setLayout(null);
        this.getContentPane().add(panel);

        labelCodTren = new JLabel("ingrese el codigo del tren");
        labelCodTren.setBounds(30,40,200,30);
        panel.add(labelCodTren);

        codTren = new JTextField();
        codTren.setBounds(30,75,200,30);
        panel.add(codTren);

        labelNumVag = new JLabel("ingrese la cantidad de vagones");
        labelNumVag.setBounds(30,105,200,30);
        panel.add(labelNumVag);

        numVag = new JTextField();
        numVag.setBounds(30,140,200,30);
        panel.add(numVag);

        labelNumPas = new JLabel("ingrese la cantidad de pasajeros");
        labelNumPas.setBounds(30,175,200,30);
        panel.add(labelNumPas);

        numPas = new JTextField();
        numPas.setBounds(30,210,200,30);
        panel.add(numPas);

        labelEstacion = new JLabel("ingrese la estacion a ingresar");
        labelEstacion.setBounds(30,245,200,30);
        panel.add(labelEstacion);

        textEstacion = new JTextField();
        textEstacion.setBounds(30,280,200,30);
        panel.add(textEstacion);

        agregar = new JButton("AGREGAR!!");
        agregar.setBounds(130,330,100,35);
        panel.add(agregar);

        agregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int var =0;
                RecorrerTren recoT = new RecorrerTren(path);
                String res = textEstacion.getText();
                for (int i = 0 ; i< riel.size();i++) {
                    if (riel.get(i).getEstacionA().getDirEstacion().equals(textEstacion.getText())) {
                        Tren tr = new Tren(codTren.getText(),Integer.parseInt(numVag.getText()),Integer.parseInt(numPas.getText()),1);
                        Estacion estA = riel.get(i).getEstacionA();
                        Estacion estB = riel.get(i).getEstacionB();
                        estA.getParqueo().add(tr);
                        Riel rl = new Riel(riel.get(i).getNumRiel(),estA,estB);
                        var = 1;
                        csv.overWrite(riel.get(i).getNumRiel(),path,rl.toString());
                    } else if (riel.get(i).getEstacionB().getDirEstacion().equals(textEstacion.getText())) {
                        Tren tr1 = new Tren(codTren.getText(),Integer.parseInt(numVag.getText()),Integer.parseInt(numPas.getText()),1);
                        Estacion estA1 = riel.get(i).getEstacionA();
                        Estacion estB1 = riel.get(i).getEstacionB();
                        estB1.getParqueo().add(tr1);
                        Riel rl1 = new Riel(riel.get(i).getNumRiel(),estA1,estB1);
                        csv.overWrite(riel.get(i).getNumRiel(),path, rl1.toString());
                        var = 1;
                    }
                }
                if (var == 1) {
                    JOptionPane.showMessageDialog(null,"ingreso el tren");
                    frame.setVisible(false);
                    frame.dispose();
                }else {
                    JOptionPane.showMessageDialog(null,"ingrese valores Correctos");
                }
            }
        });

    }
    private void cerrarVentana(){
       // frame.setDefaultCloseOperation();
    }
}
