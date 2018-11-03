package base;

import criptografia.CryptoBase64;
import criptografia.DadosConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import confProperties.Config;

/**
 *
 * @author Marcos Junior
 */
public final class Conexao {

    private CryptoBase64 cry;
    private DadosConfig dados;
    private Config conf;
    private static final String DRIVER = "org.postgresql.Driver";

    public Connection Conexao() throws Exception {
        dados = new DadosConfig();
        cry = new CryptoBase64();
        conf.CarregaDados();
        String host = cry.decrypt(dados.getHost());
        String port = cry.decrypt(dados.getPort());
        String base = cry.decrypt(dados.getBase());
        String user = cry.decrypt(dados.getUser());
        String pass = cry.decrypt(dados.getPass());
        String ConexaoRede = "jdbc:postgresql://" + host + ":" + port + "/" + base + "";
        Class.forName(DRIVER);
        Connection con = DriverManager.getConnection(ConexaoRede, user, pass);
        return con;
    }

    public Connection getConexao() throws Exception {
        return Conexao();
    }

    public boolean getValidaConexao(int i) throws Exception {
        return Conexao().isValid(i);
    }

    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Fechar Conexão Connection. " + ex.getMessage());
        }
    }

    public static void closeConnection(Connection con, PreparedStatement str) {
        closeConnection(con);
        try {
            if (str != null) {
                str.close();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Fechar Conexão PreparedStatement. " + ex.getMessage());
        }
    }

    public static void closeConnection(Connection con, PreparedStatement str, ResultSet rs) {
        closeConnection(con, str);
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Fechar Conexão ResultSet. " + ex.getMessage());
        }
    }
}
