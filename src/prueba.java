import jdk.management.resource.internal.inst.FileOutputStreamRMHooks;

import javax.swing.*;

public class prueba implements Runnable{
    public int x =0;
    public int y =0;
    JLabel etiqueta;
    @Override
    public void run() {
        System.out.println("1");

            for (int i = 0;i <= 200;i=i+20) {

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                x = x + 10;
                y = y + 10;
                etiqueta.setBounds(x, y, x + 20, y + 20);
                System.out.println(x + "   " + y);
            }
        System.out.println("2");

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public JLabel getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(JLabel etiqueta) {
        this.etiqueta = etiqueta;
    }
}
