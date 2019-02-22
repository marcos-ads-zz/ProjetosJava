package modulo.DAO;

import ConexaoData.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcos Junior
 */
public class CargosDAO {

    Conexao con;

    public boolean Insert(Cargos objFun) throws Exception {
        con = new Conexao();
        String SQL = "insert into relatorios.relatorio.cargos("
                + "descricao, "
                + "nivel) values (?,?)";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setString(1, objFun.getCargos().toUpperCase());
        ps.setInt(2, objFun.getNivel());
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }

    public boolean Update(Cargos objFun) throws Exception {
        con = new Conexao();
        String SQL = "UPDATE relatorios.relatorio.cargos set "
                + "descricao = ?, "
                + "nivel = ? WHERE id_cargo = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setString(1, objFun.getCargos().toUpperCase());
        ps.setInt(2, objFun.getNivel());
        ps.setInt(3, objFun.getId());
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }

    public boolean UpdateNome(Cargos objFun) throws Exception {
        con = new Conexao();
        String SQL = "UPDATE relatorios.relatorio.cargos set "
                + "descricao = ?, "
                + "nivel = ? WHERE descricao = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setString(1, objFun.getCargos().toUpperCase());
        ps.setInt(2, objFun.getNivel());
        ps.setString(3, objFun.getCargos().toUpperCase());
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }

    public boolean Delete(int id) throws Exception {
        con = new Conexao();
        String SQL = "DELETE from relatorios.relatorio.cargos where id_cargo = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, id);
        return ps.executeUpdate() > 0;
    }

    public List<Cargos> PesquisaNome(String nome) throws Exception {
        con = new Conexao();
        Cargos objCargo = null;
        List<Cargos> cargos = new ArrayList<>();
        String SQL = "select * from relatorios.relatorio.cargos where descricao like ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setString(1, "%" + nome.toUpperCase() + "%");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objCargo = new Cargos();
            objCargo.setId(rs.getInt("id_cargo"));
            objCargo.setCargos(rs.getString("descricao"));
            objCargo.setNivel(rs.getInt("nivel"));
            cargos.add(objCargo);
        }
        con.getCONEXAO().close();
        return cargos;

    }

    public List<Cargos> Pesquisa() throws Exception {
        con = new Conexao();
        Cargos objCargo = null;
        List<Cargos> cargos = new ArrayList<>();
        String SQL = "select * from relatorios.relatorio.cargos";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objCargo = new Cargos();
            objCargo.setId(rs.getInt("id_cargo"));
            objCargo.setCargos(rs.getString("descricao"));
            objCargo.setNivel(rs.getInt("nivel"));
            cargos.add(objCargo);
        }
        con.getCONEXAO().close();
        return cargos;

    }

    public boolean CheckSelect(String cargo) throws Exception {
        con = new Conexao();
        boolean check = false;
        String SQL = "select * from relatorios.relatorio.cargos where descricao = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setString(1, cargo);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            check = true;
        }
        con.getCONEXAO().close();
        return check;
    }

}
