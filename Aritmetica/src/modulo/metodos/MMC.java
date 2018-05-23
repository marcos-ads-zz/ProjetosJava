package modulo.metodos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class MMC {

    public long carregaMMC(List<Integer> numeros) {
        List<Integer> fatores = new ArrayList<>();
        int fator = 2;
        while (!estaDecomposto(numeros)) {
            if (ehFator(numeros, fator)) {
                fatores.add(fator);
                decompor(numeros, fator);
            } else {
                fator++;
            }
        }
        //System.out.println(fatores + " => " + prod(fatores));
        System.out.println(prod(fatores));
        return prod(fatores);
    }

    private static void decompor(final List<Integer> numeros, int fator) {
        for (Integer n : numeros) {
            //se o número não estiver decomposto, ou seja, for diferente de 1
            if (n != 1) {
                //se o fator for divisor do número,
                //então substituímos o número pelo quociente da divisão
                if (n % fator == 0) {
                    numeros.set(numeros.indexOf(n), n / fator);
                }
            }
        }
    }

    public static boolean estaDecomposto(final List<Integer> numeros) {
        for (Integer n : numeros) {
            //retorna false caso algum número seja diferente de 1
            if (n != 1) {
                return false;
            }
        }
        //retorna true caso todos os números sejam igual a 1
        return true;
    }

    public static boolean ehFator(final List<Integer> numeros, int fator) {
        for (Integer n : numeros) {
            //retorna true caso seja divisor de algum número na lista
            if (n % fator == 0) {
                return true;
            }
        }
        //retorna false caso não seja divisor de nenhum número na lista
        return false;
    }

    public static long prod(List<Integer> fatores) {
        long p = 1;
        for (Integer f : fatores) {
            p *= f;
        }
        return p;
    }
}
