import java.awt.*;
public class Riel {
    public int numRiel;
    public Estacion estacionA;
    public Estacion estacionB;
    public int x1,y1,x2,y2;

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

    public int getNumRiel() {
        return numRiel;
    }
    public Estacion getEstacionA() {
        return estacionA;
    }
    public Estacion getEstacionB() {
        return estacionB;
    }
}
