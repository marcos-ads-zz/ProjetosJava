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
                + "date_vendimento, "
                + "valor_conta, "
                + "consumno, "
                + "ano) "
                + "values (?,?,?,?,?,?,?)";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, objEnerg.getMatricula());
        ps.setInt(2, objEnerg.getNotafiscal());
        ps.setDate(3, objEnerg.getDate_emissao());
        ps.setDate(4, objEnerg.getDate_vendimento());
        ps.setDouble(5, objEnerg.getValor_conta());
        ps.setInt(6, objEnerg.getConsumno());
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
                + "date_vendimento = ?, "
                + "valor_conta = ?, "
                + "consumno = ?, "
                + "WHERE id_agua = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, objEnerg.getMatricula());
        ps.setInt(2, objEnerg.getNotafiscal());
        ps.setDate(3, objEnerg.getDate_emissao());
        ps.setDate(4, objEnerg.getDate_vendimento());
        ps.setDouble(5, objEnerg.getValor_conta());
        ps.setInt(6, objEnerg.getConsumno());
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

    public List<Agua> TabelaPesquisaPorAno(String ano) throws Exception {
        con = new Conexao();
        Agua objEnerg = null;
        List<Agua> agua = new ArrayList<>();
        String SQL = "select * from relatorios.relatorio.agua where ano = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setString(1, ano);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objEnerg = new Agua();
            objEnerg.setId_agua(rs.getInt("id_agua"));
            objEnerg.setMatricula(rs.getInt("matricula"));
            objEnerg.setNotafiscal(rs.getInt("notafiscal"));
            objEnerg.setDate_emissao(rs.getDate("date_emissao"));
            objEnerg.setDate_vendimento(rs.getDate("date_vendimento"));
            objEnerg.setValor_conta(rs.getDouble("valor_conta"));
            objEnerg.setConsumno(rs.getInt("consumno"));
            objEnerg.setAno(rs.getString("ano"));
            agua.add(objEnerg);
        }
        con.getCONEXAO().close();
        return agua;
    }
}
