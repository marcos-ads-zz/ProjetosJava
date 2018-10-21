package modulo.contas;

import ConexaoData.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcos Junior
 */
public class AguaDAO {

    Conexao con;

    public boolean Insert(Agua objEnerg) throws Exception {
        con = new Conexao();
        String SQL = "insert into relatorios.relatorio.agua("
                + "matricula, "
                + "notafiscal, "
                + "date_emissao, "
                + "date_vencimento, "
                + "valor_conta, "
                + "consumo, "
                + "ano) "
                + "values (?,?,?,?,?,?,?)";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, objEnerg.getMatricula());
        ps.setInt(2, objEnerg.getNotafiscal());
        ps.setDate(3, objEnerg.getDate_emissao());
        ps.setDate(4, objEnerg.getDate_vencimento());
        ps.setDouble(5, objEnerg.getValor_conta());
        ps.setInt(6, objEnerg.getConsumo());
        ps.setString(7, objEnerg.getAno());
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }

    public boolean Update(Agua objEnerg) throws Exception {
        con = new Conexao();
        String SQL = "UPDATE relatorios.relatorio.agua set "
                + "matricula = ?, "
                + "notafiscal = ?, "
                + "date_emissao = ?, "
                + "date_vencimento = ?, "
                + "valor_conta = ?, "
                + "consumo = ? "
                + "WHERE id_agua = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, objEnerg.getMatricula());
        ps.setInt(2, objEnerg.getNotafiscal());
        ps.setDate(3, objEnerg.getDate_emissao());
        ps.setDate(4, objEnerg.getDate_vencimento());
        ps.setDouble(5, objEnerg.getValor_conta());
        ps.setInt(6, objEnerg.getConsumo());
        ps.setInt(7, objEnerg.getId_agua());
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }

    public boolean Delete(int id) throws Exception {
        con = new Conexao();
        String SQL = "DELETE from relatorios.relatorio.agua where id_agua = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, id);
        return ps.executeUpdate() > 0;
    }

    public List<Agua> TabelaPesquisaTodos() throws Exception {
        con = new Conexao();
        Agua objEnerg = null;
        List<Agua> agua = new ArrayList<>();
        String SQL = "SELECT * FROM relatorios.relatorio.agua";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objEnerg = new Agua();
            objEnerg.setId_agua(rs.getInt("id_agua"));
            objEnerg.setMatricula(rs.getInt("matricula"));
            objEnerg.setNotafiscal(rs.getInt("notafiscal"));
            objEnerg.setDate_emissao(rs.getDate("date_emissao"));
            objEnerg.setDate_vencimento(rs.getDate("date_vencimento"));
            objEnerg.setValor_conta(rs.getDouble("valor_conta"));
            objEnerg.setConsumo(rs.getInt("consumo"));
            objEnerg.setAno(rs.getString("ano"));
            agua.add(objEnerg);
        }
        con.getCONEXAO().close();
        return agua;
    }

    public List<Agua> TabelaPesquisaPorAno(Double ano) throws Exception {
        con = new Conexao();
        Agua objEnerg = null;
        List<Agua> agua = new ArrayList<>();
        String SQL = "SELECT * FROM relatorios.relatorio.agua WHERE extract('Year' from date_vencimento) = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setDouble(1, ano);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objEnerg = new Agua();
            objEnerg.setId_agua(rs.getInt("id_agua"));
            objEnerg.setMatricula(rs.getInt("matricula"));
            objEnerg.setNotafiscal(rs.getInt("notafiscal"));
            objEnerg.setDate_emissao(rs.getDate("date_emissao"));
            objEnerg.setDate_vencimento(rs.getDate("date_vencimento"));
            objEnerg.setValor_conta(rs.getDouble("valor_conta"));
            objEnerg.setConsumo(rs.getInt("consumo"));
            objEnerg.setAno(rs.getString("ano"));
            agua.add(objEnerg);
        }
        con.getCONEXAO().close();
        return agua;
    }
}
