import javax.swing.*;
import java.awt.*;

public class Semaforo extends JLabel {

    public static final String rojoI = "Rojo.jpg";
    public static final String AmarilloI = "Amarillo.jpg";
    public static final String verdeI ="Verde.jpg";
    public int verde;
    public int rojo;

    int x,y;
    public Riel riel;
    public boolean puede;

    public Semaforo(int x,int y){
        //this.riel = rl;
        this.x = x;
        this.y = y;
    }

    public void funcionarSemaforo(){
        this.setBounds(x,y,50,50);
        this.setText("preuba 1");

    }


}
