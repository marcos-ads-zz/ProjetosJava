/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.login;

/**
 *
 * @author Marcos Junior
 */
public class UserLogado {

    static String nomelogin;
    static String matricula;
    static int numeroloja;

    public static String getNomelogin() {
        return nomelogin;
    }

    public static void setNomelogin(String nomelogin) {
        UserLogado.nomelogin = nomelogin;
    }

    public static String getMatricula() {
        return matricula;
    }

    public static void setMatricula(String matricula) {
        UserLogado.matricula = matricula;
    }

    public static int getNumeroloja() {
        return numeroloja;
    }

    public static void setNumeroloja(int numeroloja) {
        UserLogado.numeroloja = numeroloja;
    }

}
