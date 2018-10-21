package modulo.configuracoes;

import java.io.IOException;
import java.util.Properties;
import static modulo.configuracoes.JifConfig.getProp;

/**
 *
 * @author Marcos Junior
 */
public class Config {

    private static Properties prop;
    private static DadosConfig props;

    public static void CarregaDados() throws IOException {
        prop = getProp();
        props.setLoja(prop.getProperty("prop.server.loja"));
        props.setHost(prop.getProperty("prop.server.host"));
        props.setPort(prop.getProperty("prop.server.port"));
        props.setUser(prop.getProperty("prop.server.user"));
        props.setPass(prop.getProperty("prop.server.password"));
        props.setBase(prop.getProperty("prop.server.base"));
    }
}
