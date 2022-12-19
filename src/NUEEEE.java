import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NUEEEE extends JFrame implements ActionListener {
    JRadioButton jf;
    JPanel panel;
    JButton b,c;
    JRadioButton bn;
    public NUEEEE(){
        setSize(500,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf = new JRadioButton("Microfono");
        jf.setBounds(40,40,100,40);
        b =new JButton("Micorofno");
        b.setBounds(50,50,70,70);
        c = new JButton("adasd");
        c.setBounds(100,100,50,500);
        panel = new JPanel();
        this.getContentPane().add(panel);
        panel.add(b);
        panel.add(jf);
        panel.add(c);
        bn = new JRadioButton("adasdawwwwwwww");
        bn.setBounds(200,200,50,50);
        panel.add(bn);
        bn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (bn.isSelected()) {
                    panel.setBackground(Color.MAGENTA);
                }
                if (!bn.isSelected()) {
                    panel.setBackground(Color.YELLOW);
                }
            }
        });
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    panel.setBackground(Color.green);


            }
        });
        c.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setBackground(Color.BLACK);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (jf.isSelected()){
            System.out.println("pruebame");
            panel.setBackground(Color.BLUE);
        }else {
            System.out.println("-------------------------------------------------------");
        }
        if (b.isSelected()){

        }
    }
}
