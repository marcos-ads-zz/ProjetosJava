package cifra.cesar;

import java.text.Normalizer;

/**
 *
 * @author Marcos Júnior <marcosneri@outlook.com.br>
 */
public class CifraDeCesar {

    public static String encriptar(int chave, String texto) {
        String s = RetiraAcento(texto);
        StringBuilder textoCifrado = new StringBuilder();
        int tamanhoTexto = s.length();
        for (int c = 0; c < tamanhoTexto; c++) {
            int letraCifradaASCII = ((int) s.charAt(c)) + (chave);
            while (letraCifradaASCII > 126) {
                letraCifradaASCII -= 94;
            }
            textoCifrado.append((char) letraCifradaASCII);
        }
        return textoCifrado.toString();
    }

    public static String decriptar(int chave, String textoCifrado) {
        StringBuilder texto = new StringBuilder();
        int tamanhoTexto = textoCifrado.length();
        for (int c = 0; c < tamanhoTexto; c++) {
            int letraDecifradaASCII = ((int) textoCifrado.charAt(c)) - (chave);
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
