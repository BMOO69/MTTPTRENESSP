import com.sun.deploy.cache.CacheEntry;
import sun.rmi.transport.DGCImpl_Stub;

import javax.speech.AudioException;
import javax.speech.Central;
import javax.speech.EngineModeDesc;
import javax.speech.recognition.*;
import java.io.FileReader;
import java.util.Locale;

public class Microfono extends ResultAdapter {

    static Recognizer recognizer;
    String gst;
    String palabra;
    @Override
    public void resultAccepted(ResultEvent re) {
        try {
            Result res = (Result) (re.getSource());
            ResultToken tokens[] = res.getBestTokens();
            String args[] = new String[1];
            args[0] ="";
            for (int i=0;i<tokens.length;i++) {
                gst =tokens[i].getSpokenText();
                args[0] +=gst+" ";
                palabra = gst;
                System.out.println(gst+" ");
            }
            System.out.println();
            if (gst.equals("Terminar")) {
                recognizer.deallocate();
                args[0]="Hasta la proxima Cmop!!!";
                System.out.println(args[0]);
                System.exit(0);
            }else {
                recognizer.suspend();
                recognizer.resume();
            }
        } catch (Exception e) {
            System.out.println("Ha ocurrido algo inesperado en el microfono");
        }
    }
    public void inicializarMicro() {
        try {
            recognizer = Central.createRecognizer(new EngineModeDesc(Locale.ROOT));
            recognizer.allocate();
            FileReader grammar1 = new FileReader("Gramatica.txt");
            RuleGrammar rg = recognizer.loadJSGF(grammar1);
            rg.setEnabled(true);

            recognizer.addResultListener(new Microfono());
            System.out.println("empieza el prgrama");
            recognizer.commitChanges();

            recognizer.requestFocus();
            recognizer.resume();
        }catch (Exception e){
            System.out.println("Exception en "+e.toString());
            e.printStackTrace();
            System.exit(0);
        }
    }
    public void encerderMicrofono() throws AudioException {
        recognizer.resume();
    }
    public  void apagarMicrofono() {
        recognizer.suspend();
    }


    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }
}
