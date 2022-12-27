import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.EngineModeDesc;
import javax.speech.recognition.*;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Micro extends ResultAdapter {
    public static Recognizer recognizer;
    private static final String dirPath = "Gramatica.txt";
    private boolean desconectado;
    public String gst;
    public String cad;
    public String cadena;
    private ArrayList<String[]> comandos;
    public String[] array;
    public RecorrerTren recott;
    public Mapa m;
    String textEstacion;
    public Micro(){
        recott = new RecorrerTren("Rieles.csv");
        this.desconectado = true;
        cad = "";
        gst = "";
    }
    public void iniciar() {
        m = new Mapa();
        m.setVisible(true);
        try {
            recognizer = Central.createRecognizer(new EngineModeDesc(Locale.ROOT));
            recognizer.allocate();
            FileReader grammar1 = new FileReader(dirPath);

            RuleGrammar rg = recognizer.loadJSGF(grammar1);
            rg.setEnabled(true);

            recognizer.addResultListener(this);

            recognizer.commitChanges();

            recognizer.requestFocus();
            recognizer.resume();
        } catch (Exception e) {
            System.out.println("ha ocurrido algo inesperao micro: "+e.toString());
        }
    }

    @Override
    public void resultAccepted(ResultEvent re) {
        String aux = "";
        comandos = archivoComandos("FileTrenes.txt");
        try {
            Result res = (Result) (re.getSource());
            ResultToken tokens[] = res.getBestTokens();
            String args[] = new String[1];
            args[0] = "";
            for (int i = 0; i< tokens.length; i++) {
                aux  = tokens[i].getSpokenText();
                gst = tokens[i].getSpokenText();
                args[0] += gst+" ";
            }
            cad = args[0].substring(0,args[0].length()-1);
            cadena = numComando(cad);
            if (cadena.equals(cad)) {
                recognizer.suspend();
                String[] varr = cad.split(" ");
                System.out.println("funciona funciona");
                m.mandarASalirMicro(varr[2],varr[3]);
                array = varr;
                recognizer.resume();
            }
        }catch (Exception e) {
            System.out.println("el evento resultAcepted");
        }

    }
    private ArrayList<String[]> archivoComandos(String dir){
        ArrayList<String[]> res = new ArrayList<>();
        try {
            File myObj = new File(dir);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] var = data.split("-");
                res.add(var);
            }
        }catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return res;
    }
    private String numComando(String var) {
        String res = "";
        for (String[] i: comandos) {
            if (i[1].equals(var)){
                res = i[1];
            }
        }
        return res;
    }
    public void apagar() {
        try {
            if (desconectado) {
                recognizer.pause();
                System.out.println("apagado micro");
            }
        }catch (Exception e) {
            System.out.println("problema al apagar el micro: "+e.toString());
        }
    }
    public void prender() {
        try {
            if (!desconectado) {
                iniciar();
                System.out.println("encendio microfono");
            }else {
                recognizer.resume();
            }
        } catch (Exception e) {
            System.out.println("problema en el encendido del microfono: "+e.toString());
        }
    }

    public String[] getArray() {
        return array;
    }

    public void setArray(String[] array) {
        this.array = array;
    }
}
