import java.awt.*;
import java.util.ArrayList;

public class Riel {

    public int numRiel;
    public Estacion estacionA;
    public Estacion estacionB;
    public int x1,y1,x2,y2;

    public ArrayList<Tren> trenEnRiel;

    public Riel(int numRiel, Estacion estacionA, Estacion estacionB) {
        this.numRiel = numRiel;
        this.estacionA = estacionA;
        this.estacionB = estacionB;
        this.x1 = estacionA.getPosX();
        this.y1 = estacionA.getPosY();
        this.x2 = estacionB.getPosX();
        this.y2 = estacionB.getPosY();
    }
    public Riel(int nim){
        this.numRiel = nim;
    }

    public String toString() {

        String res = numRiel + "-" + estacionA.toString() + "-" + estacionB.toString() + "\n";
        return  res;
    }
    public void dibujaRiel(Graphics g) {
        g.drawLine(x1,y1,x2,y2);
    }

    public int getX1() {
        return x1;
    }
    public int getY1() {
        return y1;
    }
    public int getX2() {
        return x2;
    }
    public int getY2() {
        return y2;
    }

    public int getNumRiel() {
        return numRiel;
    }

    public void setNumRiel(int numRiel) {
        this.numRiel = numRiel;
    }

    public Estacion getEstacionA() {
        return estacionA;
    }

    public void setEstacionA(Estacion estacionA) {
        this.estacionA = estacionA;
    }

    public Estacion getEstacionB() {
        return estacionB;
    }

    public void setEstacionB(Estacion estacionB) {
        this.estacionB = estacionB;
    }
}
