/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexaoData;

/**
 *
 * @author Marcos Junior
 */
public class DadosDataBase {

    public static String BD = "academia";
    public static String SCHE = "relatorio";

    public static String getBD() {
        return BD;
    }

    public static void setBD(String BD) {
        DadosDataBase.BD = BD;
    }

    public static String getSCHE() {
        return SCHE;
    }

    public static void setSCHE(String SCHE) {
        DadosDataBase.SCHE = SCHE;
    }

}
