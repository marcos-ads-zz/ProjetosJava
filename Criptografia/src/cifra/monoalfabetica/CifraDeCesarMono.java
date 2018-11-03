package cifra.monoalfabetica;

import dao.tabelaDAO;
import java.text.Normalizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcos Júnior <marcosneri@outlook.com.br>
 */
public class CifraDeCesarMono {

    public int cifraLetra(int letra) {

        int ASCII = 0;
        tabelaDAO t = new tabelaDAO();
        try {
            System.out.println("cifrarLetra():> " + letra);
            if (letra != 32) {
                ASCII = t.CpesquisaTabelaASCII(letra).getDecimalASCIICifrado();
            } else {
                ASCII = 35;
            }
        } catch (Exception ex) {
            Logger.getLogger(CifraDeCesarMono.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ASCII;
    }

    public int descifrarLetra(int letra) {
        int ASCII = 0;
        tabelaDAO t = new tabelaDAO();
        try {
            System.out.println("descifrarLetra():> " + letra);
            if (letra == 35) {
                ASCII = 32;
            } else {
                ASCII = t.DpesquisaTabelaASCII(letra).getDecimalASCII();
            }
        } catch (Exception ex) {
            Logger.getLogger(CifraDeCesarMono.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ASCII;
    }

    public String encriptarMono(String texto) {
        String s = RetiraAcento(texto);
        StringBuilder textoCifrado = new StringBuilder();
        int tamanhoTexto = s.length();
        for (int c = 0; c < tamanhoTexto; c++) {
            int letraCifradaASCII = cifraLetra(((int) s.charAt(c)));
            while (letraCifradaASCII > 126) {
                letraCifradaASCII -= 94;
            }
            textoCifrado.append((char) letraCifradaASCII);
        }
        return textoCifrado.toString();
    }

    public String decriptarMono(String textoCifrado) {
        StringBuilder texto = new StringBuilder();
        int tamanhoTexto = textoCifrado.length();
        for (int c = 0; c < tamanhoTexto; c++) {
            int letraDecifradaASCII = descifrarLetra(((int) textoCifrado.charAt(c)));
            while (letraDecifradaASCII < 32) {
                letraDecifradaASCII += 94;
            }
            texto.append((char) letraDecifradaASCII);
        }
        return texto.toString();
    }

    public static String RetiraAcento(String str) {
        str = Normalizer.normalize(str, Normalizer.Form.NFD);
        str = str.replaceAll("[^\\p{ASCII}]", "");
        return str;
    }
}
