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
public class GerarAlfabeto {

    //Cria um array de characteres com 96 posições
    public char[] gerarAlfabeto() {
        char[] abc = new char[96];

        for (int i = 32; i <= 127; i++) {
            abc[i - 32] = (char) i;
        }
        return abc;
    }
}
