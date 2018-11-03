package cifra.vigenere;

/**
 *
 * @author Marcos Júnior <marcosneri@outlook.com.br>
 */
public class GenerarMatriz {

    public char[][] generarMatriz() {
        int contador;
        GerarAlfabeto g = new GerarAlfabeto();
        char abcTemp[] = g.gerarAlfabeto();
        char abc[] = new char[abcTemp.length * 2];

        for (int c = 0; c < 96; c++) {
            abc[c] = abcTemp[c];
            abc[c + 96] = abcTemp[c];
        }

        char[][] matriz = new char[96][96];
        
        for (int i = 0; i < 96; i++) {
            contador = 0;
            for (int j = 0; j < 96; j++) {
                matriz[i][j] = abc[contador + i];
                contador++;
            }
        }
        return matriz;
    }
}
