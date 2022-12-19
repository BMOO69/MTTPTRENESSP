import javax.swing.*;

public class MandarASalir {

    public JFrame frame;
    public MandarASalir(JFrame frame){
        this.frame = frame;
    }
    public void mandarASalir(Riel rl,String nombreESA,String nombreESLle,JLabel lavel,JPanel pannell) {

        if (rl.getEstacionA().getDirEstacion().equals(nombreESA)){
            if (rl.getEstacionA().getParqueo().size()>=1){
                Tren tre ;
                if (rl.getEstacionA().getDirEstacion().equals(nombreESA)) {
                    tre = rl.getEstacionA().getParqueo().element();
                    if (tre.getColorSemaforo()== 1){
                        Tren tren = new Tren(tre.codTren,tre.numVagones,tre.numPasajeros,tre.colorSemaforo);
                        mandarASalrTrenHilo(tren,nombreESA,nombreESLle,lavel,pannell,rl);

                    }else if (tre.getColorSemaforo() == 2) {
                        Tren tren = new Tren(tre.codTren,tre.numVagones,tre.numPasajeros,tre.colorSemaforo);
                        mandarASalrTrenHilo(tren,nombreESA,nombreESLle,lavel,pannell,rl);
                    } else {
                        JOptionPane.showMessageDialog(null,"no hay trenes en condiciones de salida");
                    }
                }else {
                    tre = rl.getEstacionB().getParqueo().element();
                    if (tre.getColorSemaforo()== 1){
                        Tren tren = new Tren(tre.codTren,tre.numVagones,tre.numPasajeros,tre.colorSemaforo);
                        mandarASalrTrenHilo(tren,nombreESA,nombreESLle,lavel,pannell,rl);
                    }else if (tre.getColorSemaforo() == 2) {
                        Tren tren = new Tren(tre.codTren,tre.numVagones,tre.numPasajeros,tre.colorSemaforo);
                        mandarASalrTrenHilo(tren,nombreESA,nombreESLle,lavel,pannell,rl);
                    } else {
                        JOptionPane.showMessageDialog(null,"no hay trenes en condiciones de salida");
                    }
                }
            }else {
                JOptionPane.showMessageDialog(null,"No existe trenees en la estacion"+rl.getEstacionA().getDirEstacion());
            }
        }else if (rl.getEstacionB().getDirEstacion().equals(nombreESA)) {
            if (rl.getEstacionB().getParqueo().size() >= 1){
                Tren tre ;
                if (rl.getEstacionB().getDirEstacion().equals(nombreESA)) {
                    tre = rl.getEstacionB().getParqueo().element();
                    if (tre.getColorSemaforo()== 1){
                        Tren tren = new Tren(tre.codTren,tre.numVagones,tre.numPasajeros,tre.colorSemaforo);
                        mandarASalrTrenHilo(tren,nombreESA,nombreESLle,lavel,pannell,rl);
                    }else if (tre.getColorSemaforo() == 2) {
                        Tren tren = new Tren(tre.codTren,tre.numVagones,tre.numPasajeros,tre.colorSemaforo);
                        mandarASalrTrenHilo(tren,nombreESA,nombreESLle,lavel,pannell,rl);
                    } else {
                        JOptionPane.showMessageDialog(null,"no hay trenes en condiciones de salida");
                    }
                }else {
                    tre = rl.getEstacionB().getParqueo().element();
                    if (tre.getColorSemaforo()== 1){
                        Tren tren = new Tren(tre.codTren,tre.numVagones,tre.numPasajeros,tre.colorSemaforo);
                        mandarASalrTrenHilo(tren,nombreESA,nombreESLle,lavel,pannell,rl);
                    }else if (tre.getColorSemaforo() == 2) {
                        Tren tren = new Tren(tre.codTren,tre.numVagones,tre.numPasajeros,tre.colorSemaforo);
                        mandarASalrTrenHilo(tren,nombreESA,nombreESLle,lavel,pannell,rl);
                    } else {
                        JOptionPane.showMessageDialog(null,"no hay trenes en condiciones de salida");
                    }
                }
            }else {
                JOptionPane.showMessageDialog(null,"No existe trenees en la estacion"+rl.getEstacionB().getDirEstacion());
            }
        }
    }

    private void mandarASalrTrenHilo(Tren tren,String nombreESA,String nombreESLle,JLabel lavel,JPanel pannell,Riel rl) {
        pannell.add(lavel);
        tren.setNombreEstacionSalida(nombreESA);
        tren.setNombreEstacionLLegada(nombreESLle);
        tren.setEtiquetaTren(lavel);
        tren.setRl(rl);
        tren.start();
    }

}
