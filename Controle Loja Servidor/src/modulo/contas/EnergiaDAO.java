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
public class EnergiaDAO {

    Conexao con;

    public boolean Insert(Energia objEnerg) throws Exception {
        con = new Conexao();
        String SQL = "insert into relatorios.relatorio.energia("
                + "codigo, "
                + "notafiscal, "
                + "date_emissao, "
                + "date_vencimento, "
                + "valor_conta, "
                + "consumo, "
                + "ano) "
                + "values (?,?,?,?,?,?,?)";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, objEnerg.getCodigo());
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

    public boolean Update(Energia objEnerg) throws Exception {
        con = new Conexao();
        String SQL = "UPDATE relatorios.relatorio.energia set "
                + "codigo = ?, "
                + "notafiscal = ?, "
                + "date_emissao = ?, "
                + "date_vencimento = ?, "
                + "valor_conta = ?, "
                + "consumo = ? "
                + "WHERE id_energ = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, objEnerg.getCodigo());
        ps.setInt(2, objEnerg.getNotafiscal());
        ps.setDate(3, objEnerg.getDate_emissao());
        ps.setDate(4, objEnerg.getDate_vencimento());
        ps.setDouble(5, objEnerg.getValor_conta());
        ps.setInt(6, objEnerg.getConsumo());
        ps.setInt(7, objEnerg.getId_energ());
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }

    public boolean Delete(int id) throws Exception {
        con = new Conexao();
        String SQL = "DELETE from relatorios.relatorio.energia where id_energ = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, id);
        return ps.executeUpdate() > 0;
    }

    public List<Energia> TabelaPesquisaTodos() throws Exception {
        con = new Conexao();
        Energia objEnerg = null;
        List<Energia> energia = new ArrayList<>();
        String SQL = "SELECT * FROM relatorios.relatorio.energia";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objEnerg = new Energia();
            objEnerg.setId_energ(rs.getInt("id_energ"));
            objEnerg.setCodigo(rs.getInt("codigo"));
            objEnerg.setNotafiscal(rs.getInt("notafiscal"));
            objEnerg.setDate_emissao(rs.getDate("date_emissao"));
            objEnerg.setDate_vencimento(rs.getDate("date_vencimento"));
            objEnerg.setValor_conta(rs.getDouble("valor_conta"));
            objEnerg.setConsumo(rs.getInt("consumo"));
            objEnerg.setAno(rs.getString("ano"));
            energia.add(objEnerg);
        }
        con.getCONEXAO().close();
        return energia;
    }

    public List<Energia> TabelaPesquisaPorAno(Double ano) throws Exception {
        con = new Conexao();
        Energia objEnerg = null;
        List<Energia> energia = new ArrayList<>();
        String SQL = "SELECT * FROM relatorios.relatorio.energia WHERE extract('Year' from date_vencimento) = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setDouble(1, ano);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objEnerg = new Energia();
            objEnerg.setId_energ(rs.getInt("id_energ"));
            objEnerg.setCodigo(rs.getInt("codigo"));
            objEnerg.setNotafiscal(rs.getInt("notafiscal"));
            objEnerg.setDate_emissao(rs.getDate("date_emissao"));
            objEnerg.setDate_vencimento(rs.getDate("date_vencimento"));
            objEnerg.setValor_conta(rs.getDouble("valor_conta"));
            objEnerg.setConsumo(rs.getInt("consumo"));
            objEnerg.setAno(rs.getString("ano"));
            energia.add(objEnerg);
        }
        con.getCONEXAO().close();
        return energia;
    }

}
