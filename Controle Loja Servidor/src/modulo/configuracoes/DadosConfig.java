package modulo.configuracoes;

import java.util.Properties;

/**
 *
 * @author Marcos Junior
 */
public class DadosConfig {

    public static String loja; //Variavel que guardará o login do servidor.
    public static String host; //Variavel que guardará o host do servidor.
    public static String port; //Variável que guardará o port do usúario.
    public static String user; //Variavel que guardará o user do servidor.
    public static String pass; //Variável que guardará o pass do usúario.
    public static String base; //Variavel que guardará o base do servidor.
    public static Properties prop;

    public static String getLoja() {
        return loja;
    }

    public static void setLoja(String loja) {
        DadosConfig.loja = loja;
    }

    public static String getHost() {
        return host;
    }

    public static void setHost(String host) {
        DadosConfig.host = host;
    }

    public static String getPort() {
        return port;
    }

    public static void setPort(String port) {
        DadosConfig.port = port;
    }

    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        DadosConfig.user = user;
    }

    public static String getPass() {
        return pass;
    }

    public static void setPass(String pass) {
        DadosConfig.pass = pass;
    }

    public static String getBase() {
        return base;
    }

    public static void setBase(String base) {
        DadosConfig.base = base;
    }

    public static Properties getProp() {
        return prop;
    }

    public static void setProp(Properties prop) {
        DadosConfig.prop = prop;
    }

}
