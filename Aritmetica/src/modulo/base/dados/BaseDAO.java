package modulo.base.dados;

import ConexaoData.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class BaseDAO {

    Conexao con;

    public boolean Insert(Base objBase) throws Exception {
        con = new Conexao();
        String SQL = "INSERT INTO relatorios.relatorio.base(dados, peso, valor0, valor1, valor2, valor3) "
                + "VALUES (?,?,?,?,?,?)";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, objBase.getDados());
        ps.setInt(2, objBase.getPeso());
        ps.setInt(3, objBase.getValor0());
        ps.setInt(4, objBase.getValor1());
        ps.setInt(5, objBase.getValor2());
        ps.setInt(6, objBase.getValor3());
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }

    public boolean Update(Base objBase) throws Exception {
        con = new Conexao();
        String SQL = "UPDATE relatorios.relatorio.base set "
                + "dados = ?, "
                + "peso = ? "
                + "valor0 = ? "
                + "valor1 = ? "
                + "valor2 = ? "
                + "valor3 = ? "
                + "WHERE id = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, objBase.getDados());
        ps.setInt(2, objBase.getPeso());
        ps.setInt(3, objBase.getValor0());
        ps.setInt(4, objBase.getValor1());
        ps.setInt(5, objBase.getValor2());
        ps.setInt(6, objBase.getValor3());
        ps.setInt(7, objBase.getId());
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }

    public boolean Delete(int id) throws Exception {
        con = new Conexao();
        String SQL = "DELETE from relatorios.relatorio.base where id = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, id);
        return ps.executeUpdate() > 0;
    }

    public List<Base> PesquisaValor(int valor) throws Exception {
        con = new Conexao();
        Base objBase;
        List<Base> base = new ArrayList<>();
        String SQL = "select * from relatorios.relatorio.base where dados ? order by 2";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, valor);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objBase = new Base();
            objBase.setId(rs.getInt("id"));
            objBase.setDados(rs.getInt("dados"));
            objBase.setPeso(rs.getInt("peso"));
            objBase.setValor0(rs.getInt("valor0"));
            objBase.setValor1(rs.getInt("valor1"));
            objBase.setValor2(rs.getInt("valor2"));
            objBase.setValor3(rs.getInt("valor3"));
            base.add(objBase);
        }
        con.getCONEXAO().close();
        return base;

    }

    public List<Base> Pesquisa() throws Exception {
        con = new Conexao();
        Base objBase = null;
        List<Base> base = new ArrayList<>();
        String SQL = "select * from relatorios.relatorio.base order by 2";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objBase = new Base();
            objBase.setId(rs.getInt("id"));
            objBase.setDados(rs.getInt("dados"));
            objBase.setPeso(rs.getInt("peso"));
            objBase.setValor0(rs.getInt("valor0"));
            objBase.setValor1(rs.getInt("valor1"));
            objBase.setValor2(rs.getInt("valor2"));
            objBase.setValor3(rs.getInt("valor3"));
            base.add(objBase);
        }
        con.getCONEXAO().close();
        return base;
    }
}
