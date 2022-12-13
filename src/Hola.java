import javax.swing.*;
import java.awt.*;

public class Hola extends JFrame {
    int x,y;
    public Hola() {
        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        iniciar();
    }
    public void iniciar(){


        JPanel panel = new JPanel();
        panel.setBackground(Color.green);
        panel.setLayout(null);
        this.getContentPane().add(panel);

        prueba pr = new prueba();
        Thread thread =new Thread(pr);

        JLabel eti = new JLabel();
        eti.setBounds(pr.getX(),pr.getY(),pr.getX()+20,pr.getY()+20);
        eti.setBackground(Color.cyan);
        eti.setText("hola mundo");
        pr.setEtiqueta(eti);
        thread.start();
        panel.add(eti);

/*
        JLabel n = new JLabel();
        n.setBounds(10,10,20,20);
        n.setText("asdadad");
        panel.add(n);
*/


    }
}
