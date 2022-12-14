import com.sun.deploy.ui.AboutDialog;

import java.util.*;

public class main {

    public static void main(String[] args) {

       //Hola ho = new Hola();
      // ho.setVisible(true);

        Mapa m1 = new Mapa();
        m1.setVisible(true);

 /*
        Queue<String> cola = new LinkedList<>();
        cola.add("perro");
        cola.add("grato");
        cola.add("ggg");
        String res = cola.toString();
        System.out.println(res);
/*
        Tren b1 = new Tren("Primer1B",4,45);
        Tren b3 = new Tren("Segundo1B",3,34);
        Tren v3 = new Tren("Tercer1B",6,12);
        Queue<Tren> par = new LinkedList<>();
        par.add(b1);
        par.add(b3);
        par.add(v3);
        Estacion es1 = new Estacion("Sacaba",7,80,100);
        par.poll();
        es1.setParqueo(par);

        System.out.println(es1.toString());


        CSVManager csv = CSVManager.getInstance();






        Estacion es1 = new Estacion("Sacaba",7,80,100);
        Tren t = new Tren("PrimerTren1A", 9,54);
        Tren t2 = new Tren("SegundoTren2A", 5,90);
        Tren t3 = new Tren("TercerTren3A",8,45);
        es1.almacenarTren(t);
        es1.almacenarTren(t2);
        es1.almacenarTren(t3);
        Estacion es2 = new Estacion("Quillacollo",5,200,400);
        Tren b1 = new Tren("Primer1B",4,45);
        Tren b3 = new Tren("Segundo1B",3,34);
        Tren v3 = new Tren("Tercer1B",6,12);
        es2.almacenarTren(b1);
        es2.almacenarTren(b3);
        es2.almacenarTren(v3);

        Riel r1 = new Riel(2,es1,es2);
        csv.write("Rieles.csv",r1.toString());
*\
/*
        Queue<Tren> bg = new ArrayDeque<Tren>();
        String pru = "PrimerTren1A#9#54,SegundoTren2A#5#90,TercerTren3A#8#45";
        String[] re = pru.split(",");
        for (int i =0;i< re.length;i++) {
            String[] var = re[i].split("#");
            String codT = var[0];
            int numV = Integer.parseInt(var[1]);
            int nump = Integer.parseInt(var[2]);
            Tren nuevo = new Tren(codT,numV,nump);
            bg.add(nuevo);
        }*/

    }
}
