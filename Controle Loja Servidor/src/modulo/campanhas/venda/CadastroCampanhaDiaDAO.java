package modulo.campanhas.venda;

import ConexaoData.Conexao;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcos Junior
 */
public class CadastroCampanhaDiaDAO {

    Conexao con;

    public boolean Insert(CadastroCampanhaDia objCamp) throws Exception {
        con = new Conexao();
        String SQL = "insert into relatorios.relatorio.campanha("
                + "matricula, "
                + "desc_campanha, "
                + "quantidade, "
                + "data_registro) "
                + "values (?,?,?,?)";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, objCamp.getMatricula());
        ps.setString(2, objCamp.getDesc_campanha());
        ps.setInt(3, objCamp.getQuantidade());
        ps.setDate(4, objCamp.getData_registro());
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }

    public boolean Update(CadastroCampanhaDia obj) throws Exception {
        con = new Conexao();
        String SQL = "UPDATE relatorios.relatorio.campanha set "
                + "matricula = ?, "
                + "desc_campanha = ?, "
                + "quantidade = ?, "
                + "data_registro = ? "
                + "WHERE id = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, obj.getMatricula());
        ps.setString(2, obj.getDesc_campanha());
        ps.setInt(3, obj.getQuantidade());
        ps.setDate(4, obj.getData_registro());
        ps.setInt(5, obj.getId());
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }

    public boolean Delete(int id) throws Exception {
        con = new Conexao();
        String SQL = "DELETE from relatorios.relatorio.campanha where id = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setInt(1, id);
        boolean p = ps.executeUpdate() > 0;
        con.getCONEXAO().close();
        return p;
    }

    public List<CadastroCampanhaDia> TabelaPesquisaTodos() throws Exception {
        con = new Conexao();
        CadastroCampanhaDia objCamp = null;
        java.util.List<CadastroCampanhaDia> agua = new ArrayList<>();
        String SQL = "SELECT * FROM relatorios.relatorio.campanha order by 5";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objCamp = new CadastroCampanhaDia();
            objCamp.setId(rs.getInt("id"));
            objCamp.setMatricula(rs.getInt("matricula"));
            objCamp.setDesc_campanha(rs.getString("desc_campanha"));
            objCamp.setQuantidade(rs.getInt("quantidade"));
            objCamp.setData_registro(rs.getDate("data_registro"));
            agua.add(objCamp);
        }
        con.getCONEXAO().close();
        return agua;
    }

    public List<CadastroCampanhaDia> TabelaPesquisa() throws Exception {
        con = new Conexao();
        CadastroCampanhaDia objCamp;
        List<CadastroCampanhaDia> camp = new ArrayList<>();
        String SQL = "select * from relatorios.relatorio.campanha order by 5";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objCamp = new CadastroCampanhaDia();
            objCamp.setId(rs.getInt("id"));
            objCamp.setMatricula(rs.getInt("matricula"));
            objCamp.setDesc_campanha(rs.getString("desc_campanha"));
            objCamp.setQuantidade(rs.getInt("quantidade"));
            objCamp.setData_registro(rs.getDate("data_registro"));
            camp.add(objCamp);
        }
        con.getCONEXAO().close();
        return camp;

    }

    public List<CadastroCampanhaDia> TabelaPesquisaDia(Date data) throws Exception {
        con = new Conexao();
        CadastroCampanhaDia objCamp;
        List<CadastroCampanhaDia> camp = new ArrayList<>();
        String SQL = "select * from relatorios.relatorio.campanha "
                + "where "
                + "data_registro "
                + "between ? "
                + "and ? "
                + "order by 5";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setDate(1, data);
        ps.setDate(2, data);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objCamp = new CadastroCampanhaDia();
            objCamp.setId(rs.getInt("id"));
            objCamp.setMatricula(rs.getInt("matricula"));
            objCamp.setDesc_campanha(rs.getString("desc_campanha"));
            objCamp.setQuantidade(rs.getInt("quantidade"));
            objCamp.setData_registro(rs.getDate("data_registro"));
            camp.add(objCamp);
        }
        con.getCONEXAO().close();
        return camp;

    }

    public List<CadastroCampanhaDia> TabelaPesquisaMes(Date dataInicio, Date dataFim) throws Exception {
        con = new Conexao();
        CadastroCampanhaDia objCamp;
        List<CadastroCampanhaDia> camp = new ArrayList<>();
        String SQL = "select * from relatorios.relatorio.campanha "
                + "where "
                + "data_registro "
                + "between ? "
                + "and ? "
                + "order by 5";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setDate(1, dataInicio);
        ps.setDate(2, dataFim);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objCamp = new CadastroCampanhaDia();
            objCamp.setId(rs.getInt("id"));
            objCamp.setMatricula(rs.getInt("matricula"));
            objCamp.setDesc_campanha(rs.getString("desc_campanha"));
            objCamp.setQuantidade(rs.getInt("quantidade"));
            objCamp.setData_registro(rs.getDate("data_registro"));
            camp.add(objCamp);
        }
        con.getCONEXAO().close();
        return camp;

    }

    public List<CadastroCampanhaDia> PesquisaNome(String nome) throws Exception {
        con = new Conexao();
        CadastroCampanhaDia objCamp;
        List<CadastroCampanhaDia> camp = new ArrayList<>();
        String SQL = "select * from relatorios.relatorio.campanha where desc_campanha like ? order by 5";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setString(1, "%" + nome.toUpperCase() + "%");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objCamp = new CadastroCampanhaDia();
            objCamp.setId(rs.getInt("id"));
            objCamp.setMatricula(rs.getInt("matricula"));
            objCamp.setDesc_campanha(rs.getString("desc_campanha"));
            objCamp.setQuantidade(rs.getInt("quantidade"));
            objCamp.setData_registro(rs.getDate("data_registro"));
            camp.add(objCamp);
        }
        con.getCONEXAO().close();
        return camp;

    }

    public List<CadastroCampanhaDia> PesquisaNomeGraficoCampanha(Date dataInicio, Date dataFim, String nome) throws Exception {
        con = new Conexao();
        CadastroCampanhaDia objCamp;
        List<CadastroCampanhaDia> camp = new ArrayList<>();
        String SQL = "select * from relatorios.relatorio.campanha "
                + "where "
                + "data_registro "
                + "between ? and ? "
                + "and desc_campanha = ? order by 5";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setDate(1, dataInicio);
        ps.setDate(2, dataFim);
        ps.setString(3, nome);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objCamp = new CadastroCampanhaDia();
            objCamp.setId(rs.getInt("id"));
            objCamp.setMatricula(rs.getInt("matricula"));
            objCamp.setDesc_campanha(rs.getString("desc_campanha"));
            objCamp.setQuantidade(rs.getInt("quantidade"));
            objCamp.setData_registro(rs.getDate("data_registro"));
            camp.add(objCamp);
        }
        con.getCONEXAO().close();
        return camp;

    }

    public int PesquisaNomeGraficoCampanhaQT(Date data, String nome) throws Exception {
        con = new Conexao();
        int objCamp = 0;
        String SQL = "select * "
                + "SUM(quantidade) AS total "
                + "from relatorios.relatorio.campanha "
                + "where "
                + "data_registro "
                + "between ? and ? "
                + "and desc_campanha = ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setDate(1, data);
        ps.setDate(2, data);
        ps.setString(3, nome);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            objCamp = rs.getInt("total");
        }
        con.getCONEXAO().close();
        return objCamp;
    }
//SELECT 
//SUM(quantidade) AS total 
//FROM relatorio.campanha 
//where data_registro 
//between '2018-10-18' 
//and '2018-10-18' 
//and desc_campanha = 'BALANCE';

//SELECT * FROM relatorio.campanha where data_registro between ? and ? AND desc_campanha = ?;
    public boolean CheckSelect(String nome) throws Exception {
        con = new Conexao();
        boolean check = false;
        String SQL = "select * from relatorios.relatorio.campanha where desc_campanha like ?";
        PreparedStatement ps = con.getCONEXAO().prepareStatement(SQL);
        ps.setString(1, "%" + nome + "%");
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            check = true;
        }
        con.getCONEXAO().close();
        return check;
    }

}
