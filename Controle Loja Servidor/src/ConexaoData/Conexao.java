package ConexaoData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modulo.metodos.CryptoBase64;
import modulo.configuracoes.DadosConfig;

/**
 *
 * @author Marcos Junior
 */
public class Conexao {

    private CryptoBase64 cry;
    private DadosConfig dados;
    private Connection CONEXAO;
    private static final String DRIVER = "org.postgresql.Driver";

    public Conexao() throws Exception {
        dados = new DadosConfig();
        String host = cry.decrypt(dados.getHost());
        String port = cry.decrypt(dados.getPort());
        String base = cry.decrypt(dados.getBase());
        String user = cry.decrypt(dados.getUser());
        String pass = cry.decrypt(dados.getPass());    
        //System.out.println(pass);
        String ConexaoRede = "jdbc:postgresql://" + host + ":" + port + "/" + base + "";
        Class.forName(DRIVER);
        CONEXAO = DriverManager.getConnection(ConexaoRede, user, pass);
    }

    public Connection getCONEXAO() {
        return CONEXAO;
    }

    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Fechar Conexão Connection." + ex.getMessage());
        }
    }

    public static void closeConnection(Connection con, PreparedStatement str) {
        closeConnection(con);
        try {
            if (str != null) {
                str.close();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Fechar Conexão PreparedStatement." + ex.getMessage());
        }
    }

    public static void closeConnection(Connection con, PreparedStatement str, ResultSet rs) {
        closeConnection(con, str);
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Fechar Conexão ResultSet." + ex.getMessage());
        }
    }
}
