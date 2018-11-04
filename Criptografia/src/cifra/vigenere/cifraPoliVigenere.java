/*
 * Para alterar esse cabeçalho de licença, escolha Cabeçalhos de licença em Propriedades do projeto.
 * Para alterar este arquivo de modelo, escolha Ferramentas | Modelos
 * e abra o modelo no editor.
 */
package cifra.vigenere;

/**
 *
 * @author Marcos Júnior <marcosneri@outlook.com.br>
 */
public class cifraPoliVigenere {

    public static String TxT, Seed;
    public static String Charset = "abcdefghijklmnopqrstuvwxyz";

    public cifraPoliVigenere(String TxT, String Seed) {
        this.Seed = Desn(Seed);
        this.TxT = TxT;
    }

    public static String Cifrar() {
        String Cifrado = "", a = TxT.toLowerCase();
        for (int n = 0, c = 0; n < TxT.length(); n++, c = (c + 1) % Seed.length()) {
            if (Charset.indexOf(a.charAt(n)) != -1) {
                int tmp = (Charset.indexOf(a.charAt(n)) + Charset.indexOf(Seed.charAt(c))) % Charset.length();
                Cifrado += (Charset.indexOf(TxT.charAt(n)) != -1) ? Charset.charAt(tmp) : String.valueOf(Charset.charAt(tmp)).toUpperCase();
            } else {
                c -= 1;
                Cifrado += TxT.charAt(n);
            }
        }
        return Cifrado;
    }

    public static String DesCifrar() {
        String DesCifrado = "";
        String a = TxT.toLowerCase();
        for (int n = 0, c = 0; n < a.length(); n++, c = (c + 1) % Seed.length()) {
            if (Charset.indexOf(a.charAt(n)) != -1) {
                int tmp = (Charset.indexOf(a.charAt(n)) - Charset.indexOf(Seed.charAt(c)));
                tmp = (tmp < 0) ? (tmp + Charset.length()) : tmp;
                DesCifrado += (Charset.indexOf(TxT.charAt(n)) != -1) ? Charset.charAt(tmp) : String.valueOf(Charset.charAt(tmp)).toUpperCase();
            } else {
                c -= 1;
                DesCifrado += TxT.charAt(n);
            }
        }
        return DesCifrado;
    }

    public static String Desn(String a) {
        String b = "";
        for (int n = 0; n < a.length(); n++) {
            if ((Charset.indexOf(a.charAt(n)) != -1) || (Charset.contains(String.valueOf(a.charAt(n)).toLowerCase()))) {
                b += a.charAt(n);
            }
        }
        return b;
    }

}
