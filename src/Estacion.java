import com.sun.deploy.util.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Estacion {

    public String dirEstacion;
    public int numTrenes;
    public int maxTrenes;
    public int posX;
    public int posY;
    public boolean operando;
    public Queue<Tren> parqueo = new PriorityQueue<>();
    public Estacion() {}

    public Estacion(String dirEstacion, int maxTrenes, int posX, int posY) {
        this.dirEstacion = dirEstacion;
        this.numTrenes= 0;
        this.operando = true;
        this.maxTrenes = maxTrenes;
        this.posX = posX;
        this.posY = posY;
    }

    public String toString() {
        String res;
        res = dirEstacion + "|" + numTrenes + "|" + operando + "|" + maxTrenes + "|" + posX + "|" + posY + "|" + toStringParqueo();
        return res;
    }
    public String toStringParqueo() {
        String res= "";
        if (!parqueo.isEmpty()) {
            for (Tren tr: parqueo) {
                res =res + tr.toString() + ",";
            }
            res = res.substring(0,res.length()-1);
        }
        return res;
    }
    public void almacenarTren(Tren tren) {
        parqueo.add(tren);
    }

    public void setParqueo(Queue<Tren> trenes) {
        this.parqueo = trenes;
    }
    public void setDirEstacion(String dirEstacion) {this.dirEstacion = dirEstacion; }
    public void setNumTrenes(int numTrenes) {
        this.numTrenes = numTrenes;
    }
    public void setMaxTrenes(int maxTrenes) {
        this.maxTrenes = maxTrenes;
    }
    public void setPosX(int posX) {
        this.posX = posX;
    }
    public void setPosY(int posY) {
        this.posY = posY;
    }
    public void setOperando(boolean operando) {
        this.operando = operando;
    }
    public String getDirEstacion() {
        return dirEstacion;
    }
    public int getPosX() { return posX;}
    public int getPosY() { return posY;}
    public Queue<Tren> getParqueo() {
        return parqueo;
    }
}
