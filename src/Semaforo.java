import sun.security.mscapi.CPublicKey;

import javax.swing.*;
import java.awt.*;

public class Semaforo {

    public static final String rojoI = "imagenes/Rojo.jpg";
    public static final String amarilloI = "imagenes/Amarillo.jpg";
    public static final String verdeI ="imagenes/Verde.jpg";

    public Semaforo(){
    }

    public ImageIcon funcionarSemaforo(int colorMath){

        if (colorMath == 1) { // color verde
            ImageIcon semaVerde = new ImageIcon(verdeI);
            Icon iconVerde = new ImageIcon(semaVerde.getImage().getScaledInstance(50,100,Image.SCALE_SMOOTH));
            return (ImageIcon) iconVerde;
        }else if (colorMath == 2) { // color amarillo
            ImageIcon semaAmar = new ImageIcon(amarilloI);
            Icon iconAmar = new ImageIcon(semaAmar.getImage().getScaledInstance(50,100,Image.SCALE_SMOOTH));
            return (ImageIcon) iconAmar;
        }else {                     // color rojo
            ImageIcon semaRojo = new ImageIcon(rojoI);
            Icon iconRojo = new ImageIcon(semaRojo.getImage().getScaledInstance(50,100,Image.SCALE_SMOOTH));
            return (ImageIcon) iconRojo;
        }

    }

    public int cambioSemaforo(int color){
        int res;
        if (color ==1 ) {
            int var = (int )(Math.random()*100);
            if (var <= 70) {
                res = 1;
            }else {
                if (var <= 85) {
                    res = 2;
                }else {
                    res = 3;
                }
            }
        }else {
            int var1 = (int) (Math.random()*(100-75+1)+75);
            if (var1 <= 75){
                res = 2;
            }else {
                res = 3;
            }
        }
        return  res;
    }

}
